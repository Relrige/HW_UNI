#include <iostream>
#include "Functions.h"
#include <cassert>
using namespace std;

#define M_PI 3.14159265358979323846 
#define DEBUG

int main()
{
    double eps = 1e-6;

    // Testing dichotomy
    cout << endl;
    double result;
    result = dichotomy(funcSinX, -1, 1, eps);
    assert(abs(result) < eps);
    cout << "sin(x) = x on [-1,1]: x = " << result << "\n";

    result = dichotomy(funcSin, M_PI - 1, M_PI, eps);
    assert(abs(result == 0) < eps);
    cout << "Solving the equation sin(x) = 0 on [PI-1, PI]: x = " << result << "\n";

    result = dichotomy(funcLog1, 2, 3, eps);
    assert(abs(result - exp(1)) < eps);
    cout << "Solving the equation ln(x) = 1 on [2,3]: x = " << result << "\n";
    result = dichotomy(funcExp2x, 0, 2, eps);
    assert(abs(result - 0.442854) < eps);

    cout << "Solving the equation exp(x) = 2 - x on [0,2]: x = " << result << "\n";
    cout << endl;
    cout << endl;



    // Testing Simpson
    double realSin = 2;
    double realLog = 0.909543; 
    double realExp = exp(1) - 1;  
    double realSquare = 1.0 / 3; 
    double realCube = 4; 
    double realSqrt = 16.0 / 3;
    double realReciprocal = log(2);
    double realPolynomial = -8.8;
    double realTrigonometricCombo = 2;
    cout << endl;
    result = Simpson(0, M_PI, funcSin, eps);
    assert(abs(result - realSin) < eps);
    cout << "Simpson integral sin(x) on [0, PI]: " << result << endl;

    result = Simpson(2, 3, funcLog, eps);
    assert(abs(result - realLog) < eps);
    cout << "Simpson integral ln(x) on [2,3]: " << result << endl;

    result = Simpson(0, 1, funcExp, eps);
    assert(abs(result - realExp) < eps);
    cout << "Simpson integral exp(x) on [0,1]: " << result << endl;

    result = Simpson(0, 1, funcSquare, eps);
    assert(abs(result - realSquare) < eps);
    cout << "Simpson integral x^2 on [0,1]: " << result << endl;

    result = Simpson(0, 2, funcCube, eps);
    assert(abs(result - realCube) < eps);
    cout << "Simpson integral x^3 on [0,2]: " << result << endl;

    result = Simpson(0, 4, funcSqrt, eps);
    assert(abs(result - realSqrt) < eps);
    cout << "Simpson integral sqrt(x) on [0,4]: " << result << endl;

    result = Simpson(1, 2, funcReciprocal, eps);
    assert(abs(result - realReciprocal) < eps);
    cout << "Simpson integral 1/x on [1,2]: " << result << endl;

    result = Simpson(-1, 1, funcPolynomial, eps);
    assert(abs(result - realPolynomial) < eps);
    cout << "Simpson integral polynomial 3x^4 - 2x^3 + x - 5 on [-1,1]: " << result << endl;

    result = Simpson(0, M_PI, funcTrigonometricCombo, eps);
    assert(abs(result - realTrigonometricCombo) < eps);
    cout << "Simpson integral sin(x) + cos(x) on [0, PI]: " << result << endl;
}