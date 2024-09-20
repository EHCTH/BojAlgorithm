for T in range(int(input())):
    n = int(input())
    arr = [0] + list(map(int, input().split()))
    m = int(input())
    dp = [0] * (m + 1)
    dp[0] = 1
    for i in range(1, n + 1):
        for j in range(arr[i], m + 1):
            dp[j] = max(dp[j - arr[i]] + dp[j], dp[j])
    print(dp[m])