## BOJ_1761_P5_정점들의 거리
- 트리, 최소 공통 조상
- https://www.acmicpc.net/problem/1761



## 풀이

트리 구조체를 생성후 입력을 먼저 받았습니다.
그 후 루트노드를 임의로 1번 정점으로 설정 후,
루트노드까지의 거리, 트리에서의 깊이, 해당 노드의 부모노드를 설정 해주었습니다.

~~~java
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
~~~


그 후 거리를 측정할 두 노드를 입력 받고,
두 노드간의 깊이가 다르면 높은쪽의 깊이를 한단계 낮춰줍니다(부모노드로 이동)

깊이가 같으면 두 노드가 같은 부모노드를 가지고 있는지 확인해줍니다.
이때 부모노드가 다르면 두 노드 모드 깊이를 낮춰줍니다.

~~~java
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
~~~


## 소스코드
~~~java
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

tree tr[40001];

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
~~~


<br/>



## 결과 

| 메모리  | 시간 |
|----|----|
| 6032KB| 1068ms|


