#include<bits/stdc++.h>
#define fastio cin.tie(0)->ios::sync_with_stdio(0); cout.tie(0); setvbuf(stdout, nullptr, _IOFBF, BUFSIZ);
using namespace std;
using int64 = int64_t;

inline pair<int, int> cmp(pair<int, int> p1, pair<int, int>p2) {
	vector<int> v(4);
	v[0] = p1.first;
	v[1] = p2.first;
	v[2] = p1.second;
	v[3] = p2.second;

	sort(v.begin(), v.end());

	pair<int, int> p = { v[3], v[2] };

	return p;
}

inline pair<int, int> findTree(vector<pair<int, int>>& segTree, int start, int end, int node, int left, int right) {
	if (start > right || end < left) return { 0, 0 };

	if (left <= start && right >= end) return segTree[node];

	int half = (start + end) / 2;

	pair<int, int> p1 = findTree(segTree, start, half, node * 2, left, right);
	pair<int, int> p2 = findTree(segTree, half + 1, end, node * 2 + 1, left, right);

	return cmp(p1, p2);

}

inline pair<int, int> updateTree(vector<pair<int, int>>& segTree, vector<int>& arr, int start, int end, int node, int idx) {
	if (end < idx || start > idx) return segTree[node];

	if (start == end) return segTree[node] = { arr[start], 0 };

	int half = (start + end) / 2;

	pair<int, int> p1 = updateTree(segTree, arr, start, half, node * 2, idx);
	pair<int, int> p2 = updateTree(segTree, arr, half + 1, end, node * 2 + 1, idx);

	return segTree[node] = cmp(p1, p2);

}

inline pair<int, int> initTree(vector<pair<int, int>>& segTree, vector<int>& arr, int start, int end, int node) {
	if (start == end) return segTree[node] = { arr[start], 0 };

	int half = (start + end) / 2;

	pair<int, int> p1 = initTree(segTree, arr, start, half, node * 2);
	pair<int, int> p2 = initTree(segTree, arr, half + 1, end, node * 2 + 1);

	return segTree[node] = cmp(p1, p2);

}

int main() {

	fastio;

	int N, M;
	cin >> N;

	int h = (int)ceil(log2(N));
	int tree_size = 1 << (h + 1);

	vector<int> arr(N + 1);
	vector<pair<int, int>> segTree(tree_size);

	for (int i = 1; i <= N; ++i) {
		cin >> arr[i];
	}

	initTree(segTree, arr, 1, N, 1);

	cin >> M;
	for (int i = 0; i < M; ++i) {
		int a, b, c;
		cin >> a >> b >> c;

		if (a == 1) {
			arr[b] = c;
			updateTree(segTree, arr, 1, N, 1, b);
		}
		else {
			pair<int, int> p = findTree(segTree, 1, N, 1, b, c);
			cout << p.first + p.second << "\n";
		}
	}

	return EXIT_SUCCESS;
}