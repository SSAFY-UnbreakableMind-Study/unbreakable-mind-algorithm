## ✔레포지터리 Fork 하기
- **Unbreakable_Mind_Algorithm 레포지터리로 이동**
- **우측 상단 Fork 클릭후 Create fork 클릭**
- **자신의 깃허브에 fork된 Unbreakable_Mind_Algorithm 레포지터리로 이동**
- **가운데 초록색 <>Code 클릭**
- **https로 시작하는 web URL 복사**

<br/>



## ✔Fork된 레포지터리 연결
- **적당한 곳에 SSAFY_Algorithm 폴더 생성**
- **SSAFY_Algorithm 폴더 안에서 마우스 우클릭 Code(으)로 열기 실행**
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
      ㄴ BOJ_7916_P5_CrossSpider.java;
김싸피
   ㄴ week1
      ㄴ BOJ_7916_P5_CrossSpider.java;
```

<br/>



## ✔문제 풀이후 Commit & Pull Request 하기
- **git remote -v 명령어로 리모트 저장소 확인**




