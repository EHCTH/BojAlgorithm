#include <iostream>
#include <string>
using namespace std;

int main() {
	int n, score;
	float answer;
	cin >> n;

	int max = -1;
	int sum = 0;

	for (int i = 0; i < n; ++i) {
		cin >> score;
		sum += score;
		if (score > max) {
			max = score;
		}
	}

	answer = sum * 100.0 / max / n;

	cout << answer;

	return 0;
}