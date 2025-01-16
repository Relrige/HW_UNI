#include <iostream>
#include <cmath>
#include "Functions.h"
using namespace std;
const double TWO_PI = 6.283185307179586476925286766559;

int main() {
    cout.precision(13);
    double x = 1000; 
    std::cout << "sin(" << x << ") = " << my_sin(x) << std::endl;
    std::cout << "std::sin(" << x << ") = " << std::sin(x) << std::endl; 


	cout << " " << endl;
    cout << "------------Testing exp" << endl;

    for (double i = 0.4; i < 110; i*=2) {
        double myExpResult = myExp(i);
        double expResult = exp(i);

        cout << "For x = " << i << ":" << endl;
        cout << "myExp(x) = " << myExpResult << endl;
        cout << "exp(x)   = " << expResult << endl;
        cout << "Difference = " << fabs(myExpResult - expResult) << endl;
        cout << " " << endl;
    }
    for (double i = -0.4; i > -15; i*=2) {
        double myExpResult = myExp(i);
        double expResult = exp(i);

        cout << "For x = " << i << ":" << endl;
        cout << "myExp(x) = " << myExpResult << endl;
        cout << "exp(x)   = " << expResult << endl;
        cout << "Difference = " << fabs(myExpResult - expResult) << endl;
        cout << " " << endl;
    }
	cout << " " << endl;
    cout << "------------Testing sin" << endl;
	cout << " " << endl;
    for (double i = 0.5; i < 1025; i *= 2) {
        double mySinResult = my_sin(i);
        double sinResult = sin(i);

        cout << "For x = " << i << ":" << endl;
        cout << "mySin(x) = " << mySinResult << endl;
        cout << "sin(x)   = " << sinResult << endl;
        cout << "Difference = " << fabs(mySinResult - sinResult) << endl;
        cout << " " << endl;
    }
    cout << " " << endl;
    cout << " " << endl;
    for (double i = -0.5; i > -1025; i *= 2) {
        double mySinResult = my_sin(i);
        double sinResult = sin(i);

        cout << "For x = " << i << ":" << endl;
        cout << "mySin(x) = " << mySinResult << endl;
        cout << "sin(x)   = " << sinResult << endl;
        cout << "Difference = " << fabs(mySinResult - sinResult) << endl;
        cout << " " << endl;
    }
    cout << " " << endl;
    cout << "------------Testing cos" << endl;
    cout << " " << endl;
    for (double i = 0.5; i < 1025; i *= 2) {
        double myCosResult = my_cos(i);
        double cosResult = cos(i);

        cout << "For x = " << i << ":" << endl;
        cout << "myCos(x) = " << myCosResult << endl;
        cout << "cos(x)   = " << cosResult << endl;
        cout << "Difference = " << fabs(myCosResult - cosResult) << endl;
        cout << " " << endl;
    }
    cout << " " << endl;
    cout << " " << endl;
    for (double i = -0.5; i > -1025; i *= 2) {
        double myCosResult = my_cos(i);
        double cosResult = cos(i);

        cout << "For x = " << i << ":" << endl;
        cout << "myCos(x) = " << myCosResult << endl;
        cout << "cos(x)   = " << cosResult << endl;
        cout << "Difference = " << fabs(myCosResult - cosResult) << endl;
        cout << " " << endl;
    }
    cout << " " << endl;
    cout << "------------Testing tan" << endl;
    cout << " " << endl;
    for (double i = 1; i < 1025; i *= 2) {
        double myTanResult = my_tan(i);
        double tanResult = tan(i);

        cout << "For x = " << i << ":" << endl;
        cout << "myTan(x) = " << myTanResult << endl;
        cout << "tan(x)   = " << tanResult << endl;
        cout << "Difference = " << fabs(myTanResult - tanResult) << endl;
        cout << " " << endl;
    }
    cout << " " << endl;
    cout << " " << endl;
    for (double i = -1; i > -1025; i *= 2) {
        double myTanResult = my_tan(i);
        double tanResult = tan(i);

        cout << "For x = " << i << ":" << endl;
        cout << "myTan(x) = " << myTanResult << endl;
        cout << "tan(x)   = " << tanResult << endl;
        cout << "Difference = " << fabs(myTanResult - tanResult) << endl;
        cout << " " << endl;
    }
    cout << " " << endl;
    cout << "------------Testing exp Steps" << endl;
    for (double i = 1; i < 513; i *= 2) {
        double myExpResult = myExpSteps(i);
        double exp2Result = myExp2Steps(i);

        cout << "For x = " << i << ":" << endl;
        cout << "myExp(x) = " << myExpResult << endl;
        cout << "exp2(x)   = " << exp2Result << endl;
        cout << "Difference = " << fabs(myExpResult - exp2Result) << endl;
        cout << " " << endl;
    }
    for (double i = -1; i > -513; i *= 2) {
        double myExpResult = myExpSteps(i);
        double exp2Result = myExp2Steps(i);

        cout << "For x = " << i << ":" << endl;
        cout << "myExp(x) = " << myExpResult << endl;
        cout << "exp2(x)   = " << exp2Result << endl;
        cout << "Difference = " << fabs(myExpResult - exp2Result) << endl;
        cout << " " << endl;
    }
    cout << " " << endl;


    return 0;
}
