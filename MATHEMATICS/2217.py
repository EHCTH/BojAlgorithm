n = int(input())
w = [int(input()) for _ in range(n)]
w.sort()
answer = 0
for i in range(1, n + 1):
    answer = max(answer, w[n - i] * i)
print(answer)