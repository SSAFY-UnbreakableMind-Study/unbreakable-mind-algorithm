#include<iostream>
#include<string>
#include<vector>
#define fastio cin.tie(0)->ios::sync_with_stdio(0); cout.tie(0); setvbuf(stdout, nullptr, _IOFBF, BUFSIZ);
using namespace std;



int main() {

	fastio;

	while (true) {
		string s;
		cin >> s;
		vector<int> pi(s.length());

		if (s == ".") break;

		int j = 0;
		for (int i = 1; i < s.length(); ++i) {
			while (j > 0 && s[j] != s[i]) {
				j = pi[j - 1];
			}

			if (s[i] == s[j]) {
				pi[i] = ++j;
			}

		}

		if (s.size() % (s.size() - pi[s.size() - 1])) {
			cout << "1" << "\n";
		}
		else {
			cout << s.size() / (s.size() - pi[s.size() - 1]) << "\n";
		}

	}

	return EXIT_SUCCESS;
}