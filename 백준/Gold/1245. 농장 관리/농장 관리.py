N, M = map(int, input().split())
dx = [-1, 0, 1, 0, -1, 1, -1, 1]
dy = [0, -1, 0, 1, 1, -1 ,-1 ,1]
grid = [list(map(int, input().split())) for _ in range(N)]
visited = [[False for _ in range(M)] for _ in range(N)]
cnt = 0
isPeek = True
def dfs(x, y):
    global visited
    global grid
    global isPeek
    global N,M
    visited[x][y] = True
    for i in range(8):
        nx = dx[i] + x
        ny = dy[i] + y
        if 0 <= nx < N and 0 <= ny < M:
            if grid[x][y] < grid[nx][ny]:
                isPeek = False
            if visited[nx][ny] or grid[nx][ny] != grid[x][y]:
                continue
            dfs(nx, ny)

for i in range(N):
    for j in range(M):
        if not visited[i][j] :
            isPeek = True
            dfs(i, j)
            if isPeek:
                cnt+=1

print(cnt)