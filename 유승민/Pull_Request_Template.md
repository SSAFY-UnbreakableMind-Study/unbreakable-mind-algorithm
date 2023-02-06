## BOJ_1759_G5_암호 만들기
- 조합, 브루트포스
- https://www.acmicpc.net/problem/1759


## 풀이

암호가 오름차순이라는 조건을 충족하기 위해 
정렬을 한번 해주었고, 그 이후
재귀를 통한 조합을 이용해 문제를 해결하였습니다.

<br>


```cpp
//조합
inline void Solve(int64 S, int64 rm, int64 am, int64 pos) {
	...
	...

	//재귀를 통한 조합 구현
	for (int64 i = pos; i < v.size(); ++i) {

		// 모음인 경우
		if (v[i] == 'a' || v[i] == 'e' || v[i] == 'i' || v[i] == 'o' || v[i] == 'u') {
			v1.push_back(v[i]);
			Solve(S - 1, rm - 1, am, i + 1);
			v1.pop_back();
		}

		//자음인 경우
		else {
			v1.push_back(v[i]);
			Solve(S - 1, rm, am - 1, i + 1);
			v1.pop_back();
		}
	}
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

vector<char> v, v1;

//조합
inline void Solve(int64 S, int64 rm, int64 am, int64 pos) {
	if (rm < 0)rm = 0; // 골라진 모음의 숫자가 최소조건을 충족시 0으로 고정
	if (am < 0) am = 0; // 골라진 자음의 숫자가 최소조건을 충족시 0으로 고정
	if (S - rm - am < 0)return; // 백트래킹 (중요)
	else if (S == 0) { // 전부다 골랐으면 출력
		for (auto _iter : v1) {
			cout << _iter;
		}
		cout << "\n";
		return;
	}

	//재귀를 통한 조합 구현
	for (int64 i = pos; i < v.size(); ++i) {

		// 모음인 경우
		if (v[i] == 'a' || v[i] == 'e' || v[i] == 'i' || v[i] == 'o' || v[i] == 'u') {
			v1.push_back(v[i]);
			Solve(S - 1, rm - 1, am, i + 1);
			v1.pop_back();
		}

		//자음인 경우
		else {
			v1.push_back(v[i]);
			Solve(S - 1, rm, am - 1, i + 1);
			v1.pop_back();
		}
	}
}

int main() {
	fastio;

	int64 L, C;

	cin >> L >> C;
	for (int64 i = 0; i < C; ++i) {
		char a;
		cin >> a;
		v.push_back(a);
	}

	//정렬시 a c i s t w 순서가 됨
	sort(v.begin(), v.end());

	// L = 총 골라야 하는 숫자
	// 1 = 최소 골라야 하는 모음의 숫자 (a, e, i, o u)
	// 2 = 최소 골라야 하는 자음의 숫자 (나머지)
	// 0 = 몇번째 index 부터 탐색해야 하는가
	Solve(L, 1, 2, 0);

	return EXIT_SUCCESS;
}
```


<br/>


## 결과 

| 메모리 | 시간 |
| ------ | ---- |
| 2024KB | 0ms |


