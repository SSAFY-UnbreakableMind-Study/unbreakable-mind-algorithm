#include <bits/stdc++.h>
#define fastio cin.tie(0)->ios::sync_with_stdio(0); cout.tie(0); setvbuf(stdout, nullptr, _IOFBF, BUFSIZ);
#define INF INT32_MAX
// INT32_MIN, INT64_MIN, INT32_MAX, INT64_MAX
using namespace std;
using int64 = int64_t;

int64 N, A;
int64 Ans = 0;
vector<pair<int64, int64>> vp;

int main() {
	fastio;

	//사람 몇명인가
	cin >> N;

	//초기값 설정
	cin >> A;
	vp.push_back({ A, 1 });

	for (int i = 1; i < N; ++i) {
		cin >> A; //이번에 들어오는 사람의 키

		int idx = -1; // 키가 같은사람 판별용 변수

		for (int j = vp.size() - 1; j >= 0; --j) {

			//앞사람이 더 작은사람이면 pop
			if (vp[j].first < A) {
				Ans += vp[j].second;
				vp.pop_back();
			}

			//키가 같은사람이면 idx 에 위치 저장해놓음
			else if (vp[j].first == A) {
				Ans += vp[j].second;
				idx = j;
			}

			//키가 큰사람이면 한명밖에 못봄
			else {
				Ans++;
				break;
			}
		}

		// 이번에 들어왔던 사람 위치 저장
		if (idx == -1) vp.push_back({ A, 1 });
		else vp[idx].second++;
	}

	cout << Ans;

	return EXIT_SUCCESS;
}