#include<bits/stdc++.h>
#define fastio cin.tie(0)->ios::sync_with_stdio(0); cout.tie(0); setvbuf(stdout, nullptr, _IOFBF, BUFSIZ);
using namespace std;
using int64 = int64_t;



int main() {

	fastio;

	int N, maxK = 0, ans = 0;
	cin >> N;

	vector<int> s(N), pi(N);
	for (int i = 0; i < N; ++i) {
		cin >> s[i];
	}

	int j = 0;
	for (int i = N - 2; i >= 0; --i) {
		while (j > 0 && s[N - 1 - j] != s[i]) {
			j = pi[N - j];
		}

		if (s[N - 1 - j] == s[i]) {
			pi[i] = ++j;
			if (pi[i] > maxK) {
				maxK = pi[i];
				ans = 1;
			}
			else if (pi[i] == maxK) ans++;
		}
	}

	if (maxK) cout << maxK << " " << ans;
	else cout << -1;

	return EXIT_SUCCESS;
}