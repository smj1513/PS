import sys
from collections import deque
input = sys.stdin.readline

dx = (-1, 1, 0, 0)
dy = (0, 0, -1, 1)


def solve():
    N, M = map(int, input().split())
    grid = [list(map(int, input().split())) for _ in range(N)]

    #전체 맵을 매번 돌지 않기 위해 빙산 좌표만 별도 관리
    ice_list = []
    for i in range(N):
        for j in range(M):
            if grid[i][j] > 0:
                ice_list.append((i, j))

    year = 0

    while ice_list:
        # 1. 빙산 덩어리 체크 (덩어리가 분리되었는지 확인)
        # 전체 DFS/BFS를 돌 필요 없이, 첫 번째 빙산에서 연결된 것만 셈
        start_r, start_c = ice_list[0]
        visited_cnt = 1
        visited = set([(start_r, start_c)])  # set으로 방문 체크 속도 향상
        q = deque([(start_r, start_c)])

        while q:
            r, c = q.popleft()
            for i in range(4):
                nr, nc = r + dx[i], c + dy[i]
                # 범위 체크 생략 가능 (테두리는 항상 0이므로 빙산이 존재할 수 없음 -> 문제 조건 확인 필요하지만 보통 안전)
                if grid[nr][nc] > 0 and (nr, nc) not in visited:
                    visited.add((nr, nc))
                    visited_cnt += 1
                    q.append((nr, nc))

        #방문한 빙산 개수 < 전체 빙산 개수 -> 두 덩어리 이상으로 갈라짐
        if visited_cnt < len(ice_list):
            return year

        # 2. 빙산 녹이기
        melt_info = []  # (r, c, 녹을 양)
        del_idx = []  # 다 녹아서 사라질 빙산의 ice_list 내 인덱스 관리는 복잡하므로 새로 리스트 생성

        next_ice_list = []

        for r, c in ice_list:
            sea_count = 0
            for i in range(4):
                nr, nc = r + dx[i], c + dy[i]
                if grid[nr][nc] == 0:
                    sea_count += 1

            if sea_count > 0:
                melt_info.append((r, c, sea_count))

        # 녹은 결과 반영
        for r, c, amount in melt_info:
            grid[r][c] -= amount
            if grid[r][c] < 0:
                grid[r][c] = 0

        # 다 녹지 않고 남은 빙산만 다음 연도로 이월
        ice_list = [(r, c) for r, c in ice_list if grid[r][c] > 0]

        year += 1

        # 빙산이 다 녹았는데 분리되지 않은 경우
        if not ice_list:
            return 0
    return 0
print(solve())
