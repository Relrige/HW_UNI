#include "Functions.h"
#include <climits> 

string randomString(int length) {
	static const char charset[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	string result;
	for (int i = 0; i < length; i++) {
		result += charset[rand() % (sizeof(charset) - 1)];
	}
	return result;
}

