
# ifndef ROUTE_DATA_HPP
# define ROUTE_DATA_HPP

# include "utils.hpp"
# include <stdio.h>
# include <iostream>

class RouteData {

public:

    ThreadSafeMap<
        std::string,
        std::vector<uintptr_t>
    > ptr_map;
    bool out_ptr;

    ThreadSafeMap<
        std::string,
        std::unordered_set<std::string>
    > route_map;

    struct fn_path_buff {
        int start_idx = -1;
        std::vector<int> data;
    };

    ThreadSafeMap<
        std::string,
        fn_path_buff
    > route_buff;

    std::vector<std::string> focus_files;

    bool enable;
    bool has_data;

    std::uint64_t start_ns = 0;
    std::uint64_t end_ns = 0;

    std::shared_mutex mtx;
    std::string full_cmd_line;

    RouteData()
        : out_ptr(false), mtx(), enable(true), has_data(false)
    {
        std::unique_lock<std::shared_mutex> ul(this->mtx);
        if (std::getenv("DISABLE_ROUTE_COVERAGE_LOGGING") != NULL) {
            this->enable = false;
        }
        else {
            const char* focus_files_env = std::getenv("RCOV_FOCUS_FILES");
            if (focus_files_env != NULL) {
                std::string focus_files_str = std::string(focus_files_env);
                this->focus_files = split(focus_files_str, ':');
            }
        }

        if (std::getenv("OUT_PTR") != NULL) {
            this->out_ptr = true;
        }
        
        std::string proc_name = get_curr_process_name();
        if (!proc_name.empty() && std::getenv("BLOCK_EXEC_FILE") != NULL) {
            std::vector<std::string> block_exec_file_list = split(std::string(std::getenv("BLOCK_EXEC_FILE")), ':');
            for (const std::string& block_exec_file : block_exec_file_list) {
                if (!proc_name.empty() && proc_name.find(block_exec_file) != std::string::npos) {
                    this->enable = false;
                    break;
                }
            }
        }
        this->full_cmd_line = vec_to_str(get_command_line_args());
        if (this->full_cmd_line.empty()) {
            this->full_cmd_line = "Unknown";
        }
        this->start_ns = get_ns();
    }

    ~RouteData() {
        std::unique_lock<std::shared_mutex> ul(this->mtx);

        bool expected = true;
        if (!this->enable) {
            return;
        }
        this->enable = false;

        this->end_ns = get_ns();
        if (!this->has_data) {
            return;
        }

        std::string out_file_path = "";
        if (std::getenv("TIME_FILE") != nullptr) {
            out_file_path = 
                std::string(std::getenv("TIME_FILE")) + "/"
                + std::to_string(this->start_ns) + "_"
                + std::to_string(this->end_ns) + "_route_data.log";
        }
        else if (std::getenv("RCOV_OUT_FILE_NAME") != nullptr) {
            out_file_path = std::string(std::getenv("RCOV_OUT_FILE_NAME"));
        }
        else if (std::getenv("RCOV_OUT_FILE") == nullptr) {
            // to std err
        }
        else {
            out_file_path = std::string(std::getenv("PWD")) + "/route_data.log";
        }

        if (!out_file_path.empty()) {
            FILE *fp = fopen(out_file_path.c_str(), "w");
            if (fp) {
                fprintf(fp, "%s", this->to_str(false).c_str());
                fflush(fp);
                fclose(fp);
            }
        }
        else {
            std::cerr << this->to_str(true) << std::flush << std::endl;
        }
    }

    void add_ptr(uintptr_t ptr) {
        std::shared_lock<std::shared_mutex> lgb(this->mtx);
        if (!this->enable) {
            return;
        }
        auto& ptr_vec = this->ptr_map.get_or_create(get_thread_id_str());
        ptr_vec.push_back(ptr);
        this->has_data = true;
    }
    
    void add_route(
        const std::string_view& src_file_,
        const std::string_view& fn_name_,
        int line_no
    ) {
        std::shared_lock<std::shared_mutex> lgb(this->mtx);
        if (!this->enable) {
            return;
        }

        std::string key_str = get_thread_id_str() + "////" +
            src_file_.data() + "////" + fn_name_.data();

        auto& buff_data = this->get_buff_element(key_str);

        int start_idx = buff_data.start_idx;
        this->has_data = true;

        if (start_idx == -1) {
            start_idx = line_no;
            buff_data.start_idx = line_no;
            buff_data.data.push_back(line_no);
        }
        else if (line_no <= start_idx) {
            if (!buff_data.data.empty()) {
                auto& bf_data = buff_data.data;            
                std::string path_str = this->fn_path_buff_to_string(bf_data);
                auto& target_set = this->route_map.get_or_create(key_str);
                target_set.emplace(path_str);
            }
            buff_data.data.clear();
        }
        else {
            if (buff_data.data.empty()) {
                buff_data.data.push_back(start_idx);
            }
            else if (line_no > buff_data.data.back()) {
                buff_data.data.push_back(line_no);
            }
        }
    }

