#ifndef POLYNOMIAL_H
#define POLYNOMIAL_H

#include "Monomial.h"
#include <iostream>

struct Polynomial {
    Monomial* monomials;
    int termCount;

    static Polynomial construct();
    static Polynomial construct(int count);
    static Polynomial construct(Monomial* initMonomials, int count);

    void destructor();

    void sortByPowersAcessing();

    void reduce();

    double value(double x) const;

};
Polynomial operator+(const Polynomial& a, const Polynomial& b);

ostream& operator<<(ostream& os, const Polynomial& poly);
#endif
