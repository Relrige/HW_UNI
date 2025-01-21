#include "Functtions.h"
#include <iostream>
#include <chrono>

using namespace std;
using namespace std::chrono;

void test(int n,int times=1) {
    cout << "--------------Fibonachi for "<<n<<"----------------" << endl;
    funcTimeMeasering(fibonacciSimple_iterative, n, "iterative function", times);
    funcTimeMeasering(fibonacciOptimized_iterative, n, "fast iterative function", times);
    funcTimeMeasering([&](int n) { return fibonacciSimple_recursive(n, 0, 1); }, n, "recursive simple function", times);
    funcTimeMeasering(fibonacciOptimized_recursive, n, "recursive optimized function", times);
    cout << endl;
}

int main() {

    for(int i=16;i<=2048;i*=2)
        test(i,1000);

    test(300, 1000000);

    test(1000, 1000000);

    test(4000, 100000);

    return 0;
}