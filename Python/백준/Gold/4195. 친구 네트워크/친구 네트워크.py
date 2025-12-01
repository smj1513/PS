import sys
sys.setrecursionlimit(10**6) # 재귀 깊이 제한 늘리기 (필수)

def find(a):
    global parent
    if parent[a] == a:
        return a
    else:
        parent[a] = find(parent[a])
        return parent[a]

def union(a, b):
    fa = find(a)
    fb = find(b)
    if fa != fb:
        parent[fb] = fa
        number[fa] += number[fb]

    return number[fa]

input = sys.stdin.readline
T = int(input())
for _ in range(T):
    seq = 0
    F = int(input())
    parent = {}
    number = {}
    for _ in range(F):
        p1, p2 = input().split()
        if p1 not in parent:
            parent[p1] = p1
            number[p1] = 1
        if p2 not in parent:
            parent[p2] = p2
            number[p2] = 1
        print(union(p1, p2))