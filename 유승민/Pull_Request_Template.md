## BOJ_3954_G1_Brainfk 인터프리터
- 스택, 구현
- https://www.acmicpc.net/problem/3954



## 풀이

여는괄호와 닫는괄호를 짝 지어준 후, 쿼리를 실행하였습니다.


~~~java
//열리는 괄호와 닫히는 괄호 계산
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
~~~

첫 5천만번은 유한루프를 탐지하기 위해 실행하고
이후 5천만번은 무한루프를 탐지하기 위해 실행하였습니다.


~~~java
//쿼리 실행
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
				// 해당 괄호가 몇번 실행됬는지 카운트
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
~~~


이후 닫히는 괄호가 2번이상 실행된 부분이
실제로 무한루프가 걸리는 부분입니다.

~~~java
	// 무한루프인 괄호 찾기
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
~~~


## 소스코드
~~~java
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

		//열리는 괄호와 닫히는 괄호 계산
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

		bool isInfLoop = true; // 무한루프인가?

		//쿼리 실행
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
				// 해당 괄호가 몇번 실행됬는지 카운트
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

		// 무한루프인 괄호 찾기
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
~~~


<br/>



## 결과 

| 메모리 | 시간 |
| ------ | ---- |
| 2324KB | 1748ms |


