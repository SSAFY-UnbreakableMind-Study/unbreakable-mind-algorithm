#include <bits/stdc++.h>
#define fastio cin.tie(0)->ios::sync_with_stdio(0); cout.tie(0); setvbuf(stdout, nullptr, _IOFBF, BUFSIZ);
#define INF INT32_MAX
// INT32_MIN, INT64_MIN, INT32_MAX, INT64_MAX
using namespace std;
using int64 = int64_t;

int N, M;
float Ans = INF;
float _map[201][201];
vector<tuple<int, int, float>> v;

int main() {
	fastio;

	cin >> N >> M;

	//그래프 초기화
	for (int i = 1; i <= N; ++i) {
		for (int j = 1; j <= N; ++j) {
			if (i != j) _map[i][j] = INF;
			else _map[i][j] = 0;
		}
	}
	
	//가중치 설정
	for (int i = 0; i < M; ++i) {
		int a, b;
		float c;
		cin >> a >> b;
		cin >> c;

		_map[a][b] = min(_map[a][b], c);
		_map[b][a] = min(_map[b][a], c);
		v.push_back({ a,b,c });
	}

	//플로이드 워셜 알고리즘
	for (int i = 1; i <= N; ++i) {
		for (int j = 1; j <= N; ++j) {
			for (int k = 1; k <= N; ++k) {
				_map[j][k] = min(_map[j][k], _map[j][i] + _map[i][k]);
			}
		}
	}

	//브루트 포스를 통한 정답 도출 
	for (int i = 1; i <= N; ++i) {
		float maxx = 0;
		for (auto iter : v) {
			float a = _map[i][get<0>(iter)];
			float b = _map[i][get<1>(iter)];
			float c = get<2>(iter);
			float dif = abs(a - b);

			if (dif < c) {
				maxx = max(maxx, max(a, b) + (c - dif) / 2);
			}
			else {
				maxx = max(maxx, min(a, b) + c);
			}

			if (maxx >= Ans) break;
		}
		
		Ans = min(Ans, maxx);
	}
	
	printf("%.1f", Ans);

	return EXIT_SUCCESS;
}