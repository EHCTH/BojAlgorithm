n = int(input())
dp = [1] * 10
for T in range(n-1):
    for i in range(10):
        dp[i] = sum(dp[i::])
print(sum(dp) % 10007)