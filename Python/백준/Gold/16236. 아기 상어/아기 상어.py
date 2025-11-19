import sys
from collections import deque

input = sys.stdin.readline
dx = (-1, 0, 1, 0)
dy = (0, -1, 0, 1)

N = int(input())
board = []
cur_x, cur_y = 0, 0

for i in range(N):
    row = list(map(int, input().split()))
    board.append(row)
    for j in range(N):
        if board[i][j] == 9:
            cur_x, cur_y = i, j
            board[i][j] = 0  # 상어가 있던 자리는 빈 칸으로 변경

shark_size = 2
eat_cnt = 0
total_time = 0


def bfs(x, y, size):
    # 거리 저장용 배열 (-1로 초기화)
    dist = [[-1] * N for _ in range(N)]
    queue = deque([(x, y)])
    dist[x][y] = 0

    candidates = []  # 먹을 수 있는 물고기 리스트

    while queue:
        cx, cy = queue.popleft()

        for i in range(4):
            nx = cx + dx[i]
            ny = cy + dy[i]

            # 범위 내이고, 방문하지 않았으며, 지나갈 수 있는 경우(작거나 같음)
            if 0 <= nx < N and 0 <= ny < N and dist[nx][ny] == -1:
                if board[nx][ny] <= size:
                    dist[nx][ny] = dist[cx][cy] + 1
                    queue.append((nx, ny))

                    # 먹을 수 있는 물고기인지 확인 (0이 아니고, 사이즈보다 작음)
                    if 0 < board[nx][ny] < size:
                        # (거리, 행, 열) 순으로 저장
                        candidates.append((dist[nx][ny], nx, ny))

    candidates.sort(key=lambda k: (k[0], k[1], k[2]))
    return candidates


while True:
    fish_list = bfs(cur_x, cur_y, shark_size)

    if not fish_list:
        break

    distance, nx, ny = fish_list[0]

    cur_x, cur_y = nx, ny  # 상어 이동
    total_time += distance  # 이동 시간 추가
    eat_cnt += 1  # 먹은 횟수 증가
    board[nx][ny] = 0  # 물고기 사라짐

    if eat_cnt == shark_size:
        shark_size += 1
        eat_cnt = 0

print(total_time)