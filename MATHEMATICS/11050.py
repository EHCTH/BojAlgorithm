n, k = map(int, input().split())
result = [1] * 100

for i in range(1, n + 1):
    result[i] = result[i - 1] * i
# nCr = n! / {(n-k)! * (k!)}
print(result[n] // (result[n - k] * result[k]))