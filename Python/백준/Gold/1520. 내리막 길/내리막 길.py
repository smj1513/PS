import sys
sys.setrecursionlimit(10 ** 6)

input = sys.stdin.readline
M, N = map(int, input().split())
grid = [list(map(int, input().split())) for _ in range(M)]
dp = [[-1 for _ in range(N)] for _ in range(M)]  # (x, y)에서 출발하여 목적지까지 도달할 수 있는 경로의 수
dx = (-1, 0, 1, 0)
dy = (0, -1, 0, 1)


def dfs(x, y):
    global dp
    global load_cnt
    if x == M - 1 and y == N - 1:
        return 1
    if dp[x][y] != -1:
        return dp[x][y]
    ways = 0
    for i in range(4):
        nx = dx[i] + x
        ny = dy[i] + y
        if 0 <= nx < M and 0 <= ny < N:
            if grid[nx][ny] < grid[x][y]:
                ways += dfs(nx, ny)
    dp[x][y] = ways
    return dp[x][y]


print(dfs(0, 0))
