#include <iostream>
using namespace std;
#include <cassert>
#include "Functions.h"

#define NDEBUG

void testInt(int length) {
	//initialise array and fill it with random numbers in range from [-500;500]
	//also oldArr to check if you dont loose some numbers through sorting
	int* arr = new int[length];
	int* oldArr = new int[length];
	srand((unsigned int)time(0));
	for (int i = 0; i < length; i++) {
		int l = rand() % 1000 - 500;
		arr[i] = l;
		oldArr[i] = l;
	}
	cout << "unsorted array" << endl;
	for (int i = 0; i < length; i++) {
		cout << "arr[" << i << "] = " << arr[i] << endl;
	}

	//sort array with quickSort
	quickSort(arr, 0, length - 1);

	cout << endl;
	cout << "sorted array" << endl;
	for (int i = 0; i < length; i++) {
		cout << "arr[" << i << "] = " << arr[i] << endl;
	}
#ifdef NDEBUG 
	//check if array sorted 
	assert(ckeckIfSorted(arr, length));
	//check if our sorted array consists only elements from unsorted array
	assert(checkIfElementsSame(oldArr, arr, length));
#endif 
	delete[]arr;
	delete[]oldArr;
	cout << endl;
}

void testDouble(int length) {
	// Initialize array and fill it with random numbers in range from [-200; 200]
	double* arr = new double[length];
	double* oldArr = new double[length];

	srand(time(0));  
	for (int i = 0; i < length; i++) {
		double l = (rand() % 400 - 200) + (rand() % 100) / 100.0;
		arr[i] = l;
		oldArr[i] = l;
	}

	cout << "Unsorted array:" << endl;
	for (int i = 0; i < length; i++) {
		cout << "arr[" << i << "] = " << arr[i] << endl;
	}

	// Sort array with quickSort
	quickSort(arr, 0, length - 1);

	cout << endl;
	cout << "Sorted array:" << endl;
	for (int i = 0; i < length; i++) {
		cout << "arr[" << i << "] = " << arr[i] << endl;
	}
#ifdef NDEBUG 
	// Check if array is sorted
	assert(ckeckIfSorted(arr, length));
	// Check if sorted array contains only the elements from the unsorted array
	assert(checkIfElementsSame(oldArr, arr, length));
#endif 
	delete[] arr;
	delete[] oldArr;
	cout << endl;
}
void testString(int length) {
	string* arr = new string[length];
	string* oldArr = new string[length];

	srand(time(0));  
	for (int i = 0; i < length; i++) {
		arr[i] = randomString(5 + rand() % 6); // Generates random strings of length [5-10]
		oldArr[i] = arr[i];
	}

	cout << "Unsorted array:" << endl;
	for (int i = 0; i < length; i++) {
		cout << "arr[" << i << "] = " << arr[i] << endl;
	}

	// Sort array 
	quickSort(arr,0,length-1);

	cout << endl;
	cout << "Sorted array:" << endl;
	for (int i = 0; i < length; i++) {
		cout << "arr[" << i << "] = " << arr[i] << endl;
	}

#ifdef NDEBUG 
	// Check if array is sorted
	assert(ckeckIfSorted(arr, length));
	// Check if sorted array contains only the elements from the unsorted array
	assert(checkIfElementsSame(oldArr, arr, length));
#endif 
	delete[] arr;
	delete[] oldArr;
	cout << endl;
}
int main() {
	//testing int
	testInt(7);
	testInt(18);
	testInt(100);
	testInt(1000);

	// Testing double
	testDouble(50);
	testDouble(300);

	//Test string 
	testString(20);
	testString(100);

}
