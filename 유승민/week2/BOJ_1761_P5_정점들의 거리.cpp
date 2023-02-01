#include <bits/stdc++.h>
#define fastio cin.tie(0)->ios::sync_with_stdio(0); cout.tie(0); setvbuf(stdout, nullptr, _IOFBF, BUFSIZ);
#define INF INT32_MIN
// INT32_MIN, INT64_MIN, INT32_MAX, INT64_MAX
using namespace std;
using int64 = int64_t;

//트리 구조체
typedef struct tree {
	int parent;
	int dep;
	int64 dis;
	vector<pair<int, int>> v;
}tree;

tree tr[40'001];

// 깊이 거리, 부모노드 설정
inline void setDis(int cur) {
	tr[cur].dis += tr[tr[cur].parent].dis;
	tr[cur].dep = tr[tr[cur].parent].dep + 1;
	for (auto iter : tr[cur].v) {
		if (iter.first == tr[cur].parent) continue;
		tr[iter.first].parent = cur;
		tr[iter.first].dis = iter.second;

		setDis(iter.first);
	}
}

int main() {
	fastio;

	int N;
	cin >> N;

	for (int i = 0; i < N - 1; ++i) {
		int a, b, c;
		cin >> a >> b >> c;

		tr[a].v.push_back({ b,c });
		tr[b].v.push_back({ a,c });
	}

	tr[1].parent = 0, tr[1].dis = 0;
	setDis(1);

	int M;
	cin >> M;

	for (int i = 0; i < M; ++i) {
		int a, b, da, db;
		cin >> a >> b;

		//깊이가 다르면 같게 맞추고, 노드값이 같으면 BREAK
		da = a, db = b;
		while (1) {
			if (tr[da].dep > tr[db].dep) da = tr[da].parent;
			else if (tr[da].dep < tr[db].dep) db = tr[db].parent;
			else {
				if (da == db) break;
				else {
					da = tr[da].parent;
					db = tr[db].parent;
				}
			}
		}

		cout << tr[a].dis + tr[b].dis - 2 * tr[da].dis << "\n";
	}

	return EXIT_SUCCESS;
}