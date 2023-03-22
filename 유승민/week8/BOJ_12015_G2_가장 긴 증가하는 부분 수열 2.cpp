#include<bits/stdc++.h>
#define fastio cin.tie(0)->ios::sync_with_stdio(0); cout.tie(0); setvbuf(stdout, nullptr, _IOFBF, BUFSIZ);
using namespace std;
using int64 = int64_t;

	

int main() {

	fastio;

	int N;
	cin >> N;

	vector<int> arr(1);
	for (int i = 0; i < N; ++i) {
		int a;
		cin >> a;
		if (arr.back() < a) {
			arr.push_back(a);
		}
		else {
			arr[lower_bound(arr.begin(), arr.end(), a) - arr.begin()] = a;
		}
	}

	cout << arr.size() - 1;


	return EXIT_SUCCESS;
}