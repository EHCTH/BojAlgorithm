n = int(input())
a = list(map(int, input().split()))
b = list(map(int, input().split()))
a.sort()

answer = 0
for i in range(n):
    answer += a[i] * max(b)
    b.remove(max(b))
print(answer)