
# include "thread_safe_struct.hpp"
# include <unordered_set>
# include <shared_mutex>
# include <filesystem>
# include <unistd.h>
# include <sstream>
# include <fstream>
# include <thread>

static inline std::vector<std::string> split(std::string_view s, char delim) {
    std::vector<std::string> out;
    size_t start = 0;
    while (true) {
        size_t pos = s.find(delim, start);
        if (pos == std::string_view::npos) {
            out.emplace_back(s.substr(start));
            break;
        }
        std::string_view tmp = s.substr(start, pos - start);
        if (!tmp.empty()) {
            out.emplace_back(tmp);
        }
        start = pos + 1;
    }
    return out;
}

static inline std::vector<std::string> split(std::string_view s, const std::string_view& delim) {
    std::vector<std::string> out;
    size_t start = 0;
    while (true) {
        size_t pos = s.find(delim, start);
        if (pos == std::string_view::npos) {
            out.emplace_back(s.substr(start));
            break;
        }
        std::string_view tmp = s.substr(start, pos - start);
        if (!tmp.empty()) {
            out.emplace_back(tmp);
        }
        start = pos + delim.size();
    }
    return out;
}

static inline std::uint64_t get_ns() {
    return std::chrono::steady_clock::now().time_since_epoch() /
        std::chrono::nanoseconds(1);
}

static inline std::string get_thread_id_str() {
    std::ostringstream ss;
    ss << std::this_thread::get_id();
    return ss.str();
}

static inline std::string get_curr_process_name() {
    char path[4096];
    ssize_t n = readlink("/proc/self/exe", path, sizeof(path) - 1);
    if (n == -1) {
        return "";
    }
    path[n] = '\0';
    return std::string(path);
}

static inline std::vector<std::string> get_command_line_args() {
# ifdef __linux__
    std::vector<std::string> args;
    std::ifstream cmdline_file("/proc/self/cmdline", std::ios::in | std::ios::binary);
    
    if (!cmdline_file.is_open()) {
        return args;
    }

    std::string current_arg;
    char c;
    while (cmdline_file.get(c)) {
        if (c == '\0') {
            for (const auto& try_suff: {"", ".class"}) {                
                try {
                    std::string full_path = std::filesystem::canonical(current_arg + try_suff).string();
                    current_arg = full_path;
                    break;
                } catch (const std::filesystem::filesystem_error& e) {
                    // If there's an error getting the absolute path, keep the original argument
                }
            }
            args.push_back(current_arg);
            current_arg.clear();
        } else {
            current_arg += c;
        }
    }
    
    return args;
# endif
    return {};
}

static inline std::string vec_to_str(const std::vector<std::string>& vec) {
    std::string out_str;
    for (const auto& item : vec) {
        out_str += item + " ";
    }
    return out_str;
}

