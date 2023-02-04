#include <bits/stdc++.h>
#define fastio cin.tie(0)->ios::sync_with_stdio(0); cout.tie(0); setvbuf(stdout, nullptr, _IOFBF, BUFSIZ);
#define INF INT64_MAX
// INT32_MIN, INT64_MIN, INT32_MAX, INT64_MAX
using namespace std;
using int64 = int64_t;

int64 N, M, Ans = INF;
int64 total, copy_total;
int64 _map[8][8], copy_map[8][8];
int64 cctvSize[6] = { 0, 4, 2, 4, 4, 1 };
int64 dx[4] = { -1, 0, 1, 0 }, dy[4] = { 0, 1, 0, -1 };
vector<int64> cctvDir[6][4];
vector<tuple<int64, int64, int64>> vec, cctvPos;
queue<tuple<int64, int64, int64>> q;

//각각의 CCTV가 볼수 있는 방향 맵핑
inline void setDir() {
	cctvDir[1][0].push_back(0);
	cctvDir[1][1].push_back(1);
	cctvDir[1][2].push_back(2);
	cctvDir[1][3].push_back(3);

	cctvDir[2][0].push_back(0);
	cctvDir[2][0].push_back(2);
	cctvDir[2][1].push_back(1);
	cctvDir[2][1].push_back(3);

	cctvDir[3][0].push_back(0);
	cctvDir[3][0].push_back(1);
	cctvDir[3][1].push_back(1);
	cctvDir[3][1].push_back(2);
	cctvDir[3][2].push_back(2);
	cctvDir[3][2].push_back(3);
	cctvDir[3][3].push_back(3);
	cctvDir[3][3].push_back(0);

	cctvDir[4][0].push_back(0);
	cctvDir[4][0].push_back(1);
	cctvDir[4][0].push_back(2);

	cctvDir[4][1].push_back(1);
	cctvDir[4][1].push_back(2);
	cctvDir[4][1].push_back(3);

	cctvDir[4][2].push_back(2);
	cctvDir[4][2].push_back(3);
	cctvDir[4][2].push_back(0);

	cctvDir[4][3].push_back(3);
	cctvDir[4][3].push_back(0);
	cctvDir[4][3].push_back(1);

	cctvDir[5][0].push_back(0);
	cctvDir[5][0].push_back(1);
	cctvDir[5][0].push_back(2);
	cctvDir[5][0].push_back(3);
}

// 유효한 위치인지 확인
inline bool isValid(int64 x, int64 y) {
	return (x >= 0 && x < N&& y >= 0 && y < M);
}

//BFS 탐색 시작
inline void BFS() {
	while (!q.empty()) {
		int64 cur_x = get<0>(q.front());
		int64 cur_y = get<1>(q.front());
		int64 direct = get<2>(q.front());

		q.pop();

		int64 next_x = cur_x + dx[direct];
		int64 next_y = cur_y + dy[direct];

		if (isValid(next_x, next_y)) {
			if (copy_map[next_x][next_y] != 6) {
				q.push({ next_x, next_y, direct });
				if (!copy_map[next_x][next_y]) {
					copy_map[next_x][next_y] = -1;
					copy_total--;
				}
			}
		}

	}

	Ans = min(copy_total, Ans);
}

//BFS 탐색 전 맵초기화
inline void setMap() {
	for (int i = 0; i < N; ++i) {
		for (int j = 0; j < M; ++j) {
			copy_map[i][j] = _map[i][j];
		}
	}

	for (auto iter : cctvPos) {
		q.push({ get<0>(iter), get<1>(iter) , get<2>(iter) });
	}

	copy_total = total;
}

//각각의 CCTV 위치 설정 재귀함수 이용
inline void conbiDir(int64 cur, int64 dep) {
	if (cur == dep) {
		setMap();
		BFS();
		return;
	}

	int64 cur_x = get<0>(vec[cur]);
	int64 cur_y = get<1>(vec[cur]);
	int64 curCCTV = get<2>(vec[cur]);

	for (int i = 0; i < cctvSize[curCCTV]; ++i) {
		for (int j = 0; j < cctvDir[curCCTV][i].size(); ++j) {
			cctvPos.push_back({ cur_x, cur_y, cctvDir[curCCTV][i][j] });
		}
		conbiDir(cur + 1, dep);
		for (int j = 0; j < cctvDir[curCCTV][i].size(); ++j) {
			cctvPos.pop_back();
		}
	}


}

int main() {
	fastio;

	cin >> N >> M;
	for (int i = 0; i < N; ++i) {
		for (int j = 0; j < M; ++j) {
			cin >> _map[i][j];

			if (!_map[i][j]) {
				total++;
			}
			else if (_map[i][j] > 0 && _map[i][j] < 6) {
				vec.push_back({ i, j, _map[i][j] });
			}
		}
	}

	setDir();
	conbiDir(0, vec.size());

	cout << Ans;

	return EXIT_SUCCESS;
}