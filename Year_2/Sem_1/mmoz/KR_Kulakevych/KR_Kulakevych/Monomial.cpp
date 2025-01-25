#include "Monomial.h"
#include <cassert>


Monomial Monomial::construct() {
    Monomial monomial;
    monomial.coefficient=Fraction::construct(0);
    monomial.power= 1;
    return monomial;
}
Monomial Monomial::construct(int coef, int power) {
    if (power <= 0) {
        throw invalid_argument("Power cannot be zero or less.");
    }
    Monomial mono;
    mono.coefficient = Fraction::construct(coef);
    mono.power = power;
    mono.reduce();
    return mono;
}
Monomial Monomial::construct(Fraction coef, int power) {
    if (power <= 0) {
        throw invalid_argument("Power cannot be zero or less.");
    }
    Monomial mono;
    mono.coefficient = coef;
    mono.power = power;
    mono.reduce();
    return mono;
}

void Monomial::reduce() {
    coefficient.reduce();
}

Monomial operator+(const Monomial& m1, const Monomial& m2) {
    if (m1.power != m2.power) {
        throw invalid_argument("Cannot add monomials with different powers.");
    }
    return Monomial::construct(m1.coefficient + m2.coefficient, m1.power);
}

Monomial operator-(const Monomial& m1, const Monomial& m2) {
    if (m1.power != m2.power) {
        throw invalid_argument("Cannot subtract monomials with different powers.");
    }
    return Monomial::construct(m1.coefficient - m2.coefficient, m1.power);
}

Monomial operator*(const Monomial& m1, const Monomial& m2) {
    return Monomial::construct(m1.coefficient * m2.coefficient, m1.power + m2.power);
}

Monomial operator/(const Monomial& m1, const Monomial& m2) {
    if (m2.coefficient.numerator == 0) {
        throw invalid_argument("Cannot divide by a monomial with a zero coefficient.");
    }
    return Monomial::construct(m1.coefficient / m2.coefficient, m1.power - m2.power);
}



double Monomial::val(double x) const {
    double valueOfMethod = coefficient.val() * pow(x, power);
#ifdef DEBUG
    auto lambdaCalculateMonomial = [](const Monomial& monomial, double x) {
        return monomial.coefficient.val() * pow(x, monomial.power);
    };
    double valueOfLambda = lambdaCalculateMonomial(*this, x);
    assert(abs(valueOfMethod - valueOfLambda) < 1e-9);
#endif
    return valueOfMethod;
}


ostream& operator<<(ostream& os, const Monomial& monomial) {
    const Fraction& fracCoef = monomial.coefficient;
    if (fracCoef.numerator == 0) { 
        os << "0";
    }
    else {
        if (fracCoef.denominator == 1) { 
            os << fracCoef.numerator;
        }
        else {
            os << fracCoef.numerator << "/" << fracCoef.denominator;
        }

        if (monomial.power > 1) {
            os << " * x^" << monomial.power;
        }
        else if (monomial.power == 1) {
            os << " * x";
        }
    }
    return os;
}