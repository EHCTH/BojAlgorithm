n = int(input())
arr = list(map(int, input().split()))
cnt = 0
for i in range(n):
    if arr[i] == cnt % 3:
        cnt += 1
print(cnt)