from collections import deque as dq
import copy
import sys

input = sys.stdin.readline

graph = []

n, m = map(int, input().split())
dx = [0,0,1,-1]
dy = [1,-1,0,0]

for i in range(n):
    graph.append(list(map(int, input().split())))

count = 0
max_value = 0

def bfs():
    global max_value
    queue = dq()
    for i in range(n):
        for j in range(m):
            if graph[i][j] == 2:
                queue.append([i,j])
    samplegraph = copy.deepcopy(graph)
    while(queue):
        x, y = queue.pop()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if (nx < n and ny < m and nx >= 0 and ny >= 0 and samplegraph[nx][ny] == 0):
                samplegraph[nx][ny] = 2
                queue.append([nx, ny])
    total = 0
    for q in range(n):
        for w in range(m):
            if samplegraph[q][w] == 0:
                total += 1
    max_value = max(max_value, total)

def dfs():
    global count
    global total
    if count == 3:
        bfs()
        return
    for i in range(n):
        for j in range(m):
            if(graph[i][j] == 0):
                graph[i][j] = 1
                count = count + 1
                dfs()
                graph[i][j] = 0
                count = count - 1

def main():
    dfs()
    print(max_value)


if __name__ == "__main__":
    main()
