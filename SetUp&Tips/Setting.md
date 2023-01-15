## ✔레포지터리 Fork 하기
- **Unbreakable_Mind_Algorithm 레포지터리로 이동**
- **우측 상단 Fork 클릭후 Create fork 클릭**
- **자신의 깃허브에 fork된 Unbreakable_Mind_Algorithm 레포지터리로 이동**
- **가운데 초록색 <>Code 클릭**
- **https로 시작하는 web URL 복사**

<br/>



## ✔Fork된 레포지터리 연결
- **적당한 곳에 Unbreakable_Mind_Algorithm 폴더 생성**
- **Unbreakable_Mind_Algorithm 폴더 안에서 마우스 우클릭 Code(으)로 열기 실행**
- **Ctrl + J 로 TERMINAL 창 실행**
- **git remote -v 명령어로 리모트 저장소 확인**

 ```jsx
git remote -v 
```

<br/>

- **명령어 실행 후 아무것도 뜨지 않을시 다음단계 진행**
- **만약 연결되있는 저장소가 있다면 git remote remove 명령어로 연결 해제**

 ```jsx
git remote remove <name>
```

<br/>

- **연결 해제 확인 후 위에서 복사한 web URL 주소를 이용, git clone <주소> .  명령어 실행**
- **<주소> 뒤에 띄어쓰기 + . 이 들어감에 유의하자**

 ```jsx
git remote add origin <주소> .
```

<br/>

- **git remote -v 명령어로 리모트 저장소 확인**

 ```jsx
git remote add origin <주소>
```

<br/>

- **origin https://github.com/블라블라 가 뜨면 다음단계 진행**

<br/>



## ✔형식에 맞게 폴더 생성후 문제 풀이
```jsx
[예시]
유승민
   ㄴ week1
      ㄴ BOJ_7916_P5_CrossSpider.java
김싸피
   ㄴ week1
      ㄴ BOJ_7916_P5_CrossSpider.java
```

<br/>



## ✔문제 풀이후 Commit 하기
- **문제 풀이 후 Commit 하기전 자신의 깃허브 Fork된 레포지터리로 이동**
- **<>Code 밑에있는 Sync fork 클릭**

<br/>

- **Update branch가 활성화 되어있을시 클릭**
- **그 후 vscode로 이동하여 git pull origin main 명령어 실행**

 ```jsx
git pull origin main
```

<br/>

- **Update branch가 비활성화 되어있을시엔 여기부터 진행**
- **문제 풀이 후 Commit 을 위해 차례대로 명령어 실행**

 ```jsx
git add .
```

<br/>

- **git commit -m "이곳은 README.md 참고하여 작성"**
 ```jsx
git commit -m "유승민 : BOJ_7916_P5_CrossSpider_Add"
```

<br/>

 ```jsx
git push origin main"
```

<br/>


## ✔Commit 후 Pull Request(PR) 하기
- **Commit 후 자신의 깃허브 Fork된 레포지터리로 이동**
- **<>Code 밑에있는 Contribute 클릭**
- **Open pull request 클릭**
- **Title은 README.md 참고하여 형식에 맞게 작성**

```jsx
유승민 : BOJ_7916_P5_CrossSpider_Add
```

<br/>
 
- **Leave a comment 는 Pull_Request_Template.md 참고하여 형식에 맞게 코드 리뷰용으로 작성**
- **모든 작성이 끝났으면 Create pull request 클릭**

<br/>
<br/>
<br/>

- **Leave a comment 예시**

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




