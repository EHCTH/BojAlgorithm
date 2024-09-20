n, k = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(n)]
dp = [[0] * (k + 1) for _ in range(n + 1)]
for i in range(n):
    for j in range(k + 1):
        if arr[i][0] <= j:
            dp[i][j] = max(dp[i -1][j], dp[i - 1][j - arr[i][0]] +  arr[i][1])
        else:
            dp[i][j] = dp[i -1][j]
print(dp[n - 1][k])