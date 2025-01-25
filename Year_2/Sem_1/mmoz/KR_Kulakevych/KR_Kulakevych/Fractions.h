#ifndef FRACTIONS_H
#define FRACTIONS_H

#include <stdexcept>
#include <iostream>

using namespace std;

struct Fraction {
    int numerator;
    int denominator;

    void reduce();

    static Fraction construct();
    static Fraction construct(int num, int den = 1);

    double val() const;


    Fraction& operator=(const Fraction& other);
};
Fraction operator+(const Fraction& fraction1, const Fraction& fraction2);
Fraction operator-(const Fraction& fraction1, const Fraction& fraction2);
Fraction operator*(const Fraction& fraction1, const Fraction& fraction2);
Fraction operator/(const Fraction& fraction1, const Fraction& fraction2);
ostream& operator<<(ostream& os, const Fraction& fraction);
#endif