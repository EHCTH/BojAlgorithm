n = int(input())
arr = list(map(int, input().split()))
dp = [item for item in arr]
for i in range(len(arr)):
    for j in range(i):
        if arr[j] < arr[i]:
            dp[i] = max(dp[i], dp[j] + arr[i])
print(max(dp))