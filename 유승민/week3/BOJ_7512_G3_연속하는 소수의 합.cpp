#include <bits/stdc++.h>
#define fastio cin.tie(0)->ios::sync_with_stdio(0); cout.tie(0); setvbuf(stdout, nullptr, _IOFBF, BUFSIZ);
#define INF INT64_MAX
// INT32_MIN, INT64_MIN, INT32_MAX, INT64_MAX
using namespace std;
using int64 = int64_t;

int64 T;
bool demical[10000000];
vector<int64> knapsack;

int main() {
	fastio;

	// 소수 찾기
	for (int64 i = 2; i * i < 10000000; ++i) {
		for (int64 j = 2; i * j < 10000000; ++j) {
			demical[i * j] = true;
		}
	}

	//소수 맵핑
	for (int64 i = 2; i < 10000000; ++i) {
		if (!demical[i]) knapsack.push_back(i);
	}

	cin >> T;

	//테스트 케이스 개수
	for (int64 t = 1; t <= T; ++t) {
		int64 m;
		cin >> m;

		int64 ni[10];
		for (int64 i = 0; i < m; ++i) {
			cin >> ni[i];
		}

		int64 maxx = -1; // 찾아야 할 값
		int64 pnt[10] = { 0, }; //포인터
		int64 slider[10] = { 0, }; // 슬라이딩 윈도우

		//슬라이딩 윈도우 초기값 세팅
		for (int64 i = 0; i < m; ++i) {
			for (; pnt[i] < ni[i]; ++pnt[i]) {
				slider[i] += knapsack[pnt[i]];
			}
			maxx = max(maxx, slider[i]);
		}

		// 최솟값 찾기
		while (1) {
			// 모든 경우에 대해서 찾아야하는 값이 더 크면 슬라이딩 윈도우 한칸씩 밀기
			for (int64 i = 0; i < m; ++i) {
				for (; pnt[i] < knapsack.size(); ++pnt[i]) {
					if (slider[i] == maxx && !demical[maxx]) break;
					else {
						slider[i] += knapsack[pnt[i]];
						slider[i] -= knapsack[pnt[i] - ni[i]];

						if (maxx <= slider[i]) {
							maxx = slider[i];
						}
					}
				}
			}

			//모든 경우에 대해 maxx값이 다 같은지 확인
			int cnt = 0;
			for (int64 i = 0; i < m; ++i) {
				if (slider[i] == maxx) cnt++;
				else break;
			}

			if (cnt == m) {
				break;
			}
		}

		cout << "Scenario " << t << ":" << "\n" << maxx << "\n\n";

	}

	return EXIT_SUCCESS;
}