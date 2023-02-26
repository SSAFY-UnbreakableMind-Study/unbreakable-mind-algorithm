## PG*L3*단어변환

- DFS, BFS
- https://school.programmers.co.kr/learn/courses/30/lessons/43163?language=java

## 풀이

- 시작 단어에서 출발하여 주어진 단어 중 알파벳이 하나만 다른 단어와 교환하면서 타겟 단어를 만드는 최소 횟수를 구하는 문제
- dfs가 타고 간 깊이를 기록하며 방문하지 않은 노드 중에 알파벳이 하나만 다른 애를 탐색하며 찾기

```java

static void dfs (String cur, int depth) {
    if (target.equals(cur)) {
        answer = depth<answer?depth:answer;
        return;
    }

    for (int i=0; i<words.length; i++) {
        if (!isVisited[i]) {                //방문한 적 없고
            if (getDiff(cur, words[i])) {   //알파벳이 하나만 다른 애 골라서
                isVisited[i] = true;
                dfs(words[i], depth+1);
                isVisited[i] = false;
                //안에서 방문 체크 할 때는 다시 되돌려주기
            }
        }
    }
}

```

## 소스코드

```java
class Solution {
    static String[] words;
    static String begin, target;
    static boolean[] isVisited;
    static int answer = Integer.MAX_VALUE;

    public int solution(String b, String t, String[] temp) {
        begin = b;
        target = t;
        words = temp;
        isVisited = new boolean[words.length];
        dfs(begin, 0);
        if (answer==Integer.MAX_VALUE) answer=0;
        return answer;
    }
    static void dfs (String cur, int depth) {
        if (target.equals(cur)) {
            answer = depth<answer?depth:answer;
            return;
        }

        for (int i=0; i<words.length; i++) {
            if (!isVisited[i]) {                //방문한 적 없고
                if (getDiff(cur, words[i])) {   //알파벳이 하나만 다른 애 골라서
                    isVisited[i] = true;
                    dfs(words[i], depth+1);
                    isVisited[i] = false;
                    //안에서 방문 체크 할 때는 다시 되돌려주기
                }
            }
        }
    }
    //두 단어 간에 알파벳 다른 개수가 1개면 true 아니면 false
    static boolean getDiff(String from, String to) {
        int diff=0;
        for (int i=0; i<from.length(); i++) {
            if (from.charAt(i)!=to.charAt(i)) {
                diff++;
            }
        }
        return diff==1?true:false;
    }
}
```

## 결과

| 메모리  | 시간    |
| ------- | ------- |
| 72.8 MB | 0.03 ms |
| 81.7 MB | 0.07 ms |
| 73.7 MB | 0.47 ms |
| 76.0 MB | 0.03 ms |
| 76.2 MB | 0.04 ms |
