#pragma once
#include <climits> 
#include <iostream>
#include <string>
using namespace std;

string randomString(int length);

template <typename T>
void swap(T* a, T* b) {
	T temp(*a);
	*a = *b;
	*b = temp;
}

//find pivot element in unsorted array and place smaller elements before it and bigger after it.
template <typename T>
int partition(T arr[], int low, int high) {
	T pivot = arr[high];
	int i = (low - 1);

	for (int j = low; j <= high - 1; j++) {
		if (arr[j] <= pivot) {
			i++;
			swap(&arr[i], &arr[j]);
		}
	}
	swap(&arr[++i], &arr[high]);
	return i;
}

//recursive quickSort(nlog(n) average)
//using partition find place of pivot element, then recursive sort part before this element and part after it.
template <typename T>
void quickSort(T* arr, int low, int high) {
	if (low < high) {
		//finding pivot to divide array on two parts
		int pivot = partition(arr, low, high);

		//recursevly sort left part
		quickSort(arr, low, pivot - 1);
		//recursevly sort right part
		quickSort(arr, pivot + 1, high);
	}
}

//Checking if sorted
template <typename T>
bool ckeckIfSorted(T* arr, int length) {
	for (int i = 0; i < length - 1; i++) {
		if (arr[i] > arr[i + 1]) {
			std::cout << "------Error " << arr[i]<<" " << arr[i + 1];
			return false;
		}
	}
	return true;
}
//checking if arrays contains of same elements
template <typename T>
bool checkIfElementsSame(T* prevArr, T* sortedArr, int length) {
	if (length <= 0) return true;
	for (int i = 0; i < length; i++) {
		bool foundMatch = false;
		for (int j = 0; j < length; j++) {
			if (sortedArr[i] == prevArr[j]) {
				prevArr[j] = T();
				foundMatch = true;
				break;
			}
		}
		if (!foundMatch) {
			return false;
		}
	}
	return true;
}
