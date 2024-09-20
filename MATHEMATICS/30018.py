n = int(input())
a = list(map(int, input().split()))
b = list(map(int, input().split()))
cnt = 0
for i in range(len(a)):
    if a[i] > b[i]:
       cnt += a[i] - b[i]
print(cnt)