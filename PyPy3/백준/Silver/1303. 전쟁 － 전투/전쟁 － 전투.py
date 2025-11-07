W, H = map(int, input().split())
dx = (-1, 0, 1, 0)
dy = (0, -1, 0, 1)
grid = []
visited = [[False for _ in range(W)] for _ in range(H)]
for _ in range(H):
    grid.append(list(input()))


blue_power = 0
white_power = 0

def dfs(x, y):
    global blue_cnt
    global white_cnt
    global visited
    if not visited[x][y]:
        if grid[x][y] == 'W':
            white_cnt += 1
        else:
            blue_cnt += 1
    visited[x][y] = True

    for i in range(4):
        nx = dx[i] + x
        ny = dy[i] + y
        if 0 <= nx < H and 0 <= ny < W :
            if not visited[nx][ny] and grid[x][y] == grid[nx][ny]:
                dfs(nx, ny)

for i in range(H):
    for j in range(W):
        blue_cnt = 0
        white_cnt = 0
        dfs(i, j)
        blue_power += (blue_cnt ** 2)
        white_power += (white_cnt ** 2)
print(white_power, blue_power)