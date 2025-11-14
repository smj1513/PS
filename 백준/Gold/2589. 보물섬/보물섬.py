H, W = map(int, input().split())
dx = (-1, 0, 1, 0)
dy = (0, -1, 0, 1)
island = [list(input()) for _ in range(H)]
max_length = 0


def bfs(x, y):
    global max_length
    queue = [(x, y)]
    visited = [[-1 for _ in range(W)] for _ in range(H)]
    visited[x][y] = 0
    while len(queue) > 0:
        x, y = queue.pop(0)
        for i in range(4):
            nx = dx[i] + x
            ny = dy[i] + y
            if 0 <= nx < H and 0 <= ny < W and visited[nx][ny] == -1 and island[nx][ny] == 'L':
               queue.append((nx, ny))
               visited[nx][ny] = visited[x][y] + 1
    for vi in visited:
        max_length = max(max_length, max(vi))

for i in range(H):
    for j in range(W):
        if island[i][j] == 'L':
            bfs(i, j)
print(max_length)
