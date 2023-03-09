#include <bits/stdc++.h>
#define fastio cin.tie(0)->ios::sync_with_stdio(0); cout.tie(0); setvbuf(stdout, nullptr, _IOFBF, BUFSIZ);
#define INF INT32_MAX
// INT32_MIN, INT64_MIN, INT32_MAX, INT64_MAX
using namespace std;
using int64 = int64_t;

int T;
int tmp[75000], segTree[75000 * 4];

//세그먼트 트리 업데이트
void updateTree(int start, int end, int node, int price) {
	if (price < start || price > end) return;

	segTree[node] ++;

	if (start != end) {
		int half = (start + end) / 2;

		updateTree(start, half, node * 2, price);
		updateTree(half + 1, end, node * 2 + 1, price);
	}
}

//현재 섬으로 올 수 있는 경우의 수
int64 query(int start, int end, int node, int left, int right) {
	if (end <= right) return segTree[node];
	if (start > right) return 0;

	int half = (start + end) / 2;

	return query(start, half, node * 2, left, right) + query(half + 1, end, node * 2 + 1, left, right);
}

int main() {
	fastio;

	cin >> T;

	//테스트 케이스
	for (int t = 0; t < T; ++t) {
		//변수 초기화
		memset(segTree, 0, sizeof segTree);
		memset(tmp, 0, sizeof tmp);
		vector<pair<int, int>> island;

		//섬 개수
		int N;
		int64 Ans = 0;
		cin >> N;

		//섬 좌표
		for (int i = 0; i < N; ++i) {
			int xi, yi;
			cin >> xi >> yi;

			island.push_back({ xi, yi });
		}

		// y좌표 정렬
		sort(island.begin(), island.end(), [](pair<int, int>& a, pair<int, int>&b) {return a.second < b.second; });

		int cnt = 0;

		// y좌표 압축
		for (int i = 0; i < island.size(); ++i) {
			if (i > 0 && island[i].second != island[i - 1].second) cnt++;
			tmp[i] = cnt;
		}

		for (int i = 0; i < island.size(); ++i) {
			island[i].second = tmp[i];
		}

		// 
		sort(island.begin(), island.end(), [](pair<int, int>& a, pair<int, int>&b) {
			if (a.first == b.first)
				return a.second < b.second;
			return a.first > b.first;
		});

		for (auto & iter : island) {
			Ans += query(0, island.size() - 1, 1, 0, iter.second);
			updateTree(0, island.size() - 1, 1, iter.second);
		}

		cout << Ans << "\n";

	}

	return EXIT_SUCCESS;
} 
