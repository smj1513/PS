import sys
sys.setrecursionlimit(100000)
N, M, K = map(int, input().split(" "))
dx = [-1 ,0, 1, 0]
dy = [0, -1, 0, 1]

visited = [[False for _ in range(M)] for _ in range(N)]
for _ in range(K):
    x1, y1, x2, y2 = map(int, input().split(" "))
    for i in range(x1, x2):
        for j in range(y1, y2):
            visited[j][i] += True

area = []

def dfs(x, y):
    global area_cnt
    global visited
    global area
    area_cnt += 1
    visited[x][y] = True
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        if 0 <= nx <N and 0<= ny <M and not visited[nx][ny]:
            dfs(nx, ny)

for i in range(N):
    for j in range(M):
        if not visited[i][j]:
            area_cnt = 0
            dfs(i, j)
            area.append(area_cnt)
print(len(area))
print(*sorted(area))