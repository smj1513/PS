from collections import deque
import sys

input = sys.stdin.readline
N, M = map(int, input().split())
graph = [[] for _ in range(N + 1)]
for i in range(1, N):
    a, b, dis = map(int, input().split())
    graph[a].append((b, dis))
    graph[b].append((a, dis))

def bfs(start, end):
    queue = deque([])
    queue.append((start, 0))
    visited = [False] * (N+1)
    visited[start] = True
    while queue:
        v, v_dis = queue.popleft()
        if v == end:
            return v_dis

        for nv, nv_dis in graph[v]:
            if not visited[nv]:
                queue.append((nv,nv_dis + v_dis))
                visited[nv] = True


for i in range(M):
    start, end = map(int, input().split())
    print(bfs(start, end))