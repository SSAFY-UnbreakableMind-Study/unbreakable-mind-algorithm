#include<iostream>
#include<vector>
#include<cmath>
#include<map>
#define fastio cin.tie(0)->::sync_with_stdio(0); cout.tie(0); setvbuf(stdout, nullptr, _IOFBF, BUFSIZ);
#define INF INT32_MAX
using namespace std;
using int64 = int64_t;

int N;
int64 Ans;

void updateTree(vector<int>& segTree, int start, int end, int node, int ad) {
	if (start <= ad && end >= ad) segTree[node]++;
	else return;

	if (start != end) {
		int half = (start + end) / 2;

		updateTree(segTree, start, half, node * 2, ad);
		updateTree(segTree, half + 1, end, node * 2 + 1, ad);
	}
}

int findCross(vector<int>& segTree, int start, int end, int node, int left, int right) {
	if (end < left) return 0;
	else if (left <= start && right >= end) return segTree[node];

	int half = (start + end) / 2;

	return (findCross(segTree, start, half, node * 2, left, right) + findCross(segTree, half + 1, end, node * 2 + 1, left, right));
}

int main() {

	cin >> N;

	int h = (int)ceil(log2(N));
	int tree_size = (1 << (h + 1));

	vector<int> upArr(1000001);
	vector<int> downArr(N);
	vector<int> segTree(tree_size);


	for (int i = 0; i < N; ++i) {
		int a;
		cin >> a;
		upArr[a] = i;
	}

	for (int i = 0; i < N; ++i) {
		int a;
		cin >> a;
		downArr[i] = upArr[a];
	}

	for (int i = 0; i < N; ++i) {
		Ans += findCross(segTree, 0, N - 1, 1, downArr[i], N - 1);
		updateTree(segTree, 0, N - 1, 1, downArr[i]);
	}

	cout << Ans;

	return EXIT_SUCCESS;
}

