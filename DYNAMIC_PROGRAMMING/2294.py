n, k= map(int, input().split())
arr = [int(input()) for _ in range(n)]
dp = [1e9] * (k + 1)
dp[0] = 0
for i in range(n):
    for j in range(arr[i], k + 1):
        dp[j] = min(dp[j - arr[i]] + 1, dp[j])
if dp[k] == 1e9:
    print(-1)
else:
    print(dp[k])