#include<iostream>
#include<vector>
#include<string>
#include<algorithm>
#define fastio cin.tie(0)->ios::sync_with_stdio(0); cout.tie(0); setvbuf(stdout, nullptr, _IOFBF, BUFSIZ);
using namespace std;
using int64 = int64_t;



int main() {

	fastio;

	bool tk = false;
	int N, maxK = 0;
	cin >> N;

	vector<int> c1(N), c2(N), pi(N);

	for (int i = 0; i < N; ++i) {
		cin >> c1[i];
	}

	for (int i = 0; i < N; ++i) {
		cin >> c2[i];
	}

	sort(c1.begin(), c1.end());
	sort(c2.begin(), c2.end());

	int a = c1[0], b = c2[0];

	for (int i = 0; i < N - 1; ++i) {
		c1[i] = c1[i + 1] - c1[i];
		c2[i] = c2[i + 1] - c2[i];
	}

	c1[N - 1] = 360000 - c1[N - 1] + a;
	c2[N - 1] = 360000 - c2[N - 1] + b;

	c2.resize(2 * N - 1);

	for (int i = N; i < 2 * N - 1; ++i) {
		c2[i] = c2[i - N];
	}

	int j = 0; 
	for (int i = 1; i < N; ++i) {
		while (j > 0 && c1[i] != c1[j]) {
			j = pi[j - 1];
		}

		if (c1[i] == c1[j]) {
			pi[i] = ++j;
		}
	}

	j = 0;
	for (int i = 0; i < 2 * N - 1; ++i) {
		while (j > 0 && c1[j] != c2[i]) {
			j = pi[j - 1];
		}

		if (c1[j] == c2[i]) {
			if (j == N - 1) {
				tk = true;
				break;
			}
			else ++j;
		}
	}

	if (tk) cout << "possible";
	else cout << "impossible";

	return EXIT_SUCCESS;
}