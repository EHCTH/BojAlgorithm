n, k = map(int, input().split())
arr = [0] + [int(input()) for _ in range(n)]
dp = [0] * (k + 1)
dp[0] = 1
for i in range(1, len(arr)):
    for j in range(arr[i], k + 1):
        dp[j] = max(dp[j], dp[j - arr[i]] + dp[j])
print(dp[k])