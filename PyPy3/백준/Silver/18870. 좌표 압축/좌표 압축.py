import sys
import bisect
input = sys.stdin.readline
N = int(input())
arr = list(map(int, input().split()))
arr2 = sorted(list(set(arr)))
for i in range(N):
    pos = bisect.bisect_left(arr2, arr[i])
    print(pos, end=' ')