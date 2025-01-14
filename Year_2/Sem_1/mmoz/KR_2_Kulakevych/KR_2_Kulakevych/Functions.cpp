#include <cmath>
#include <cassert>
#include <iostream>
#include "Structurs.h"


//n = ~n?n:0xFFFFFFFF; simplifes just to n=n
//0xFFFFFFFF is -1 and ~ for 0xFFFFFFFF is false than n=0xFFFFFFFF, n is same
void task1(unsigned int& n) {
	n = n;
}

//Not recurent sum for task 2
unsigned long long testOfTask2(const size_t n) {
	unsigned long long res(0);
	for (unsigned long i = 1; i <= n; ++i) {
		res += pow(2, n - i) * (i * i);
	}
	return res;
}

//Sn = sum(from i=1 to n)(2^(n-i)*i^2)
//S1 = 1
//S1= 2^n-1*1^2
//S2 2^1(2^(n-2)*1^2) + (1*n^2)
//Sn = 2*S(n-1)+n^2
unsigned long long task2(const size_t n) {
	// n must be bigger than 0
	if (n == 0) {
		throw 0;
	}
	unsigned long long res = 1;
	for (unsigned long i = 2; i <= n; ++i) {
		res = 2 * res + (i * i);
	}

#ifdef _DEBUG
	//Checking for range up to 50 if right
	if (n <= 50)
		assert(res == testOfTask2(n));
#endif
	return res;
}

//shift in a cycle by one bit and check if there is sequence of len 1
int task3(const unsigned int word, const size_t len) {
	//length must be bigger than 0
	if (len == 0) throw 0;

	size_t counter(0);

	for (size_t i = 0; i < 32 - (len - 1); ++i) {
		unsigned int currBit = word >> i;
		size_t currCount(0);
		while (currBit % 2 == 1) {
			++currCount;
			if (currCount == len)break;
			currBit >>= 1;
		}
		if (currCount == len)++counter;
	}
	return counter;
}

//Swap enen and odd bits
int task4(const int input) {
	//shift by one bit to left and make 0 all unpair bits
	int a = input << 1 & 0xAAAAAAAA;
	//shift by one bit to right and make 0 all pair bits
	int b = input >> 1 & 0x55555555;
	//combine the resulting sequences
	return b | a;
}

//Replacing the first hexadecimal digit with the second
int task5(const int word) {
	//shift by one byte to left and make 0 all unpair bytes
	int a = word << 4 & 0xF0F0F0F0;
	//shift by one byte to right and make 0 all pair bytes
	int b = word >> 4 & 0x0F0F0F0F;
	return a | b;
}

//
double task6(double** points, const size_t length, const char coord) {
	if (length == 0)throw 0;
	double point1(0);
	double point2(0);

	int posOfCordinate = 0;
	if (coord == 'x')posOfCordinate = 0;
	else if (coord == 'y')posOfCordinate = 1;
	else if (coord == 'z')posOfCordinate = 2;
	else throw coord;

	for (size_t i = 0; i < length; ++i) {
		point1 += points[i][3] * points[i][posOfCordinate];
		point2 += points[i][3];
	}
	return point1 / point2;
}


double task7(PointWithMass* points, const size_t length, const char coord) {
	if (length == 0)throw 0;
	double point1(0);
	double point2(0);
	for (size_t i = 0; i < length; ++i) {
		point2 += points[i].mass;
	}
	if (coord == 'x')
		for (size_t i = 0; i < length; ++i)
			point1 += points[i].mass * points[i].x;
	else if (coord == 'y')
		for (size_t i = 0; i < length; ++i)
			point1 += points[i].mass * points[i].y;
	else if (coord == 'z')
		for (size_t i = 0; i < length; ++i)
			point1 += points[i].mass * points[i].z;
	else throw coord;
	return point1 / point2;
}

double** createPoints(size_t n) {
	double** newPoints = new double* [n];
	for (size_t i = 0; i < n; ++i) {
		newPoints[i] = new double[4];
		for (size_t j = 0; j < 4; ++j) {
			newPoints[i][j] = rand() % 100 + 1;
		}
	}
	return newPoints;
}

PointWithMass* createPointFromArray(double** points, size_t n) {
	PointWithMass* pointsInStruct = new PointWithMass[n];
	for (size_t i = 0; i < n; ++i) {
		pointsInStruct[i] = PointWithMass{ points[i][3], points[i][0], points[i][1], points[i][2] };
	}
	return pointsInStruct;
}