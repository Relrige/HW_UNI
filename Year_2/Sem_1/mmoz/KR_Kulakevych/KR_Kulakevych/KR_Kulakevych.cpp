#include <iostream>
#include "Functions.h"
#include <cassert>
using namespace std;

void testOutputMonomialTask1() {
    cout << "----Task1" << endl;
    Fraction fraction = Fraction::construct(3, 4);
    Monomial monomial = Monomial::construct(fraction, 2);
    printMonomial(monomial);
    
    Fraction fraction2 = Fraction::construct(0, 1);
    Monomial monomial2 = Monomial::construct(fraction2, 5);
    printMonomial(monomial2);

    Fraction fraction3 = Fraction::construct(5, 2);
    Monomial monomial3 = Monomial::construct(fraction3, 12);
    printMonomial(monomial3);

    Fraction fraction4 = Fraction::construct(-14, 7);
    Monomial monomial4 = Monomial::construct(fraction4, 3);
    printMonomial(monomial4);

    Fraction fraction5 = Fraction::construct(4, 8);
    Monomial monomial5 = Monomial::construct(fraction5, 1);
    printMonomial(monomial5);
}
void testReducingMonomialTask2(int testCount) {
    cout << endl;
    cout << "----Task2" << endl;

    for (int i = 0; i < testCount; i++) {
        Monomial monomial = createRandomMonomial();
        cout << "Before reduction: ";
#ifdef DEBUG
        double originalValue = monomial.coefficient.val();
#endif
        printMonomial(monomial);
        monomial.reduce();
        cout << "After reduction: ";
        printMonomial(monomial);
#ifdef DEBUG
        double reducedValue = monomial.coefficient.val();
        assert(abs(originalValue - reducedValue) < 1e-8);
#endif
    }
}
void testCalculatingValueMonomialTask3(int countTest) {
    cout << endl;
    cout << "----Task3" << endl;
    const int numMonomials = countTest;
    Monomial* MonoArr = new Monomial[numMonomials];
    for (int i = 0; i < numMonomials; ++i) {
        MonoArr[i] = createRandomMonomial();
    }

    for (int i = 0; i < numMonomials; ++i) {
        double x = rand() % 9 + 1;

        double valueOfMethod = MonoArr[i].val(x);

        printMonomial(MonoArr[i]);
        cout << "The value of x = " << x << " on (method): " << valueOfMethod << endl;
    }
}
void testMonomialOutputTask4(int testCount) {
    cout << endl;
    cout << "---Task4" << endl;
    for (int i = 0; i < testCount; ++i) {
        Monomial monomial1 = createRandomMonomial();
        cout << "Monomial: " << monomial1 << endl;
    }
}
void randomFractionTestTask5(int testCount) {
    cout << endl;
    cout << "-----Task5----" << endl;
    for (int i = 0; i < testCount; ++i) {
        Fraction fract1 = createRandomFraction();
        Fraction fract2 = createRandomFraction();
        Fraction fract3 = fract1 + fract2;

        assert(abs((fract1.val()+ fract2.val())-(fract3.val()))<0.0000001);
        
        cout << "  frac1: " << fract1 << "  frac2: " << fract2 << endl;
        cout << "Result:  frac1 + frac2 = " << fract3 << endl;
    }
}
void polynomialTask6(int testCount) {
    cout << endl;
    cout << "---Task6" << endl;

    int mono_arr_length = testCount;
    Monomial* MonoArr = new Monomial[mono_arr_length];
    for (int i = 0; i < mono_arr_length; i++) { 
        MonoArr[i] = createRandomMonomial();
    }

    Polynomial poly6 = Polynomial::construct(MonoArr, mono_arr_length);
    cout << "Polynomial: ";
    printPolynomial(poly6);

    double x2 = rand() % 4 + 1;
    double expected_value = 0.0;
    for (int i = 0; i < mono_arr_length; i++) {
        expected_value += MonoArr[i].coefficient.val() * pow(x2, MonoArr[i].power);
    }

    double result_value = poly6.value(x2);
    assert(abs(result_value - expected_value) < 1e-7);
    cout << "Value of Polynomial at x = " << x2 << ": " << result_value << endl;
    poly6.destructor();
    delete[] MonoArr;
}

