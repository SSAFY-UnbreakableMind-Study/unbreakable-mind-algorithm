## BOJ_27313_G3_효율적인 애니메이션 감상
- 그리디, 정렬
- https://www.acmicpc.net/problem/27313


## 풀이

정렬을 통해 애니메이션 감상 시간이 적게 걸리는 순서대로 정렬한 후,
그리디 알고리즘을 통해 문제를 해결하였습니다.

<br>


```cpp
// K번째보다 작은 i에 대하여 걸리는 최댓값은 v[i]
	for (int i = 0; i < K; ++i) {
		if (i >= v.size()) break;
		if (v[i] <= M) ans++;
		else break;
	}
```

<br>

```cpp
// K이상인 i에 대하여 걸리는 최댓값은 v[i] + v[i-K] 
	for (int i = K; i < v.size(); ++i) {
		if (v[i] + v[i - K] <= M) {
			ans++;
			v[i] += v[i - K];
		}
		else break;
	}
```


## 소스코드
```cpp
#include <bits/stdc++.h>
#define fastio cin.tie(0)->ios::sync_with_stdio(0); cout.tie(0); setvbuf(stdout, nullptr, _IOFBF, BUFSIZ);
#define INF INT32_MAX
// INT32_MIN, INT64_MIN, INT32_MAX, INT64_MAX
using namespace std;
using int64 = int64_t;

int64 ans;
vector<int64> v;

int main() {
	fastio;

	int64 N, M, K, Ans = 0;
	cin >> N >> M >> K;

	int64 A;
	for (int i = 0; i < N; ++i) {
		cin >> A;
		v.push_back(A);
	}

	sort(v.begin(), v.end());

	// K번째보다 작은 i에 대하여 걸리는 최댓값은 v[i]
	for (int i = 0; i < K; ++i) {
		if (i >= v.size()) break;
		if (v[i] <= M) ans++;
		else break;
	}

	// K이상인 i에 대하여 걸리는 최댓값은 v[i] + v[i-K] 
	for (int i = K; i < v.size(); ++i) {
		if (v[i] + v[i - K] <= M) {
			ans++;
			v[i] += v[i - K];
		}
		else break;
	}

	cout << ans;

	return EXIT_SUCCESS;
}
```


<br/>


## 결과 

| 메모리 | 시간 |
| ------ | ---- |
| 3692KB | 32ms |


