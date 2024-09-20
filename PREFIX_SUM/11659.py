import sys
input = sys.stdin.readline
n, k = map(int, input().split())
arr = [0] + list(map(int, input().split()))
dp = [0] * (n + 1)
for i in range(1, n + 1):
    dp[i] = dp[i - 1] + arr[i]
for _ in range(k):
    a, b = map(int, input().split())
    print(dp[b] - dp[a - 1])