    bool is_enabled() {
        return this->enable;
    }

    bool is_out_ptr() const {
        return this->out_ptr;
    }

    bool can_log(const std::string_view& file_name) {
        if (file_name.empty()) {
            return false;
        }
        if (
            file_name.find(".cpp") == std::string::npos &&
            file_name.find(".hpp") == std::string::npos &&
            file_name.find(".cc") == std::string::npos &&
            file_name.find(".c") == std::string::npos
        ) {
            return false;
        }

        std::shared_lock<std::shared_mutex> lgb(this->mtx);
        if (!this->enable) {
            return false;
        }

        if (this->focus_files.empty()) {
            return true;
        }

        bool matched = false;
        for (const std::string& focus_file : this->focus_files) {
            if (file_name.find(focus_file) != std::string::npos) {
                matched = true;
                break;
            }
        }

        return matched;
    }

private:

    fn_path_buff& get_buff_element(
        const std::string_view& key
    ) {
        return this->route_buff.get_or_create(key.data());
    }

    std::string fn_path_buff_to_string(
        const std::vector<int>& buff
    ) {
        std::string path_str;
        int prev = buff[0];
        path_str.append(std::to_string(prev));
        for (int idx = 1; idx < buff.size(); idx++) {
            if (buff[idx] > prev) {
                path_str.append("->" + std::to_string(buff[idx]));
                prev = buff[idx];
            }
        }
        return path_str;
    }

    std::pair<std::string, std::string> split_src_fn(const std::string& key) {
        auto parts = split(key, "////");
        return {parts[1], parts[2]};
    }

    std::string ptr_to_str(bool with_preffix = false) {
        std::stringstream ss;
        if (with_preffix)   ss << "[RCOV] ";
        ss << "START_NS:::: " + std::to_string(this->start_ns) + "\n";
        if (with_preffix)   ss << "[RCOV] ";
        ss << "END_NS:::: " + std::to_string(this->end_ns) + "\n";
        if (with_preffix)   ss << "[RCOV] ";
        ss << "CMD_LINE:::: " + this->full_cmd_line + "\n";
        auto buf_map = this->ptr_map.copy_and_clear();
        for (auto& map_item : buf_map) {
            auto& ptr_vec = map_item.second;
            if (!ptr_vec.empty()) {
                for (const auto& ptr : ptr_vec) {
                    if (with_preffix)   ss << "[RCOV] ";
                    ss << "0x" << std::hex << ptr << std::endl;
                }
            }
        }
        return ss.str();
    }

    std::string to_str(bool with_preffix = false) {
        if (this->out_ptr) {
            return this->ptr_to_str(with_preffix);
        }
        using namespace std;
        string out_str = "";

        auto buf_map = this->route_buff.copy_and_clear();
        for (auto& map_item : buf_map) {
            auto& bf_data = map_item.second.data;
            if (!bf_data.empty()) {
                std::string path_str = this->fn_path_buff_to_string(bf_data);
                auto& target_set = this->route_map.get_or_create(map_item.first);
                target_set.emplace(path_str);
            }
        }

        std::unordered_map<
            std::string,
            std::unordered_map<
                std::string,
                std::unordered_set<std::string>
            >
        > true_data;
        auto map_data = this->route_map.copy_and_clear();
        for (auto& map_item : map_data) {
            auto src_fn = this->split_src_fn(map_item.first);
            auto& src = src_fn.first;
            auto& fn_name = src_fn.second;
            auto& path_set = map_item.second;
            auto& target_set = true_data[src][fn_name];
            for (const auto& path : path_set) {
                target_set.emplace(path);
            }
        }

        if (with_preffix)   out_str += "[RCOV] ";
        out_str += "START_NS:::: " + std::to_string(this->start_ns) + "\n";
        if (with_preffix)   out_str += "[RCOV] ";
        out_str += "END_NS:::: " + std::to_string(this->end_ns) + "\n";
        if (with_preffix)   out_str += "[RCOV] ";
        out_str += "CMD_LINE:::: " + this->full_cmd_line + "\n";
        
        for (const auto& map_item: true_data) {
            const auto& src = map_item.first;
            const auto& fn_route_map = map_item.second;
            if (with_preffix)   out_str += "[RCOV] ";
            out_str += "File:::: " + src + "\n";
            for (const auto& map_item2 : fn_route_map) {
                const auto& fn_name = map_item2.first;
                const auto& path_rec = map_item2.second;
                if (with_preffix)   out_str += "[RCOV] ";
                out_str += "Function:::: " + fn_name + "\n";
                for (const auto& path : path_rec) {
                    if (with_preffix)   out_str += "[RCOV] ";
                    out_str += path + "\n";
                }
            }
        }

        return out_str;
    }

    

};

# endif // ROUTE_DATA_HPP
