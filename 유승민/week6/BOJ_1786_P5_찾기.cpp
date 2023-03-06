#include<iostream>
#include<vector>
#include<string>
#define fastio cin.tie(0)->ios::sync_with_stdio(0); cout.tie(0); setvbuf(stdout, nullptr, _IOFBF, BUFSIZ);
using namespace std;

int ans;
vector<int> start;

inline void KMP(string& T, string& P, vector<int>& pi) {
	int j = 0;
	for (int i = 0; i < T.size(); ++i) {
		while (j > 0 && T[i] != P[j]) {
			j = pi[j - 1];
		}

		if (T[i] == P[j]) {
			if (j == P.size() - 1) {
				ans++;
				start.push_back(i - P.size() + 2);
				j = pi[j];
			}
			else {
				++j;
			}
		}
	}
}

inline void getPi(string& P, vector<int>& pi) {
	int j = 0;
	for (int i = 1; i < P.size(); ++i) {
		while (j > 0 && P[j] != P[i]) {
			j = pi[j - 1];
		}

		if (P[j] == P[i]) pi[i] = ++j;
	}
}

int main() {

	string T, P;
	getline(cin, T);
	getline(cin, P);

	vector<int> pi(P.size());

	getPi(P, pi);
	KMP(T, P, pi);

	cout << ans << "\n";
	for (auto& iter : start) {
		cout << iter << " ";
	}

	return EXIT_SUCCESS;
}