#include<bits/stdc++.h>
#define fastio cin.tie(0)->ios::sync_with_stdio(0); cout.tie(0); setvbuf(stdout, nullptr, _IOFBF, BUFSIZ);
using namespace std;
using int64 = int64_t;

inline int gcd(int a, int b) {
	while (true) {
		if (a % b == 0) return b;
		int tmp = a % b;
		a = b;
		b = tmp;
	}
}

int main() {

	fastio;

	int N, nu = 0;
	cin >> N;

	vector<char> s(2 * N), p(N);
	vector<int> pi(N);

	for (int i = 0; i < N; ++i) {
		cin >> s[i];
		s[i + N] = s[i];
	}

	for (int i = 0; i < N; ++i) {
		cin >> p[i];
	}

	for (int i = 1, j = 0; i < N; ++i) {
		while (j > 0 && p[i] != p[j]) {
			j = pi[j - 1];
		}

		if (p[i] == p[j]) {
			pi[i] = ++j;
		}
	}

	for (int i = 0, j = 0; i < 2 * N - 1; ++i) {
		while (j > 0 && s[i] != p[j]) {
			j = pi[j - 1];
		}

		if (s[i] == p[j]) {
			if (j == N - 1) {
				nu++;
				j = pi[j];
			}
			else ++j;
		}
	}

	if (nu == N) cout << "1/1";
	else {
		int divN = gcd(nu, N);
		cout << nu / divN << "/" << N / divN;
	}

	return EXIT_SUCCESS;
}