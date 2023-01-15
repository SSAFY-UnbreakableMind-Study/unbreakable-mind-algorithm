## 알고리즘 기록

- 알고리즘 스터디 기록을 위한 저장소입니다.
- 초기설정과 팁은 SetUp&Tips 폴더를 참고해주세요.

### ✔Rule

- **일주일 목표 문제량 : 4문제**
- **문제 풀이 : 월요일 08 : 40 전까지**
- **코드 리뷰는 자율적으로 PR을 활용하여 진행합니다**

- **플랫폼 통일**
  - **BOJ : 백준**
  - **PG : 프로그래머스**
  - **LTC : 리트코드**
  - **CFS : 코드포스**
  - **SWEA : 삼성 SW Expert Academy**

### ✔Commit & Pull Request(PR) Convention
- **한 문제 해결 시 Commit & PR을 진행 해주세요**

```jsx
Add : 문제 푼뒤
Fix : 코드 수정
Remove : 파일 삭제
```

- **문제를 풀고 난 뒤**

```jsx
git commit -m "유승민 : BOJ_7916_P5_CrossSpider_Add
```

- **이미 커밋한 코드 수정 시**

```jsx
git commit -m "유승민 : BOJ_7916_P5_CrossSpider_Fix_탐색 방법 수정"
```

- **파일 삭제 시**

```jsx
git commit -m "유승민 : BOJ_7916_P5_CrossSpider_Remove"
```

- **PR 제목은 다음과 같이 통일합니다.**
  - **이름 : 문제플랫폼_문제번호_문제등급_문제이름_분류(Add or Fix or Remove) 중 하나**

```jsx
유승민 : BOJ_7916_P5_CrossSpider_Add
```

- **PR 내용은 코드 리뷰로 활용해주세요**
  - **양식은 Pull_Request_Template.md 참고**


### ✔File Convention

- **문제풀이는 각자 개인 환경에서 작업하고 제출파일 이름은 다음과 같이 구성합니다.**

  - **플랫폼_문제번호_문제등급_문제이름.확장자**

```jsx
BOJ_7916_P5_CrossSpider.java
```


### ✔Code Convention

- **변수와 함수 이름 작명 시 역할을 알기 쉽도록 간단한 주석을 덧붙입니다.**

```jsx
// dfs로 타켓을 찾는 함수
static void findTarget(){
	...
}
```


### ✔ 참고자료
- [언어 도움말](https://www.acmicpc.net/help/language)
- [언어별 입력 속도 비교](https://www.acmicpc.net/blog/view/56)
- [언어별 출력 속도 비교](https://www.acmicpc.net/blog/view/57)



### 🏃🏻‍♂️week 0
|Type | 문제 | 제목 | 유형 | 등급|
|--- | --- | --- | --- | ---|
| BOJ | 9251 | [LCS](https://www.acmicpc.net/problem/9251) | DP | G5 |
| BOJ | 9461 | [파도반 수열](https://www.acmicpc.net/problem/9461) | DP | S3 |


### 🏃🏻‍♂️week 1

|Type | 문제 | 제목 | 유형 | 등급|
|--- | --- | --- | --- | ---|
| BOJ | 1920 | [수 찾기](https://www.acmicpc.net/problem/1920) | 이분탐색 | S4 |
| BOJ | 14501 | [퇴사](https://www.acmicpc.net/problem/14501) | 브루트포스 | S3 |
| BOJ | 3190 | [뱀](https://www.acmicpc.net/problem/3190) | 덱/큐 | G4 |
| BOJ | 2667 | [단지 번호 붙이기](https://www.acmicpc.net/problem/2667) | 그래프 탐색 | S1 |




