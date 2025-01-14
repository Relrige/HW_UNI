#include <iostream>
#include <cassert>
#include<ctime>
#include "Structurs.h"
#include "Functions.h"

using namespace std;


//task1 check;
void task1CheckNForSimplifying() {
	cout << "-----Test1------" << endl;
	unsigned int n = 19;
	cout << "n = " << n << endl;
	int n2 = ~n ? n : 0xFFFFFFFF;
	task1(n);
#ifdef DEBUG
	assert(n == n2);
#endif

	cout << "For task1(n) = " << n << endl;
	n = 0;
	cout << "n = " << n << endl;
	n2 = ~n ? n : 0xFFFFFFFF;
	task1(n);

#ifdef DEBUG
	assert(n == n2);
#endif
	cout << "For task1(n) = " << n << endl;
	//check max value 
	n = 4294967295;
	cout << "n = " << n << endl;
	n2 = ~n ? n : 0xFFFFFFFF;
	task1(n);
#ifdef DEBUG
	assert(n == n2);
#endif
	cout << "task1(n) = " << n << endl;
};

//task2 check
void task2ForRecurent() {
	cout << endl;
	cout << "----Task2-----" << endl;
	try {
		cout << "S(1) = " << task2(1) << endl;
		cout << "S(2) = " << task2(2) << endl;
		cout << "S(3) = " << task2(3) << endl;
		cout << "S(4) = " << task2(4) << endl;
		cout << "S(13) = " << task2(13) << endl;
		cout << "S(49) = " << task2(49) << endl;

	}
	catch (int e) {
		cout << "invalid argument " << e << endl;
	}
}

void task3AmountofOneBits() {
	cout << endl;
	cout << "------ Task 3: Count the Number of '1' Bits ------" << endl;
	struct TestCase {
		unsigned int number;
		int base;
	};
	TestCase testCases[] = {
		{ 0, 1 },
		{ 11, 2 },
		{ 2048, 1 },
		{ 2047, 1 },
		{ 4294967295, 1 },
		{ 4294967295, 2 },
		{ 1, 1 },
		{ 15, 1 },
		{ 255, 1 },
		{ 128, 1 },
		{ 123456789, 1 },
		{ 987654321, 2 },
	};
	for (const auto& testCase : testCases) {
		try {
			cout << "task3(" << testCase.number << ", " << testCase.base << ") = "
				<< task3(testCase.number, testCase.base) << endl;
		}
		catch (int e) {
			cout << "Invalid argument: " << e << endl;
		}
	}
}


void task4Check() {
	cout << endl;
	cout << "----------- Task 4 -----------" << endl;
	cout << "Check behavior by assuming that a = task4(task4(a))" << endl;

	int testValues[] = {
	0,       
	17,
	-1234,     
	2147483647,
	-2147483648,     
	1,               
	-1,              
	42,              
	123,            
	1024,
	-1024,
	};
	const int numValues = sizeof(testValues) / sizeof(testValues[0]);

	for (int i = 0; i < numValues; ++i) {
		int val = testValues[i];
		cout << "task4(" << val << ") = " << task4(val) << endl;
		cout << "task4(task4(" << val << ")) = " << task4(task4(val)) << endl;
#ifdef DEBUG
		assert(task4(task4(val)) == val);
#endif
	}
}


void task5Check() {
	cout << endl;
	cout << "-----------task 5-----------" << endl;

	// Тестові випадки
	struct TestCase {
		int word;
		int expected;
	};

	TestCase tests[] = {
		{0x12345678, 0x21436587},
		{0x0F0F0F0F, 0xF0F0F0F0},
		{0xA1B2C3D4, 0x1A2B3C4D},
		{0xFFFFFFFF, 0xFFFFFFFF},
		{0x00000000, 0x00000000},
		{0x87654321, 0x78563412}
	};

	for (const auto& test : tests) {
		int result = task5(test.word);

#ifdef DEBUG
		assert(result == test.expected);
#endif

		cout << "Input:    0x" << hex << test.word << endl;
		cout << "Output:   0x" << hex << result << endl;
		cout << "Expected: 0x" << hex << test.expected << endl;

	}
}

void task6_7() {
	cout << endl;
	srand(time(NULL));
	cout << "Task 6 and 7 check" << endl;
	cout << "Task 6 and 7 should have the same value" << endl;

	double** pointsArr = createPoints(4);
	PointWithMass* pointsStruct = createPointFromArray(pointsArr, 4);

	try {
		for (char axis : {'x', 'y', 'z'}) {
			cout << "task6(points, 4, '" << axis << "') = " << task6(pointsArr, 4, axis) << endl;
			cout << "task7(pointsStruct, 4, '" << axis << "') = " << task7(pointsStruct, 4, axis) << endl;
		}

		cout << "Changing points..." << endl;

		delete[] pointsStruct;
		for (int i = 0; i < 4; ++i) {
			delete[] pointsArr[i];
		}
		delete[] pointsArr;

		pointsArr = createPoints(4);
		pointsStruct = createPointFromArray(pointsArr, 4);

		for (char axis : {'x', 'y', 'z'}) {
			double result6 = task6(pointsArr, 4, axis);
			double result7 = task7(pointsStruct, 4, axis);

			cout << "task6(points, 4, '" << axis << "') = " << result6 << endl;
			cout << "task7(pointsStruct, 4, '" << axis << "') = " << result7 << endl;
#ifdef DEBUG
			assert(result6 == result7);
#endif
		}
	}
	catch (const char& e) {
		cout << "Invalid argument: " << e << endl;
	}
	catch (const int& l) {
		cout << "Invalid argument: " << l << endl;
	}
	for (int i = 0; i < 4; ++i) {
		delete[] pointsArr[i];
	}
	delete[] pointsArr;
	delete[] pointsStruct;
}


int main()
{
	task1CheckNForSimplifying();
	task2ForRecurent();
	task3AmountofOneBits();
	task4Check();
	task5Check();
	task6_7();

}
