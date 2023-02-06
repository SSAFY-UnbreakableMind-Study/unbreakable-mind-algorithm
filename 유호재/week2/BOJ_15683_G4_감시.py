import copy
min_value = int(1e9)
n, m = map(int, input().split())
graph1 = []

camerainfo = []
cameradir = [[],
             [[0],[1],[2],[3]],
             [[0,2],[1,3]],
             [[0,1],[1,2],[2,3],[0,3]],
             [[0,1,2],[0,1,3],[1,2,3],[0,2,3]],
             [[0,1,2,3]]]
dx = [-1,0,1,0]
dy = [0,1,0,-1]

for i in range(n):
    graph1.append(list(map(int, input().split())))
    for j in range(m):
        if graph1[i][j] != 0 and graph1[i][j] != 6:
            camerainfo.append([graph1[i][j],i,j])

def run(graph, cdir, x, y):
    for i in cdir:
        nx = x
        ny = y
        while True:
            nx += dx[i]
            ny += dy[i]
            if nx <0 or ny <0 or nx >= n and ny >= m:
                break
            if graph[nx][ny] == 6:
                break
            elif graph[nx][ny] == 0:
                graph[nx][ny] = 7

def dfs(depth, graphtemp):
    global min_value
    if depth == len(camerainfo):
        count = 0
        for i in range(n):
            count += graphtemp[i].count(0)
        min_value = min(min_value, count)
        return
    temp = copy.deepcopy(graphtemp)
    camerano, x, y = camerainfo[depth]
    for i in cameradir[camerano]:
        run(temp, i, x, y)
        dfs(depth+1, temp)
        temp = copy.deepcopy(graphtemp)

def main():
    dfs(0,graph1)
    print(min_value)


if __name__ == "__main__":
    main()
