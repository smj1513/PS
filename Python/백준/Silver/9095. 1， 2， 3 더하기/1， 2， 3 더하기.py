T = int(input())
for _ in range(T):
    N = int(input())
    dp = [1] * (N + 1)  # N을 1, 2, 3으로 나타낼 수 있는 최대 개수
    if N == 0:
        print(0)
    elif N == 1:
        print(1)
    elif N == 2:
        print(2)
    elif N == 3:
        print(4)
    elif N == 4:
        print(7)
    else:
        dp[0] = 0
        dp[1] = 1  # 1
        dp[2] = 2  # 1+1, 2
        dp[3] = 4  # 1+1+1, 1+2, 2+1, 3
        dp[4] = 7  # 1+1+1+1, 1+1+2, 1+2+1, 2+1+1, 2+2, 1+3, 3+1
        for a in range(5, N + 1):
            dp[a] = dp[a - 1] + dp[a - 2] + dp[a - 3]
        print(dp[N])
