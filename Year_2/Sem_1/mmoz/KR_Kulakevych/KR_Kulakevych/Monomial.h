#ifndef MONOMIAL_H
#define MONOMIAL_H
#include "Fractions.h"
#include <stdexcept>
#include <cmath>
#include <iostream>

using namespace std;

struct Monomial {
    int power;
    Fraction coefficient;
    
    static Monomial construct();
    static Monomial construct(int coeff, int pow = 1);
    static Monomial construct(Fraction coeff, int pow = 1);

    double val(double x) const;
    void reduce();

};
Monomial operator+(const Monomial& m1, const Monomial& m2);
Monomial operator-(const Monomial& m1, const Monomial& m2);
Monomial operator*(const Monomial& m1, const Monomial& m2);
Monomial operator/(const Monomial& m1, const Monomial& m2);
ostream& operator<<(ostream& os, const Monomial& monomial);
#endif