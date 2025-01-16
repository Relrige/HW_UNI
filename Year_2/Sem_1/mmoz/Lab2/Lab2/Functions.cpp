#include <iostream>
#include <cmath>
#include <cassert>
#include <cmath>
#include "Functions.h"
#include <vector>
using namespace std;
const double TWO_PI = 6.283185307179586476925286766559;
const double PI = 3.141592653589793238462643383279;
const double E = 2.718281828459045;
const double EPSILON = 0.00000000000001;

bool equalsWithPrecision(double a, double b, double precision) {
	if (isnan(a) || isnan(b))
		return isnan(a) && isnan(b);
	if (isinf(a) || isinf(b))
		return isinf(a) && isinf(b);
	return abs(a / b - 1) < precision;
}

double reduce_angle(double x) {
	x = fmod(x, TWO_PI);
	if (x > TWO_PI / 2) {
		x -= TWO_PI;
	}
	else if (x < -TWO_PI / 2) {
		x += TWO_PI;
	}
	return x;
}
int factorial(int n)
{
	int res = 1;
	for (int i = 2; i <= n; i++)
		res *= i;
	return res;
}
double my_sin(double x) {
	x = reduce_angle(x);
	double term = x;
	double prev(0);
	double sum = term;
	int n = 1;

	while (abs(sum - prev) > EPSILON) {
		prev = sum;
		term *= -x * x / ((2 * n) * (2 * n + 1));
		sum += term;
		++n;
	}
#ifndef NDEBUG
	assert(equalsWithPrecision(sum, sin(x), 0.000000001));
#endif
	return sum;
}

double my_cos(double x) {
	x = reduce_angle(x);
	double term = 1.0;
	double prev(0);
	double sum = term;
	int n = 1;

	while (abs(sum - prev) > EPSILON) {
		prev = sum;
		term *= -x * x / ((2 * n - 1) * (2 * n));
		sum += term;
		++n;
	}
#ifndef NDEBUG
	assert(equalsWithPrecision(sum, cos(x), 0.000000001));
#endif
	return sum;
}

double my_tan(double x) {
	x = reduce_angle(x);  
	double sin = my_sin(x);
	double cos = my_cos(x);
	if (cos == 0) {
		if (sin > 0) {
			return std::numeric_limits<double>::infinity(); 
		}
		else if (sin < 0) {
			return -std::numeric_limits<double>::infinity(); 
		}
		else {
			return 0.0; 
		}
	}
#ifndef NDEBUG
	assert(equalsWithPrecision(sin / cos, tan(x), 0.000000001));
#endif
	return sin / cos;
}

//exp
double myExp(double x) {
	double prev(0);
	double sum = 1;
	double d = 1;
	double n(1);

	while (abs(sum - prev) > EPSILON) {
		prev = sum;
		d *= x / n;
		sum += d;
		++n;
	}
#ifndef NDEBUG
	assert(equalsWithPrecision(sum,exp(x), 0.0001));
#endif
	return sum;
}
double myQuickPow(double x, int n) {
	if (n < 1)return -1;
	double orgX(x);
	int i(2);
	for (i; i < n; i *= 2) {
		x *= x;
	}
	i /= 2;
	for (int y = 0; y < n - i; y++) {
		x *= orgX;
	}
	return x;
}
//exp fast
double myExp2(double x) {
	if (x >= 0) {
		int intPart = static_cast<int>(x);
		double p = x - intPart;
		return myQuickPow(E, intPart) * myExp(p);
	}
	else {
		return 1 / myExp2(-x);
	}
}
double myQuickPowSteps(double x, int n) {
	int count(0);
	if (n < 1)return -1;
	double orgX(x);
	int steps(0);
	int i(2);
	for (i; i < n; i *= 2) {
		x *= x;
		steps++;
	}
	i /= 2;
	for (int y = 0; y < n - i; y++) {
		x *= orgX;
		steps++;
	}
	return steps;
}

//exp steps
double myExpSteps(double x) {
	double prev(0);
	double sum = 1;
	double d = 1;
	double n(1);
	while (abs(sum - prev) > EPSILON) {
		prev = sum;
		d *= x / n;
		sum += d;
		++n;
	}
	return n - 1;
}
//exp fast steps
int myExp2Steps(double x) {
	if (x >= 0) {
		int i = x;
		double p = x - i;
		return myQuickPowSteps(E, i) + myExpSteps(p);
	}
	else {
		return myExp2Steps(-x) + 1;
	}
}