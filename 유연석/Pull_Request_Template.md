## PG*L3*네트워크

- DFS, BFS
- https://school.programmers.co.kr/learn/courses/30/lessons/43162

## 풀이

- 2차원 배열을 활용한 dfs, bfs 문제
- 방문한 적 없는 모든 노드에서 시작하는 dfs를 호출하며 그때마다 count++

```java

for (int i=0; i<n; i++) {
	if (!isVisited[i]) {
		dfs(i);
		answer++;
	}
}

```

## 소스코드

```java
import java.util.*;

class Solution {
    static int N;
    static int[][] coms;
    static int answer=0;
    static boolean[] isVisited;
    public int solution(int n, int[][] computers) {
        N=n;
        coms = computers;
        isVisited = new boolean[N];

        for (int i=0; i<n; i++) {
            if (!isVisited[i]) {
                dfs(i);
                answer++;
            }
        }

        return answer;
    }
    static void dfs(int cur) {
        isVisited[cur] = true;

        for (int i=0; i<N; i++) {
            if (coms[cur][i]==1 && !isVisited[i]) {
                dfs(i);
            }
        }
    }
}
```

## 결과

| 메모리  | 시간    |
| ------- | ------- |
| 74.2 MB | 0.02 ms |
