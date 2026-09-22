rm -f *.o
$CLANG_HOME/bin/clang++ -O0 -g -fno-omit-frame-pointer -fno-inline -fsanitize-coverage=trace-pc-guard,bb,no-prune -c main.cpp -o main.o
$CLANG_HOME/bin/clang++ main.o ../lib/librcov.so -o main
./main

