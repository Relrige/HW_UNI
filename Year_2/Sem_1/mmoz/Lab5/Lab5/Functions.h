#include <functional>
using namespace std;



double dichotomy(double(* const f)(double), double a, double b, double eps = 0.00000001);
double funcSinX(double x);
double funcSin(double x);
double funcLog1(double x);
double funcExp2x(double x);


double Simpson(const double a, const double b, double(* const f)(double), const double eps = 0.00000001);
double funcLog(double x);
double funcExp(double x);



double funcSquare(double x);
double funcCube(double x);
double funcSqrt(double x);
double funcReciprocal(double x);
double funcPolynomial(double x);
double funcTrigonometricCombo(double x);