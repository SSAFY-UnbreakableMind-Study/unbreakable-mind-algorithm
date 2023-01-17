## BOJ_9251_G5_LCS
- DP
- https://www.acmicpc.net/problem/9251



## 풀이

해당 문제는 2차원 배열을 활용해 풀 수 있다. <br/>
2차원 배열을 돌면서 dp[i][j] 번째 값을 dp[i - 1][j] 과 dp[i][j - 1] 를 비교하여 큰값으로 초기화 해준다. <br/>
추가로 string b[i - 1] string a[j - 1] 값이 같을 경우  <br/>
dp[i - 1][j - 1] 까지 비교하여 가장 큰 값으로 dp[i][j]를 초기화 해준다. <br/>

~~~java
for (int64 i = 1; i <= b.length(); ++i) {
		for (int64 j = 1; j <= a.length(); ++j) {
			dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);
			if (b[i - 1] == a[j - 1]) dp[i][j] = max(dp[i][j], dp[i - 1][j - 1] + 1);
		}
	}
~~~



## 소스코드
~~~java
#include <bits/stdc++.h>
#define fastio cin.tie(0)->ios::sync_with_stdio(0); cout.tie(0); setvbuf(stdout, nullptr, _IOFBF, BUFSIZ);
#define INF 109876543210
using namespace std;
using int64 = int64_t;
//BOJ_9251_G5_LCS
//5940KB, 4ms

int dp[1001][1001]; // dp 배열
string a, b; //문자열 a,b

int main() {
	fastio;

	cin >> a >> b;	//a,b 입력받기

	//dp[i][j] 칸의 값은 dp[i - 1][j] 와 dp[i][j - 1] 중 큰것으로 초기화
	//두 문자가 같은 칸의 경우 추가로 dp[i - 1][j - 1] + 1 까지 비교하여 초기화
	for (int64 i = 1; i <= b.length(); ++i) {
		for (int64 j = 1; j <= a.length(); ++j) {
			dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);
			if (b[i - 1] == a[j - 1]) dp[i][j] = max(dp[i][j], dp[i - 1][j - 1] + 1);
		}
	}

	cout << dp[b.length()][a.length()]; // 초기화 끝난 dp 출력

	return EXIT_SUCCESS;
}
~~~


<br/>



## 결과 

| 메모리  | 시간 |
|----|----|
| 5940KB| 4ms|

