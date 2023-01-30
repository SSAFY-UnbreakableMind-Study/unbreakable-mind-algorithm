## BOJ_11053_S2_가장긴증가하는부분수열
- dp
- https://www.acmicpc.net/problem/11053



## 풀이
1차원 배열을 하나씩 탐색하며 현재 인덱스 앞의 값들을 모두 탐색
value는 작은 값들 중 count가 가장 큰 값의 count에 1을 더하여 현재 인덱스에 저장

~~~java
for j in range(i):
            if graph[j] < graph[i]:
                max_count = max(max_count, count[j])
        count[i] = max_count +1
~~~

## 소스코드
~~~java

def main():
    n = int(input())
    graph = list(map(int, input().split()))
    count = [0]*n
    for i in range(n):
        max_count = 0
        for j in range(i):
            if graph[j] < graph[i]:
                max_count = max(max_count, count[j])
        count[i] = max_count +1
    print(max(count))


if __name__ == "__main__":
    main()

~~~

## 결과 
pypy
| 메모리  | 시간 |
|----|----|
| 30616 KB| 92ms|

