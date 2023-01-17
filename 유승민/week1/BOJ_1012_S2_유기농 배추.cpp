#include <bits/stdc++.h>
#define fastio cin.tie(0)->ios::sync_with_stdio(0); cout.tie(0); setvbuf(stdout, nullptr, _IOFBF, BUFSIZ);
#define INF 109876543210
using namespace std;
using int64 = int64_t;
//BOJ_1012_S2_유기농 배추
//2168KB, 0ms

int N, M, bg, ans, _map[51][51]; // 맵크기, 배추 개수, 정답, 맵
int dx[4] = { -1, 0, 1, 0 }, dy[4] = { 0, 1, 0, -1 }; //방향
vector < pair<int, int>> v;
queue<pair<int, int>> q;

//다음 위치가 유효한가?
inline bool isValid(int x, int y) {
	return (x >= 0 && x < N && y >= 0 && y < M);
}

//단순 BFS 탐색
inline void BFS() {
	while (!q.empty()) {
		int x = q.front().first;
		int y = q.front().second;

		q.pop();

		for (int i = 0; i < 4; ++i) {
			int next_x = x + dx[i];
			int next_y = y + dy[i];

			if (isValid(next_x, next_y)) {
				if (_map[next_x][next_y]) {
					_map[next_x][next_y] = 0;
					q.push({ next_x, next_y });
				}
			}
		}
	}
}

int main() {
	fastio;

	int T;
	cin >> T;
	for (int t = 0; t < T; ++t) {

		cin >> N >> M >> bg;
		for (int i = 0; i < bg; ++i) {
			int a, b;
			cin >> a >> b;

			_map[a][b] = 1; //배추 위치
			v.push_back({ a,b }); // 벡터에 저장
		}

		//배추가 안전한지 확인하는 부분
		for (auto iter : v) {
			if (_map[iter.first][iter.second]) {
				_map[iter.first][iter.second] = 0;
				q.push({ iter.first, iter.second });
				ans++;
				BFS();
			}
		}

		cout << ans << "\n";

		v.clear();
		ans = 0;

	}

	return EXIT_SUCCESS;
}