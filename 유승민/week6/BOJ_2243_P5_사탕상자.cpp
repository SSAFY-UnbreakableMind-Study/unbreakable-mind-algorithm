#include<iostream>
#include<vector>
#include<cmath>
#define fastio cin.tie(0)->ios::sync_with_stdio(0); cout.tie(0); setvbuf(stdout, nullptr, _IOFBF, BUFSIZ);
#define CANDY 1000000
using namespace std;

int N, takeoff;

inline void updateTree(vector<int>& segTree, int start, int end, int node, int idx, int diff) {
    if (start <= idx && end >= idx) segTree[node] += diff;
    else return;

    int half = (start + end) / 2;

    if (start != end) {
        updateTree(segTree, start, half, node * 2, idx, diff);
        updateTree(segTree, half + 1, end, node * 2 + 1, idx, diff);
    }
}

inline void selectTree(vector<int>& segTree, int start, int end, int node, int total, int goal) {
    if (total >= goal) return;

    if (total + segTree[node] < goal) return;

    if (start == end) {
        cout << start << "\n";
        takeoff = start;
        return;
    }
    
    int half = (start + end) / 2;

    selectTree(segTree, start, half, node * 2, total, goal);
    selectTree(segTree, half + 1, end, node * 2 + 1, total + segTree[node * 2], goal);
}

int main() {

    fastio;

    cin >> N;

    int h = (int)ceil(log2(CANDY));
    int tree_size = (1 << (h + 1));

    vector<int> segTree(tree_size);

    for (int i = 0; i < N; ++i) {
        int A, B, C;
        cin >> A;

        if (A == 1) {
            cin >> B;
            selectTree(segTree, 1, CANDY, 1, 0, B);
            updateTree(segTree, 1, CANDY, 1, takeoff, -1);
        }
        else {
            cin >> B >> C;
            updateTree(segTree, 1, CANDY, 1, B, C);
        }

    }

	return EXIT_SUCCESS;
}