void task7(int testCount) {
    cout << endl;
    cout << "---Task7" << endl;

    int mono_arr_length2 = testCount;
    Monomial* MonoArr2 = new Monomial[mono_arr_length2];

    for (int i = 0; i < testCount; i++) {
        MonoArr2[i] = createRandomMonomial();
    }
    Polynomial poly7 = Polynomial::construct(mono_arr_length2);
    for (int i = 0; i < testCount; i++) {
        poly7.monomials[i] = MonoArr2[i];
    }
    cout << "Polynomial before reduce: ";
    printPolynomial(poly7);
    
    double x = rand() % 5 + 1;
    double expectedValueBeforeReduce = 0.0;
    for (int i = 0; i < mono_arr_length2; i++) {
        expectedValueBeforeReduce += MonoArr2[i].coefficient.val() * pow(x, MonoArr2[i].power);
    }
    double actualValueBeforeReduce = poly7.value(x);

    assert(abs(actualValueBeforeReduce - expectedValueBeforeReduce) < 1e-8);

    poly7.reduce();
    cout << "Polynomial after reduce: ";
    printPolynomial(poly7);

    double expectedValueAfterReduce = poly7.value(x);
    double actualValueAfterReduce = poly7.value(x);

    assert(abs(actualValueAfterReduce - expectedValueAfterReduce) < 1e-8);
    poly7.destructor();
    delete[] MonoArr2;
}

void reducingPolynomialtask8(int testCount) {
    cout << endl;
    cout << "---Task8" << endl;
    int mono_arr_length = testCount;
    Monomial* MonoArr2 = new Monomial[mono_arr_length];

    for (int i = 0; i < testCount; i++) {
        MonoArr2[i]= createRandomMonomial();
    }

    Polynomial poly8 = Polynomial::construct(mono_arr_length);
    for (int i = 0; i < mono_arr_length; ++i) {
        poly8.monomials[i] = MonoArr2[i];
    }
    cout << "Polynomial before sorting: ";
    printPolynomial(poly8);

    poly8.sortByPowersAcessing();
    poly8.reduce();

    cout << "Polynomial after sorting and reducing: ";
    printPolynomial(poly8);

    double x = rand() % 5 + 1;
    double result8 = poly8.value(x);
    cout << "Value of Polynomial at x = " << x << ": " << result8 << endl;

    for (int i = 1; i < poly8.termCount; i++) {
        assert(poly8.monomials[i - 1].power <= poly8.monomials[i].power);
    }
    poly8.destructor();
    delete[] MonoArr2;
}

void testPolynomialAdditionTask9(int count) {
    cout << endl;
    cout << "---Task9" << endl;

    Monomial* terms1 = new Monomial[count];
    for (int i = 0; i < count; i++) {
        terms1[i] = createRandomMonomial();
    }
    Polynomial P = Polynomial::construct(terms1, count);

    Monomial* terms2 = new Monomial[count];
    for (int i = 0; i < count; i++) {
        terms2[i] = createRandomMonomial();
    }
    Polynomial Q = Polynomial::construct(terms2, count);

    Polynomial Respoly = P + Q;

    cout << "P(x): ";
    printPolynomial(P);
    cout << "Q(x): ";
    printPolynomial(Q);
    cout << "Res(x) = P(x) + Q(x): ";
    printPolynomial(Respoly);

    double x = rand() % 4 + 1;
    double expectedValue = P.value(x) + Q.value(x);
    double resultValue = Respoly.value(x);
    cout << "P(" << x << ") + Q(" << x << ") = " << expectedValue << endl;
    cout << "Res(" << x << ") = " << resultValue << endl;

    assert(abs(resultValue - expectedValue) < 1e-8);
    P.destructor();
    Q.destructor();
    Respoly.destructor();

    delete[] terms1;
    delete[] terms2;
}
void testPolynomialOutputByAdditionTask10(int testCount) {
    cout << endl;
    cout << "--- Task10---" << endl;

    Monomial* terms1 = new Monomial[testCount];
    for (int i = 0; i < testCount; i++) {
        terms1[i] = createRandomMonomial();
    }
    Polynomial poly1 = Polynomial::construct(terms1, testCount);

    Monomial* terms2 = new Monomial[testCount];
    for (int i = 0; i < testCount; i++) {
        terms2[i] = createRandomMonomial();
    }
    Polynomial poly2 = Polynomial::construct(terms2, testCount);

    Polynomial sum = poly1 + poly2;

    cout << "Polynomial 1: " << poly1 << endl;
    cout << "Polynomial 2: " << poly2 << endl;
    cout << "Sum: " << sum << endl;
}

int main()
{
    srand(static_cast<unsigned>(time(0)));

    //1
    testOutputMonomialTask1();


    //2
    testReducingMonomialTask2(10);
    

    //3
    testCalculatingValueMonomialTask3(5);

    //4
    testMonomialOutputTask4(10);


    //5
    randomFractionTestTask5(5);


    //6
    polynomialTask6(5);

    //7
    task7(6);

    //8
    reducingPolynomialtask8(6);

    //9
    testPolynomialAdditionTask9(3);
    
    //10
    testPolynomialOutputByAdditionTask10(4);

    return 0;
}
