
// # include <assanitize/assanitize.h>
# include <iostream>

void func(int a) {
    if (a > 0) {
        a = 20;
    }
    if (a < 200) {
        a = 30;
    }
    // std::cout << a << std::endl;
}


int main() {
    int a[10] = {0};
    func(a[0]);
    if (a[0] > 0) {
        a[0] = 20;
    }
    if (a[0] < 200) {
        a[0] = 30;
    }
    std::cout << a << std::endl;
    return 0;
}
