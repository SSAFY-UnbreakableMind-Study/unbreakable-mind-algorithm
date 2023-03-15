#include<bits/stdc++.h>
#define fastio cin.tie(0)->ios::sync_with_stdio(0); cout.tie(0); setvbuf(stdout, nullptr, _IOFBF, BUFSIZ);
using namespace std;
using int64 = int64_t;



int main() {

	fastio;

	int N, K;
	cin >> N >> K;

	bool tk = false;
	vector<int> s[100];
	vector<int> pi(K);

	for (int i = 0; i < N; ++i) {
		int M;
		cin >> M;

		for (int j = 0; j < M; ++j) {
			int a;
			cin >> a;
			s[i].push_back(a);
		}
	}

	for (int i = 0; i + K <= s[0].size(); ++i) {
		int idx = 0;
		for (int j = 1; j < K; ++j) {
			while (idx > 0 && s[0][idx + i] != s[0][j + i]) {
				idx = pi[idx - 1];
			}

			if (s[0][idx + i] == s[0][j + i]) {
				pi[j] = idx++;
			}
		}

		int checkPoint = 0;
		for (int j = 1; j < N; ++j) {
			if (checkPoint != j - 1) break;
			idx = 0;
			for (int k = 0; k < s[j].size(); ++k) {
				while (idx > 0 && s[0][idx + i] != s[j][k]) {
					idx = pi[idx - 1];
				}

				if (s[0][idx + i] == s[j][k]) {
					if (idx == K - 1) {
						checkPoint++;
						break;
					}
					else idx++;
				}
			}

			if (checkPoint == j) continue;

			for (int k = s[j].size() - 1; k >= 0; --k) {
				while (idx > 0 && s[0][idx + i] != s[j][k]) {
					idx = pi[idx - 1];
				}

				if (s[0][idx + i] == s[j][k]) {
					if (idx == K - 1) {
						checkPoint++;
						break;
					}
					else idx++;
				}
			}
		}

		if (checkPoint == N - 1) {
			tk = true;
			break;
		}
	}

	if (tk) cout << "YES";
	else cout << "NO";

	return EXIT_SUCCESS;
}