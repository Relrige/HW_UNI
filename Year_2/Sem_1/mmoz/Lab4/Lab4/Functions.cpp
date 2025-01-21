#include "Functtions.h"
#include <iostream>
#include <cassert>
//#define DBUG
using namespace std;

Matrix2x2 multiplyMatrix(const Matrix2x2& first, const Matrix2x2& second) {
    Matrix2x2 resMatrix;
    resMatrix._11 = first._11 * second._11 + first._12 * second._21;
    resMatrix._12 = first._11 * second._12 + first._12 * second._22;
    resMatrix._21 = first._21 * second._11 + first._22 * second._21;
    resMatrix._22 = first._21 * second._12 + first._22 * second._22;
    return resMatrix;
}

Vector2 multiplyMatrixVector(const Matrix2x2& first, const Vector2& vector) {
    Vector2 resVector;
    resVector._1 = first._11 * vector._1 + first._12 * vector._2;
    resVector._2 = first._21 * vector._1 + first._22 * vector._2;
    return resVector;
}



Matrix2x2 identityMatrix() {
    return { 1, 0, 0, 1 };
}

Matrix2x2 iteretiveQuickPower(Matrix2x2 base, unsigned int exp) {
    Matrix2x2 result = identityMatrix();
    while (exp > 0) {
        if (exp % 2 == 1) {
            result = multiplyMatrix(result, base);
        }
        base = multiplyMatrix(base, base);
        exp /= 2;
    }
    return result;
}

Matrix2x2 simplePower_recursive(Matrix2x2 base, unsigned int exp) {
    if (exp < 1) throw invalid_argument("Exponent to be qreater 1");

    if (exp == 1) {
        return base;
    }
    return multiplyMatrix(base, simplePower_recursive(base, exp - 1));
}

Matrix2x2 quickPower_recursive(Matrix2x2 base, unsigned int exp) {
    if (exp == 0) {
        return identityMatrix();
    }
    if (exp % 2 == 0) {
        Matrix2x2 halfPower = quickPower_recursive(base, exp / 2);
        return multiplyMatrix(halfPower, halfPower);
    }
    else {
        return multiplyMatrix(base, quickPower_recursive(base, exp - 1));
    }
}


unsigned long long fibonacciMatrix(unsigned int n, Matrix2x2(*powerFunction)(Matrix2x2, unsigned int)) {
    if (n == 0) return 0;
    if (n == 1) return 1;

    Matrix2x2 matrix = { 1, 1, 1, 0 };
    Matrix2x2 powerOfmatrix = powerFunction(matrix, n - 1);

    Vector2 startValues = { 1, 0 };
    Vector2 resultVector = multiplyMatrixVector(powerOfmatrix, startValues);

    return resultVector._1;
}

unsigned long long fibonacciSimple_iterative(unsigned int n) {
    if (n == 0) return 0;
    unsigned long long prevPrevNum = 0;
    unsigned long long prevNum = 1;
    unsigned long long res = 1;

    for (unsigned int i = 2; i <= n; i++) {
        res = prevNum + prevPrevNum;
        prevPrevNum = prevNum;
        prevNum = res;
    }

    return res;
}

unsigned long long fibonacciOptimized_iterative(unsigned int n) {
    unsigned long long res = fibonacciMatrix(n, iteretiveQuickPower);
#ifdef DBUG
    assert(res == fibonacciSimple_iterative(n));
#endif
    return res;
}

//faster simple recurcive funct for n steps
unsigned long long fibonacciSimple_recursive(unsigned int n, unsigned long long a, unsigned long long b) { 
    if (n == 0) return a;
    if (n == 1) return b;
    return fibonacciSimple_recursive(n - 1, b, a + b);
}
//simple recurcive funct for n steps
unsigned long long fibonacciSimple_recursiveUsingPower(unsigned int n) {
    unsigned long long res = fibonacciMatrix(n, simplePower_recursive);
#ifdef DBUG
    assert(res == fibonacciSimple_iterative(n));
#endif
    return res;
}

unsigned long long fibonacciOptimized_recursive(unsigned int n) {
    unsigned long long res = fibonacciMatrix(n, quickPower_recursive);
#ifdef DBUG
    assert(res == fibonacciSimple_iterative(n));
#endif
    return res;
}