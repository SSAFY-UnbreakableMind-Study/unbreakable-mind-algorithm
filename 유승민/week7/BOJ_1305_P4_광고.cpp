#include<bits/stdc++.h>
#define fastio cin.tie(0)->ios::sync_with_stdio(0); cout.tie(0); setvbuf(stdout, nullptr, _IOFBF, BUFSIZ);
using namespace std;
using int64 = int64_t;

int L;

int main() {
	fastio;

	cin >> L;
	
	string s;
	cin >> s;

	vector<int> pi(L);

	int j = 0;
	for (int i = 1; i < L; ++i) {
		while (j > 0 && s[i] != s[j]) {
			j = pi[j - 1];
		}

		if (s[i] == s[j]) {
			pi[i] = ++j;
		}
	}

	cout << L - pi[L-1];

	return EXIT_SUCCESS;
}