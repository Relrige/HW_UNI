#include <iostream>
#include <cmath>
#include <functional>
using namespace std;

double dichotomy(double(* const f)(double), double a, double b, double eps) {
	if (f(a) * f(b) > 0) {
		cerr << "Error: f(a) and f(b) must have opposite signs or one of them must be zero." << endl;
		return NAN;
	}

	while ((b - a) / 2.0 > eps) {
		double midOfTwo = (a + b) / 2.0;
		if (f(midOfTwo) == 0) {
			return midOfTwo;
		}
		else if (f(a) * f(midOfTwo) < 0) {
			b = midOfTwo;
		}
		else {
			a = midOfTwo;
		}
	}
	return (a + b) / 2.0;
}
double funcSinX(double x) { return sin(x) - x; }
double funcSin(double x) { return sin(x); }
double funcLog1(double x) { return log(x) - 1; }
double funcExp2x(double x) { return exp(x) - (2 - x); }

//recurent Simpson
double Simpson(const double a, const double b,
	double(* const f)(double), const double eps) {
	int n = 2;
	double h = (b - a) / 2.0;
	double prev = 0;
	double part1 = h * (f(a) + f(b));
	double part2 = 0;
	double part3 = 4.0 * h * f(a + h); 
	double curr = part1 + part2 + part3; 
	do {
		prev = curr;
		n *= 2.0;
		h /= 2.0;
		part1 /= 2.0; 
		part2 = part2 /2.0 + part3 /4.0; 

		part3 = 0; 
		int i = 1;
		do {
			part3 = part3 + f(a + i * h); 
			i += 2;
		} while (i <= n);
		part3 = 4.0 * h * part3; 

		curr = part1 + part2 + part3; 
	} while (fabs(curr - prev) > eps);
	return curr / 3.0;
}

double funcLog(double x) { return log(x); }
double funcExp(double x) { return exp(x); }

double funcSquare(double x) { return x * x; }
double funcCube(double x) { return x * x * x; }
double funcSqrt(double x) { return sqrt(x); }
double funcReciprocal(double x) { return 1 / x; }
double funcPolynomial(double x) { return 3 * pow(x, 4) - 2 * pow(x, 3) + x - 5; }
double funcTrigonometricCombo(double x) {return sin(x) + cos(x);}
