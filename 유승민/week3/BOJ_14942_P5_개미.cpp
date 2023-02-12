#include <bits/stdc++.h>
#define fastio cin.tie(0)->ios::sync_with_stdio(0); cout.tie(0); setvbuf(stdout, nullptr, _IOFBF, BUFSIZ);
#define INF INT32_MAX
// INT32_MIN, INT64_MIN, INT32_MAX, INT64_MAX
using namespace std;
using int64 = int64_t;

int64 N;
int64 energy[100001];
pair<int64, int64> rareArr[100001][18];
vector<pair<int64, int64>> vp[100001];

//재귀를 이용하여 가장 지상과 가까운 굴 찾기
inline int64 findLD(int64 cur, int64 eny) {
	if (eny - rareArr[cur][0].second < 0) return cur;
	else {
		for (int64 i = 1; i < 18; ++i) {
			if (eny - rareArr[cur][i].second < 0) return findLD(rareArr[cur][i - 1].first, eny - rareArr[cur][i - 1].second);
		}
	}

	return 1;
}

// 희소배열 맵핑 
inline void setArr(int64 cur, int64 parent, int64 dis) {
	rareArr[cur][0].first = parent;
	rareArr[cur][0].second = dis;

	//자신으로부터 2^i 번째 위에있는 굴까지 가는데 걸리는 비용
	for (int64 i = 1; i < 18; ++i) {
		rareArr[cur][i].first = rareArr[rareArr[cur][i - 1].first][i - 1].first;
		rareArr[cur][i].second = rareArr[rareArr[cur][i - 1].first][i - 1].second + rareArr[cur][i - 1].second;
	}

	for (auto& iter : vp[cur]) {
		if (iter.first == parent) continue;
		setArr(iter.first, cur, iter.second);
	}
}

int main() {
	fastio;

	cin >> N;

	for (int64 i = 1; i <= N; ++i) {
		cin >> energy[i];
	}

	for (int64 i = 1; i < N; ++i) {
		int64 a, b, c;
		cin >> a >> b >> c;

		vp[a].push_back({ b,c });
		vp[b].push_back({ a,c });
	}

	setArr(1, 1, 0);
	for (int64 i = 1; i <= N; ++i) {
		cout << findLD(i, energy[i]) << "\n";
	}

	return EXIT_SUCCESS;
}