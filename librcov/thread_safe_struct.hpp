# ifndef _THREAD_SAFE_STRUCT_HPP_
# define _THREAD_SAFE_STRUCT_HPP_

# include <unordered_set>
# include <unordered_map>
# include <shared_mutex>
# include <sstream>
# include <atomic>
# include <thread>
# include <string>
# include <vector>
# include <mutex>

template<typename KeyType, typename ValueType>
class ThreadSafeMap {

    static_assert(std::is_same<KeyType, std::string>::value, "KeyType must be std::string");

private:

    struct thread_safe_map_ele {
        std::string thread_id_str;
        std::unordered_map<std::string, ValueType> data;
    };

    std::vector<thread_safe_map_ele> data_;
    std::unordered_map<std::string, std::size_t> idx_map_;
    std::shared_mutex gb_mutex_;
    std::atomic<bool> enable = true;

    inline std::string get_thread_id_str() {
        std::ostringstream ss;
        ss << std::this_thread::get_id();
        return ss.str();
    }

    std::size_t get_idx(const std::string_view& key) {
        {
            std::shared_lock lock(this->gb_mutex_);
            auto iter = this->idx_map_.find(key.data());
            if (iter != this->idx_map_.end()) {
                return iter->second;
            }
        }
        std::unique_lock lock(this->gb_mutex_);
        auto iter = this->idx_map_.find(key.data());
        if (iter != this->idx_map_.end()) {
            return iter->second;
        }
        this->data_.push_back({std::string(key), {}});
        this->idx_map_[key.data()] = this->data_.size() - 1;
        return this->data_.size() - 1;
    }

public:

    ThreadSafeMap() = default;

    ~ThreadSafeMap() = default;

    std::unordered_map<std::string, ValueType> copy_and_clear() {
        std::unique_lock g_lock(this->gb_mutex_);
        std::unordered_map<std::string, ValueType> ret;
        for (auto &bucket : this->data_) {
            for (auto& item : bucket.data) {
                ret.emplace(item.first, item.second);
            }
            bucket.data.clear();
        }
        this->enable = false;
        return ret;
    }

    ValueType& get_or_create(const std::string_view& key) {
        int idx = this->get_idx(this->get_thread_id_str());
        {
            std::shared_lock lock(this->gb_mutex_);
            if (!this->enable) {
                throw std::runtime_error("ThreadSafeMap is disabled");
            }
            return this->data_[idx].data[key.data()];
        }
    }

    bool contains(const std::string_view& key) {
        int idx = this->get_idx(this->get_thread_id_str());
        {
            std::shared_lock lock(this->gb_mutex_);
            if (!this->enable) {
                throw std::runtime_error("ThreadSafeMap is disabled");
            }
            return this->data_[idx].contains(key.data());
        }
    }

};

template<typename ValueType>
class ThreadSafeSet {

private:
    
    std::unordered_set<ValueType> *data_;
    std::shared_mutex mutex_;

public:

    ThreadSafeSet() {
        this->data_ = new std::unordered_set<ValueType>();
    }

    ~ThreadSafeSet() {
        delete this->data_;
    }

    void insert(const ValueType& value) {
        std::unique_lock lock(mutex_);
        this->data_->insert(value);
    }

    bool contains(const ValueType& value) {
        std::shared_lock lock(mutex_);
        return this->data_->find(value) != this->data_->end();
    }

    std::unordered_set<ValueType> copy_and_clear() {
        std::unique_lock lock(mutex_);
        std::unordered_set<ValueType> copy = *this->data_;
        this->data_->clear();
        return copy;
    }

};

// template<typename ValueType>
// class ThreadSafeVec {

// private:
    
//     std::vector<ValueType> *data_;
//     std::shared_mutex mutex_;

// public:

//     ThreadSafeVec() {
//         data_ = new std::vector<ValueType>();
//     }

//     ~ThreadSafeVec() {
//         delete data_;
//     }

//     void push_back(const ValueType& value) {
//         std::unique_lock lock(mutex_);
//         data_->push_back(value);
//     }

//     std::vector<ValueType> copy_and_clear() {
//         std::unique_lock lock(mutex_);
//         std::vector<ValueType> copy = *data_;
//         data_->clear();
//         return copy;
//     }

//     bool empty() {
//         std::shared_lock lock(mutex_);
//         return data_->empty();
//     }

//     std::size_t size() {
//         std::shared_lock lock(mutex_);
//         return data_->size();
//     }

//     void push_back_if_gt(const ValueType& value) {
//         std::unique_lock lock(mutex_);
//         if (data_->empty() || value > data_->back()) {
//             data_->push_back(value);
//         }
//     }

// };

# endif // _THREAD_SAFE_STRUCT_HPP_
