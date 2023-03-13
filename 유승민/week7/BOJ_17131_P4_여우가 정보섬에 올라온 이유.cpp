#include<bits/stdc++.h>
#define fastio cin.tie(0)->ios::sync_with_stdio(0); cout.tie(0); setvbuf(stdout, nullptr, _IOFBF, BUFSIZ);
using namespace std;
using int64 = int64_t;

const int MOD = 1e9 + 7;

inline int64 findTree(vector<int>& segTree, int start, int end, int node, int left, int right) {
	if (right < start || left > end) return 0;
	if (left <= start && right >= end ) return segTree[node];

	int half = (start + end) / 2;

	return findTree(segTree, start, half, node * 2, left, right) + findTree(segTree, half + 1, end, node * 2 + 1, left, right);
}

inline void updateTree(vector<int>& segTree, int start, int end, int node, int pivot) {
	if (start <= pivot && end >= pivot) {
		segTree[node]++;
	}
	else return;

	if (start != end) {
		int half = (start + end) / 2;

		updateTree(segTree, start, half, node * 2, pivot);
		updateTree(segTree, half + 1, end, node * 2 + 1, pivot);
	}
}

struct cmp {
	bool operator()(pair<int, int> o1, pair<int, int>o2) {
		if (o1.second == o2.second)
			return o2.first < o1.first;
		return o1.second < o2.second;
	}
};

int main() {
	fastio;

	int h = (int)ceil(log2(4 * 1e5 + 1));
	int tree_size = 1 << (h + 1);

	vector<int> segTree(tree_size), lazy;
	priority_queue<pair<int, int>, vector<pair<int, int>>, cmp> pq;

	int N, pivot = 0;
	int64 ans = 0;
	cin >> N;

	for (int i = 0; i < N; ++i) {
		int a, b;
		cin >> a >> b;

		pq.push({ a + 2 * 1e5, b });
	}

	pivot = pq.top().second;

	while (!pq.empty()) {
		int x = pq.top().first;
		int y = pq.top().second;

		pq.pop();

		if (y != pivot) {
			while (!lazy.empty()) {
				updateTree(segTree, 0, 4 * 1e5, 1, lazy.back());
				lazy.pop_back();
			}

			pivot = y;
		}

		if (x != 0 && x != 4 * 1e5) {
			ans += findTree(segTree, 0, 4 * 1e5, 1, 0, x - 1) * findTree(segTree, 0, 4 * 1e5, 1, x + 1, 4 * 1e5);
			ans %= MOD;
		}

		lazy.push_back(x);

	}

	cout << ans;

	return 0;
}