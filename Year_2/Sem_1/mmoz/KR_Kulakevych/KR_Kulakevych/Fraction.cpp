#include "Fractions.h"

int gcd(int numb1, int numb2) {
    while (numb2 != 0) {
        int temp = numb2;
        numb2 = numb1 % numb2;
        numb1 = temp;
    }
    return numb1;
}

void Fraction::reduce() {
    int gcd2 = gcd(numerator, denominator);
    if (gcd2 < 0) {
        gcd2 *= -1;
    }
    numerator /= gcd2;
    denominator /= gcd2;
}

Fraction Fraction::construct() {
    Fraction frac;
    frac.numerator = 0;
    frac.denominator = 1;
    return frac;
}
Fraction Fraction::construct(int num, int den) {
    if (den <= 0) {
        throw invalid_argument("Denominator cannot be zero or less.");
    }
    Fraction frac;
    frac.numerator = num;
    frac.denominator = den;
    frac.reduce();
    return frac;
}

double Fraction::val() const {
    return static_cast<double>(numerator) / denominator;
}

ostream& operator<<(ostream& os, const Fraction& fraction) {
    os << fraction.numerator << '/' << fraction.denominator;
    return os;
}

Fraction operator+(const Fraction& fraction1, const Fraction& fraction2) {
    int denominator2 = fraction1.denominator * fraction2.denominator;
    int numerator2 = fraction1.numerator * fraction2.denominator + fraction2.numerator * fraction1.denominator;
    return Fraction::construct(numerator2, denominator2);
}

Fraction operator-(const Fraction& fraction1, const Fraction& fraction2) {
    int denominator2 = fraction1.denominator * fraction2.denominator;
    int numerator2 = fraction1.numerator * fraction2.denominator - fraction2.numerator * fraction1.denominator;
    return Fraction::construct(numerator2, denominator2);
}

Fraction operator*(const Fraction& fraction1, const Fraction& fraction2) {
    int numerator2 = fraction1.numerator * fraction2.numerator;
    int denominator2 = fraction1.denominator * fraction2.denominator;
    return Fraction::construct(numerator2, denominator2);
}

Fraction operator/(const Fraction& fraction1, const Fraction& fraction2) {
    if (fraction2.numerator == 0) {
        throw invalid_argument("Cannot divide by zero.");
    }
    int numerator2 = fraction1.numerator * fraction2.denominator;
    int denominator2 = fraction1.denominator * fraction2.numerator;
    return Fraction::construct(numerator2, denominator2);
}

Fraction& Fraction::operator=(const Fraction& other) {
    if (this != &other) {
        numerator = other.numerator;
        denominator = other.denominator;
    }
    return *this;
}