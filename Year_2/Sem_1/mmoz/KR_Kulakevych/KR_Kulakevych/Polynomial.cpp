#include "Polynomial.h"
#include <cmath>
#include <stdexcept>

Polynomial Polynomial::construct() {
    Polynomial pol;
    pol.monomials = nullptr;
    pol.termCount = 0;
    return pol;
}

Polynomial Polynomial::construct(int count) {
    Polynomial pol;
    pol.monomials = new Monomial[count];
    pol.termCount = count;
    return pol;
}

Polynomial Polynomial::construct(Monomial* initMonomials, int count) {
    Polynomial pol;
    pol.monomials = new Monomial[count];
    for (int i = 0; i < count; ++i) {
        pol.monomials[i] = initMonomials[i];
    }
    pol.termCount = count;
    pol.reduce();
    pol.sortByPowersAcessing();
    return pol;
}

void Polynomial::destructor() {
    delete[] this->monomials;
    this->monomials = nullptr;
}

void Polynomial::sortByPowersAcessing() {
    for (int i = 0; i < termCount - 1; ++i) {
        for (int j = i + 1; j < termCount; ++j) {
            if (monomials[i].power > monomials[j].power) {
                swap(monomials[i], monomials[j]);
            }
        }
    }
}

void Polynomial::reduce() {
    sortByPowersAcessing();

    int newLength = 0;
    for (int i = 0; i < termCount; ++i) {
        if (i < termCount - 1 && monomials[i].power == monomials[i + 1].power) {
            monomials[i + 1] = monomials[i] + monomials[i + 1];
            monomials[i].power = -1;
        }
        else {
            monomials[newLength++] = monomials[i];
        }
    }
    if (newLength == termCount)
        return;
    Monomial* simplified = new Monomial[newLength];

    for (int i = 0; i < newLength; ++i) {
        simplified[i] = monomials[i];
    }
    delete[] monomials;
    monomials = simplified;
    termCount = newLength;
}

double Polynomial::value(double x) const {
    double result = 0.0;
    for (int i = 0; i < termCount; ++i) {
        result += (monomials[i].coefficient.val() * pow(x, monomials[i].power));
    }
    return result;
}

Polynomial operator+(const Polynomial& a, const Polynomial& b) {
    int newLength = a.termCount + b.termCount;
    Monomial* newMonomials = new Monomial[newLength];
    for (int i = 0; i < a.termCount; ++i) {
        newMonomials[i] = a.monomials[i];
    }
    for (int i = 0; i < b.termCount; ++i) {
        newMonomials[a.termCount + i] = b.monomials[i];
    }
    Polynomial result = Polynomial::construct(newMonomials, newLength);
    delete[] newMonomials;
    return result;
}

ostream& operator<<(ostream& os, const Polynomial& poly) {
    bool first = true;
    for (int i = 0; i < poly.termCount; ++i) {
        if (poly.monomials[i].coefficient.denominator != 0) {
            if (!first) os << " + ";
            os << poly.monomials[i];
            first = false;
        }
    }
    if (first) os << "0";
    return os;
}