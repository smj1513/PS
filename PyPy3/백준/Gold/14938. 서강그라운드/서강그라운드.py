import sys
import heapq

input = sys.stdin.readline

N, M, R = map(int, input().split())
values = list(map(int, input().split()))
values.insert(0, 0)  # 1-based 인덱싱

graph = [[] for _ in range(N + 1)]
for _ in range(R):
    a, b, w = map(int, input().split())
    graph[a].append((b, w))
    graph[b].append((a, w))

INF = float('inf')


def dijkstra(start):
    # 1. 거리 배열 초기화
    dist = [INF] * (N + 1)
    dist[start] = 0

    # 2. 우선순위 큐 (min-heap)
    pq = [(0, start)]  # (비용, 노드)

    while pq:
        current_cost, current_node = heapq.heappop(pq)

        # 이미 처리된 노드(더 짧은 경로를 찾은 경우)는 무시
        if current_cost > dist[current_node]:
            continue

        for neighbor, weight in graph[current_node]:
            new_cost = current_cost + weight
            # 3. 더 짧은 경로 발견 시 갱신
            if new_cost < dist[neighbor]:
                dist[neighbor] = new_cost
                heapq.heappush(pq, (new_cost, neighbor))

    # 4. 최단 거리 계산 완료 후 아이템 합산
    current_sum = 0
    for i in range(1, N + 1):
        if dist[i] <= M:
            current_sum += values[i]

    return current_sum


max_value = 0
for i in range(1, N + 1):
    max_value = max(max_value, dijkstra(i))

print(max_value)