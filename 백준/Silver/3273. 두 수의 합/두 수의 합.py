n = int(input())
li = list(map(int, input().split(" ")))
x = int(input())

cnt = 0
start = 0
end = n-1
inter_sum = 0
li.sort()

while start < end:
    inter_sum = li[start] + li[end]
    if inter_sum == x:
        cnt += 1
        end -= 1
    elif inter_sum < x:
        start += 1
    else:
        end -=1
print(cnt)