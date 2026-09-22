# include "./libbacktrace/build/include/backtrace.h"
# include "./route_data.hpp"
# include <string>
# include <cstdlib>
# include <cxxabi.h>

static RouteData g_route_data;

backtrace_state* g_bt = nullptr;

static void bt_error_ignore(void*, const char*, int) {}


static void append_data(const std::string& file_name,
                        const std::string& function_name,
                        int line_no) {
    try {
        g_route_data.add_route(file_name, function_name, line_no);
    } catch (...) {
        // ignore all errors
    }
}

static int bt_full_cb(void* data, uintptr_t pc,
                    const char* filename_, int lineno, const char* function) {
    if (!filename_) {
        return 0;
    }
    std::string filename = filename_;
    if (!g_route_data.can_log(filename)) {
        return 0;
    }

    const char* demangled = function;
    int status = 0;
    char* dem = function ? abi::__cxa_demangle(function, nullptr, nullptr, &status) : nullptr;
    if (dem && status == 0) demangled = dem;
    if (!demangled) {
        free(dem);
        return 0;
    }
    
    append_data(filename, demangled, lineno);
    
    free(dem);
    return 0;
}

static void ensure_bt_init() {
    if (g_bt) return;
    g_bt = backtrace_create_state(nullptr, 1, bt_error_ignore, nullptr);
}

extern "C" void __sanitizer_cov_trace_pc_guard_init(uint32_t* start, uint32_t* stop) {
    static uint32_t N;
    if (start == stop || *start) return;
    for (uint32_t* p = start; p < stop; ++p) *p = ++N;
}

extern "C" void __sanitizer_cov_trace_pc_guard(uint32_t* guard) {
    if (!guard || !*guard) {
        return;
    }
    if (!g_route_data.is_enabled()) return;
    ensure_bt_init();

    void* pc = __builtin_return_address(0);
    if (g_route_data.is_out_ptr()) {
        g_route_data.add_ptr((uintptr_t)pc);
    }
    else {
        backtrace_pcinfo(g_bt, (uintptr_t)pc, bt_full_cb, bt_error_ignore, nullptr);
    }
}
