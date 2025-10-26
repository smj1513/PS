T = int(input())
for t in range(T):
    N = int(input())
    prices = list(map(int, input().split()))

    max_profit = 0
    max_price = prices[N - 1] 

    for i in range(N - 2, -1, -1):
        if prices[i] < max_price:
            max_profit += (max_price - prices[i])
        else:
            max_price = prices[i]

    print(max_profit)