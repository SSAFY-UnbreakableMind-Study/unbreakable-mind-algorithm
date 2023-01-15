## BOJ 9461 파도반수열
- DP
- https://www.acmicpc.net/problem/9461



## 풀이

i번째 삼각형은 i-1 번째 삼각형과 i - 5 번째 삼각형을 더하여 구할 수 있다

~~~java
for (int64 i = 17; i < 101; ++i) {
		dp[i] = dp[i - 1] + dp[i - 5];
	}
~~~

## 소스코드
~~~java
#include <bits/stdc++.h>
#define fastio cin.tie(0)->ios::sync_with_stdio(0); cout.tie(0); setvbuf(stdout, nullptr, _IOFBF, BUFSIZ);
#define INF 109876543210
using namespace std;
using int64 = int64_t;

int main() {
	fastio;
	int64 N, M, dp[101] = { 0, 1, 1, 1, 2, 2, 3, 4, 5, 7, 9, 12, 16, 21, 28, 37, 49 };
	cin >> N;

	// i 번째 삼각형을 i - 1 번째 삼각형화 i - 5 번째 삼각형을 더한 값으로 갱신한다.
	for (int64 i = 17; i < 101; ++i) {
		dp[i] = dp[i - 1] + dp[i - 5];
	}

	for (int64 i = 0; i < N; ++i) {
		cin >> M;
		cout << dp[M] << "\n";
	}

	return EXIT_SUCCESS;
}
~~~

## 결과 

| 메모리  | 시간 |
|----|----|
| 2020KB| 0ms|