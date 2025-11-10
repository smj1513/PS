import sys

input = sys.stdin.readline

n, m = map(int, input().split())

prices = []
for _ in range(m):
    prices.append(int(input()))

prices.sort()

max_revenue = 0
best_price = 0

for i in range(m):

    current_price = prices[i]

    num_customers = len(prices[i:])
    sellable_count = min(n, num_customers)

    current_revenue = current_price * sellable_count

    if current_revenue > max_revenue:
        max_revenue = current_revenue
        best_price = current_price

print(best_price, max_revenue)