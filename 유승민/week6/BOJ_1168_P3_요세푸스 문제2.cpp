#include<iostream>
#include<vector>
#include<cmath>
#define fastio cin.tie(0)->ios::sync_with_stdio(0); cout.tie(0); setvbuf(stdout, nullptr, _IOFBF, BUFSIZ);
using namespace std;

int N, K, P, D, n;

inline int initTree(vector<int>& segTree, int start, int end, int node) {
	if (start == end) return segTree[node] = 1;

	int half = (start + end) / 2;

	return segTree[node] = initTree(segTree, start, half, node * 2) + initTree(segTree, half + 1, end, node * 2 + 1);
}

inline void findJosephus(vector<int>& segTree, int start, int end, int node, int pivot, int prev) {
	if (prev > pivot) return;

	if (prev + segTree[node] < pivot) return;

	if (start != end) {
		int half = (start + end) / 2;

		findJosephus(segTree, start, half, node * 2, pivot, prev);
		findJosephus(segTree, half + 1, end, node * 2 + 1, pivot, prev + segTree[node * 2]);
		return;
	}

	if (start == end) {
		if (!segTree[node]) return;

		if (prev + segTree[node] == pivot) {
			P = prev;
			D = start;
			if (n) cout << start << ", ";
			else cout << start << ">";
		}
	}
}

inline void updateJosephus(vector<int>& segTree, int start, int end, int node) {
	if (start <= D && end >= D) segTree[node]--;
	else return;

	if (start != end) {
		int half = (start + end) / 2;

		updateJosephus(segTree, start, half, node * 2);
		updateJosephus(segTree, half + 1, end, node * 2 + 1);
	}
}

int main() {

	fastio;

	cin >> N >> K;

	P = N;
	int h = (int)ceil(log2(N));
	int tree_size = (1 << (h + 1));

	vector<int> segTree(tree_size);

	initTree(segTree, 1, N, 1);

	cout << "<";

	n = N;
	while (n--) {
		int pivot = (P + K) % (n + 1);
		if (!pivot) pivot = n + 1;
		findJosephus(segTree, 1, N, 1, pivot, 0);
		updateJosephus(segTree, 1, N, 1);
	}

	return EXIT_SUCCESS;
}