#include<iostream>
#include<vector>
#include<string>

#define fastio cin.tie(0)->ios::sync_with_stdio(0); cout.tie(0); setvbuf(stdout, nullptr, _IOFBF, BUFSIZ);
using namespace std;
using int64 = int64_t;

int main() {

	fastio;

	int64 N, ans = 0;
	cin >> N;

	vector<int>pi(N);

	string s;
	cin >> s;

	int j = 0;
	for (int i = 1; i < N; ++i) {
		while (j > 0 && s[j] != s[i]) {
			j = pi[j - 1];
		}

		if (s[j] == s[i]) pi[i] = pi[j] > 0 ? pi[j++] : ++j;


		if (pi[i]) ans += i - pi[i] + 1;
	}

	cout << ans;

	return EXIT_SUCCESS;
}