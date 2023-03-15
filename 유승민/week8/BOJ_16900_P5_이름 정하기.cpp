#include<iostream>
#include<vector>
#include<string>
#define fastio cin.tie(0)->ios::sync_with_stdio(0); cout.tie(0); setvbuf(stdout, nullptr, _IOFBF, BUFSIZ);
using namespace std;
using int64 = int64_t;



int main() {

	fastio;

	int n;
	string s;

	cin >> s >> n;
	vector<int> pi(s.length());

	int j = 0;
	for (int i = 1; i < s.length(); ++i) {
		while (j > 0 && s[j] != s[i]) {
			j = pi[j - 1];
		}

		if (s[j] == s[i]) {
			pi[i] = ++j;
		}
	}

	int64 ans = (s.length() - pi[s.length() - 1]) * (n - 1) + s.length();
	cout << ans;

	

	return EXIT_SUCCESS;
}