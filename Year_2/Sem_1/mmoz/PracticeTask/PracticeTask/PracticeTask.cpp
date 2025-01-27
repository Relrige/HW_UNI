#include <iostream>
using namespace std;


unsigned long long S(int x) {
	unsigned long long sum = 0;
	for (int k = 1; k <= x; k++) {
		unsigned long long term = pow(x, pow(3, k));
		sum += term;
	}
	return sum;
}

int calculateAn(int n) {
    if (n == 0) return 3;
    if (n == 1) return 1;
    if (n == 2) return 2;

    int a0 = 3, a1 = 1, a2 = 2, an;

    for (int i = 3; i <= n; i++) {
        an = 3 * a2 - 2 * a0;
        a0 = a1;
        a1 = a2;
        a2 = an;
    }

    return an;
}
void order1(double& a, double& b) {
	a = min(a, b);
	b = max(a, b); 
}
void order2(double& a, double& b) {
	a = (a > b) ? (a - b) : a;
	b = (a > b) ? (b + a) : b;
	a = (a > b) ? (b - a) : a;
}
void order3(double& a, double& b) {
	double diff = a - b;
	a -= diff * (diff > 0);
	b += diff * (diff > 0);
}
void order4(double& a, double& b) {
	double temp = a;
	a = (a < b) * a + (b <= a) * b;
	b = (temp < b) * b + (b<=temp) * temp;
}

void f(int k) { k--; }; 
struct S { int _x : 4; int _y : 4; };
int main()
{
	cout << "S(2) = " << S(2) << endl;
	//∑x^(k^2)
	// x(1)=1
	//x(2)=2^4 = 16 + 1
	//x(3) 3^9 = 19683+16 +1

	//2
	int n = 15;
	int result = calculateAn(n);
	cout << "a(" << n << ") = " << result << endl;

	//3
	double a=5.32132;
	double b=5.32232;
	order2(a, b);
	cout << "a: " << a << endl;
	cout << "b: " << b << endl;

}
