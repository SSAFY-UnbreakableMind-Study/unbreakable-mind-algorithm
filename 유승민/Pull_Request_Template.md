## BOJ_5670_P4_휴대폰 자판
- 문자열, 트리, 트라이
- https://www.acmicpc.net/problem/5670


## 풀이

트라이 클래스를 잘 만드는게 중요했던 문제로
클래스 멤버변수로 단어가 끝나는지 체크하는 isEnd 와
단어 해싱을 위한 map 을 사용하였습니다.

<br>

```cpp
//트라이 클래스
class Trie {
	bool isEnd = false;
	map<char, Trie*> children;
}

```

이후 어떤 단어든 첫번째 글자는 무조건 입력해줘야 하기 때문에
checkFirst() 함수를 이용해 따로 체크해준후, 
두번째 단어부턴 문자열이 끝나거나, 해싱값이 2개 이상일 경우만
카운트를(여기선 변수명 sum) 1개씩 추가해주었습니다.

<br>

```cpp
//단어 삽입
	void insertWords(string& s, int idx) {}

//첫번째 단어는 수동 체크
	void checkFirst() {}

//두번째 단어 이후 자동 체크
	void findWords(double sum) {
		//마지막 단어일 경우 정답횟수 더하고, 버튼 누르는 횟수 더하기
		if (isEnd) {
			Ans += sum;
			sum++;
		}

		// 해싱값이 2개 이상인경우 버튼누르는 횟수만 더하기
		else if (children.size() > 1) sum++;
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

double Ans;

//트라이 클래스
class Trie {
	bool isEnd = false;
	map<char, Trie*> children;

public:
	//단어 삽입
	void insertWords(string& s, int idx) {
		if (s.size() == idx) {
			isEnd = true;
			return;
		}

		if (children.find(s[idx]) == children.end()) {
			children[s[idx]] = new Trie();
		}

		children[s[idx]]->insertWords(s, idx + 1);
	}

	//첫번째 단어는 수동 체크
	void checkFirst() {
		if (isEnd) Ans++;

		for (auto& iter : children) {
			iter.second->findWords(1);
		}
	}

	//두번째 단어 이후 자동 체크
	void findWords(double sum) {

		//마지막 단어일 경우 정답횟수 더하고, 버튼 누르는 횟수 더하기
		if (isEnd) {
			Ans += sum;
			sum++;
		}

		// 해싱값이 2개 이상인경우 버튼누르는 횟수만 더하기
		else if (children.size() > 1) sum++;

		//다음단어 탐색
		for (auto& iter : children) {
			iter.second->findWords(sum);
		}

	}

};

int main() {
	fastio;

	int N;
	while (cin >> N) {

		Trie* root = new Trie();

		//단어 입력받기
		for (int i = 0; i < N; ++i) {
			string s;
			cin >> s;
			
			root->insertWords(s, 0);
		}

		Ans = 0;

		//몇번 눌러야 하는지 체크
		root->checkFirst();

		printf("%.2f \n", Ans / N);

	}

	return EXIT_SUCCESS;
}
```


<br/>


## 결과 

| 메모리 | 시간 |
| ------ | ---- |
| 123468KB | 388ms |


