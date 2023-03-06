#include<iostream>
#include<algorithm>
#include<vector>
#include<queue>
#define fastio cin.tie(0)->ios::sync_with_stdio(0); cout.tie(0); setvbuf(stdout, nullptr, _IOFBF, BUFSIZ);
using namespace std;
using int64 = int64_t;

struct cmp {
	bool operator()(int64 o1, int64 o2) {
		int pivot = 0;
		int len1 = 0, len2 = 0;
		int arr1[10] = { 0, }, arr2[10] = { 0, };

		if (!o1) len1 = 1;
		if (!o2) len2 = 1;

		while (o1 || o2) {
			arr1[pivot] = o1 % 10;
			arr2[pivot] = o2 % 10;

			if (!o1 && !len1) len1 = pivot;
			if (!o2 && !len2) len2 = pivot;

			o1 /= 10;
			o2 /= 10;

			pivot++;
		}

		if (!len1) len1 = pivot;
		if (!len2) len2 = pivot;

		int number = len1 + len2;

		for (int i = 0; i < number; ++i) {
			int res1 = i % len1;
			int res2 = i % len2;

			if (arr1[len1 - 1 - res1] < arr2[len2 - 1 - res2]) {
				return true;
			}
			else if (arr1[len1 - 1 - res1] > arr2[len2 - 1 - res2]) {
				return false;
			}
		}

		return len1 > len2;

	}
};

int main() {

	fastio;

	priority_queue<int64, vector<int64>, cmp> pq;

	int N, K;
	cin >> N >> K;

	vector<int64> vec(N);

	for (int i = 0; i < N; ++i) {
		int64 a;
		cin >> a;
		vec[i] = a;
		pq.push(a);
	}

	sort(vec.begin(), vec.end(), greater<int>());

	K -= N;
	while (K--) {
		pq.push(vec.front());
	}

	while (!pq.empty()) {
		cout << pq.top();
		pq.pop();
	}

	return EXIT_SUCCESS;
}