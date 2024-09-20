n = int(input())
INF = int(1e9)
dp = [INF for i in range(n + 1)]
dp[0] = 0
for i in range(1, n + 1):
    if dp[i] == INF:
        dp[i] = min(dp[i - 3] + 1, dp[i - 5] + 1,dp[i])
if dp[n] == INF:
    print(-1)
else:
    print(dp[n])