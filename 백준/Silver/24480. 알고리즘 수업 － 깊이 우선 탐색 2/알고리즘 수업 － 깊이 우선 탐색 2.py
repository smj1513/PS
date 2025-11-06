import sys
# setrecursionlimit는 이제 필요 없으므로 제거해도 됩니다.
# sys.setrecursionlimit(10**6) 

if __name__ == '__main__':
    read = sys.stdin.readline
    write = sys.stdout.write
    N, M, R = map(int, read().split())

    graph = [[] for _ in range(N + 1)]
    visited = [False] * (N + 1)
    order = [0 for _ in range(N+1)]
    cnt = 0

    # --- 1. 재귀 함수를 스택을 이용한 반복문으로 변경 ---
    def dfs_iterative(start_node):
        global cnt
        stack = [start_node] # 탐색할 노드를 담을 스택

        while stack:
            v = stack.pop() # 스택의 가장 위 노드를 꺼냄

            # 2. (중요) 스택에 중복으로 쌓일 수 있으므로, 
            #    꺼냈을 때 이미 방문했다면 건너뜀
            if visited[v]:
                continue
            
            # --- 방문 처리 ---
            visited[v] = True
            cnt += 1
            order[v] = cnt
            # -----------------

            # 3. (중요) 인접 노드를 스택에 추가
            #    기존 재귀와 동일한 순서(내림차순)로 방문하려면,
            #    정렬된 리스트의 '역순'으로 스택에 넣어야 함 (LIFO)
            for nv in reversed(graph[v]):
                if not visited[nv]:
                    stack.append(nv)
    # ----------------------------------------------------

    for _ in range(0, M):
        u, v = map(int, read().split())
        graph[u].append(v)
        graph[v].append(u)

    # 이 정렬 방식(내림차순)은 문제 요구사항이므로 그대로 둡니다.
    for edge in graph:
        edge.sort(reverse=True)

    # 재귀 함수 대신 새로 만든 반복 함수 호출
    dfs_iterative(R)
    
    for i in range(1, len(order)):
        write(str(order[i])+"\n")