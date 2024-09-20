n = int(input())
dp = [0] * (10 ** 6 + 1)
next_move = [0] * (10 ** 6 + 1)
dp[2] = 1
dp[3] = 1
next_move[2] = 1
next_move[3] = 1
for i in range(4, n + 1):
    dp[i] = dp[i - 1] + 1
    next_move[i] = i - 1
    if i % 3 == 0 and dp[i // 3] + 1 < dp[i]:
        dp[i] = min(dp[i // 3] + 1, dp[i])
        next_move[i] = i // 3
    if i % 2 == 0 and dp[i // 2] + 1 < dp[i]:
        dp[i] = min(dp[i // 2] + 1, dp[i])
        next_move[i] = i // 2
print(dp[n])
cur = n
while True:
    print(cur, end=" ")
    if cur == 1:
        break
    cur = next_move[cur]