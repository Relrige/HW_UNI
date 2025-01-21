#pragma once

#include <iostream>
#include <chrono>

using namespace std;
using namespace std::chrono;

struct Matrix2x2 {
    unsigned long long _11, _12, _21, _22;

    Matrix2x2 operator*(const Matrix2x2& other) const {
        return {
            _11* other._11 + _12 * other._21,  
            _11* other._12 + _12 * other._22,
            _21* other._11 + _22 * other._21, 
            _21* other._12 + _22 * other._22 
        };
    }
};

struct Vector2 {
    unsigned long long _1, _2;
};

template<typename Func>
void funcTimeMeasering(Func fibonacciFunction, int n, const string& description,int times) {
    microseconds duration(0);
    unsigned long long result;

    for (int i = 0; i < times; i++) {
        auto start_clock = high_resolution_clock::now();
        result = fibonacciFunction(n); 
        auto end_clock = high_resolution_clock::now();

        duration += duration_cast<microseconds>(end_clock - start_clock);
    }
    cout << "Fibonacci F(" << n << ") = " << result<<" for " << times << " times" << endl;
    cout << "Time taken by " << description << ": " << duration.count() << " microseconds" << endl;
}

unsigned long long fibonacciOptimized_iterative(unsigned int n);
unsigned long long fibonacciSimple_iterative(unsigned int n);
unsigned long long fibonacciOptimized_recursive(unsigned int n);
unsigned long long fibonacciSimple_recursive(unsigned int n, unsigned long long a = 0, unsigned long long b = 1);

unsigned long long fibonacciSimple_recursiveUsingPower(unsigned int n);