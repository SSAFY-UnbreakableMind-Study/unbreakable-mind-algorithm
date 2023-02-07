#include <bits/stdc++.h>
#define fastio cin.tie(0)->ios::sync_with_stdio(0); cout.tie(0); setvbuf(stdout, nullptr, _IOFBF, BUFSIZ);
#define INF INT64_MAX
// INT32_MIN, INT64_MIN, INT32_MAX, INT64_MAX
using namespace std;
using int64 = int64_t;

int N, M;
int depth[100001];
int rArr[100001][18];
vector<int> v[100001];

//공통 조상 찾는 부분
inline int LCA(int a, int b) {

	// 왼쪽 깊이가 오른쪽보다 클 경우
	if (depth[a] > depth[b]) { 
		a = LCA(rArr[a][(int)log2(depth[a] - depth[b])], b);
	}

	//오른쪽 깊이가 왼쪽보다 클 경우
	else if (depth[a] < depth[b]) {
		a = LCA(a, rArr[b][(int)log2(depth[b] - depth[a])]);
	}

	//깊이는 같으나 서로 다른노드를 참조하는 경우
	else if (a != b) {
		for (int i = 0; i < 18; ++i) {
			if (rArr[a][i] == rArr[b][i]) {
				if (i == 0) return rArr[a][i];
				else return LCA(rArr[a][i - 1], rArr[b][i - 1]);
			}
		}
	}

	return a;
}

//희소배열을 이용한 부모노드 파악 
inline void setRArr(int dep, int parent, int cur) {
	rArr[cur][0] = parent;
	depth[cur] = dep;

	for (int i = 1; i < 18; ++i) {
		rArr[cur][i] = rArr[rArr[cur][i - 1]][i - 1]; // dp를 활용한 초기화
	}

	for (auto& iter : v[cur]) {
		if(iter != parent) setRArr(dep + 1, cur, iter);
	}
}

int main() {
	fastio;

	cin >> N;

	//트리 정보 저장
	for (int i = 0; i < N - 1; ++i) {
		int a, b;
		cin >> a >> b;

		v[a].push_back(b);
		v[b].push_back(a);
	}

	setRArr(1, 1, 1); //희소배열을 이용한 부모노드 파악 
	
	cin >> M;

	//LCA 찾기
	for (int i = 0; i < M; ++i) {
		int a, b;
		cin >> a >> b;

		cout << LCA(a, b) << "\n";
	}

	return EXIT_SUCCESS;
}