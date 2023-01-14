#include <bits/stdc++.h>
#define fastio cin.tie(0)->ios::sync_with_stdio(0); cout.tie(0); setvbuf(stdout, nullptr, _IOFBF, BUFSIZ);
#define INF 109876543210
using namespace std;
using int64 = int64_t;

int main() {
	fastio;
	int64 N, M, dp[101] = { 0, 1, 1, 1, 2, 2, 3, 4, 5, 7, 9, 12, 16, 21, 28, 37, 49 };
	cin >> N;

	for (int64 i = 17; i < 101; ++i) {
		dp[i] = dp[i - 1] + dp[i - 5];
	}

	for (int64 i = 0; i < N; ++i) {
		cin >> M;
		cout << dp[M] << "\n";
	}

	return EXIT_SUCCESS;
}