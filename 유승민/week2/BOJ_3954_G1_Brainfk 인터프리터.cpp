#include <bits/stdc++.h>
#define fastio cin.tie(0)->ios::sync_with_stdio(0); cout.tie(0); setvbuf(stdout, nullptr, _IOFBF, BUFSIZ);
#define INF INT32_MIN
// INT32_MIN, INT64_MIN, INT32_MAX, INT64_MAX
using namespace std;
using int64 = int64_t;
using uint = unsigned int;

int main() {
	fastio;

	int T;
	cin >> T;

	for (int t = 0; t < T; ++t) {
		int sm, sc, si;
		cin >> sm >> sc >> si;

		uint Sm[100001] = { 0, };
		int loopCnt[5000] = { 0, };
		char Sc[5000];
		char Si[5000];

		//??? ??? ??? ?? ??
		vector<int> bracketIn;
		vector<pair<int, int>> bracketInOut;
		for (int i = 0; i < sc; ++i) {
			cin >> Sc[i];
			if (Sc[i] == '[') {
				bracketIn.push_back(i);
			}
			else if (Sc[i] == ']') {
				bracketInOut.push_back({ bracketIn.back(), i });
				bracketIn.pop_back();
			}
		}

		for (int i = 0; i < si; ++i) {
			cin >> Si[i];
		}

		bool isInfLoop = true; // ???????

		//?? ??
		for (int i = 0, smp = 0, scp = 0, sip = 0, inp = -1, outp = 100001; i < 2; ++i) {
			for (int j = 0; j < 50000001; ++j) {

				if (scp >= sc) {
					isInfLoop = false;
					break;
				}

				if (Sc[scp] == '-') {
					Sm[smp] = (Sm[smp] - 1) % 256;
				}
				else if (Sc[scp] == '+') {
					Sm[smp] = (Sm[smp] + 1) % 256;
				}
				else if (Sc[scp] == '<') {
					if (smp == 0) smp = sm - 1;
					else smp--;
				}
				else if (Sc[scp] == '>') {
					if (smp == sm - 1) smp = 0;
					else smp++;
				}
				else if (Sc[scp] == '[') {
					if (!Sm[smp]) {
						inp = scp;
						for (auto iter : bracketInOut) {
							if (inp == iter.first) {
								outp = iter.second;
								break;
							}
						}
						scp = outp + 1;
						continue;
					}
				}
				// ?? ??? ?? ????? ???
				else if (Sc[scp] == ']') {
					loopCnt[scp]++;
					if (Sm[smp]) {
						outp = scp;
						for (auto iter : bracketInOut) {
							if (outp == iter.second) {
								inp = iter.first;
								break;
							}
						}
						scp = inp + 1;
						continue;
					}
				}
				else if (Sc[scp] == '.') {

				}
				else if (Sc[scp] == ',') {
					if (sip < si) {
						Sm[smp] = Si[sip];
						sip++;
					}
					else Sm[smp] = 255;
				}

				scp++;
			}
		}

		// ????? ?? ??
		int maxx = -1;
		if (isInfLoop) {
			for (int j = sc - 1; j >= 0; --j) {
				if (loopCnt[j] >= 2) {
					maxx = j;
					break;
				}
			}
			int minn;
			for (auto iter : bracketInOut) {
				if (maxx == iter.second) {
					minn = iter.first;
					break;
				}
			}

			cout << "Loops " << minn << ' ' << maxx << "\n";
		}
		else cout << "Terminates" << "\n";

	}

	return EXIT_SUCCESS;
}