#include <bits/stdc++.h>
#define fastio cin.tie(0)->ios::sync_with_stdio(0); cout.tie(0); setvbuf(stdout, nullptr, _IOFBF, BUFSIZ);
#define INF INT32_MAX
// INT32_MIN, INT64_MIN, INT32_MAX, INT64_MAX
using namespace std;
using int64 = int64_t;

int64 ans;
vector<int64> v;

int main() {
	fastio;

	int64 N, M, K, Ans = 0;
	cin >> N >> M >> K;

	int64 A;
	for (int i = 0; i < N; ++i) {
		cin >> A;
		v.push_back(A);
	}

	sort(v.begin(), v.end());

	// K번째보다 작은 i에 대하여 걸리는 최댓값은 v[i]
	for (int i = 0; i < K; ++i) {
		if (i >= v.size()) break;
		if (v[i] <= M) ans++;
		else break;
	}

	// K이상인 i에 대하여 걸리는 최댓값은 v[i] + v[i-K] 
	for (int i = K; i < v.size(); ++i) {
		if (v[i] + v[i - K] <= M) {
			ans++;
			v[i] += v[i - K];
		}
		else break;
	}

	cout << ans;

	return EXIT_SUCCESS;
}