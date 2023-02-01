import sys
sys.setrecursionlimit(10**7)
for _ in range(int(input())):
    m, n, k = map(int, input().split())
    graph = [[0]*(m) for i in range(n)]
    count = 0
    for i in range(k):
        b, a = map(int, input().split())
        graph[a][b] = 1
    dx = [0,0,1,-1]
    dy = [1,-1,0,0]
    def dfs(x, y):
        if x >=n or y >= m or x < 0 or y < 0 or graph[x][y] == 2:
            return
        if graph[x][y] == 1:
            graph[x][y] = 2
            for i in range(4):
                nx = x + dx[i]
                ny = y + dy[i]
                dfs(nx, ny)
        return

    for i in range(n):
        for j in range(m):
            if graph[i][j] == 1:
                count += 1
                dfs(i, j)
    print(count)