#include<bits/stdc++.h>
#define fastio cin.tie(0)->ios::sync_with_stdio(0); cout.tie(0); setvbuf(stdout, nullptr, _IOFBF, BUFSIZ);
using namespace std;
using int64 = int64_t;

int64 arr[100000], maxx;
priority_queue<pair<int64, int>> pq;

int main() {

	int N, D;
	cin >> N >> D;

	cin >> arr[0];
	maxx = arr[0];
	pq.push({ arr[0], 0 });

	for (int i = 1; i < N; ++i) {
		cin >> arr[i];

		while (pq.top().second < i - D) {
			pq.pop();
		}

		arr[i] = max(arr[i], arr[i] + pq.top().first);
		maxx = max(maxx, arr[i]);
		pq.push({ arr[i], i });
	}

	cout << maxx;

	return EXIT_SUCCESS;
}