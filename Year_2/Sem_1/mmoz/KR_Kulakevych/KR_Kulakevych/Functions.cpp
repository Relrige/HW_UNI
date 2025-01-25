#include "Functions.h"
#include <iostream>
#include <cstdlib>
#include <ctime>
#include <numeric>

using namespace std;

void printMonomial(const Monomial& monomial) {
    const Fraction& coef = monomial.coefficient;
    
    if (coef.numerator == 0) {
        cout << "0" << endl;
        return;
    }
    if (coef.denominator == 1) {
        cout << coef.numerator;
    }
    else {
        cout << coef.numerator << "/" << coef.denominator;
    }

    if (monomial.power > 1) {
        cout << " * x^" << monomial.power;
    }
    else if (monomial.power == 1) {
        cout << " * x";
    }

    cout << endl;
}
void printPolynomial(const Polynomial& polynomial) {
    bool first = true;

    for (int i = 0; i < polynomial.termCount; ++i) {
        if (polynomial.monomials[i].coefficient.numerator == 0) { 
            continue;
        }

        if (!first) {
            cout << " + ";
        }

        cout << polynomial.monomials[i].coefficient;

        if (polynomial.monomials[i].power > 0) {
            cout << "x";
            if (polynomial.monomials[i].power > 1) {
                cout << "^" << polynomial.monomials[i].power;
            }
        }

        first = false;
    }

    if (first) {
        cout << "0";
    }

    cout << endl;
}

Monomial createRandomMonomial() {
    int pow = rand() % 10+1;
    Fraction coeff= createRandomFraction();
    return Monomial::construct(coeff, pow);
}
Fraction createRandomFraction() {
    int numer1 = rand() % 40 - 20;
    int denom1 = rand() % 40 + 2;
    return Fraction::construct(numer1, denom1);
}
