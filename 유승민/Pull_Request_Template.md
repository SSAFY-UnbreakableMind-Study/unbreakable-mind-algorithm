## BOJ_7512_G3_연속하는 소수의 합
- 자료구조, 스택
- https://www.acmicpc.net/problem/3015


## 풀이

한사람씩 입력받으면서
마지막 사람부터 처음사람으로 탐색방향을 잡고
앞사람이 키가 더 큰 사람이면 탐색 종료 후 스택에 저장
앞사람이 키가 같은 사람이면 탐색 지속 후 스택에 저장
앞사람이 키가 작은 사람이면 앞사람을 스택에서 빼고 탐색 지속 후 저장하여
문제를 해결하였습니다.

<br>

```cpp
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
```

<br>



## 소스코드
```cpp
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
```


<br/>


## 결과 

| 메모리 | 시간 |
| ------ | ---- |
| 2020KB | 72ms |


