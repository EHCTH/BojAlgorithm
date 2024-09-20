n, m = map(int, input().split())
array = list(map(int, input().split()))
sum, end, cnt = 0, 0, 0
for start in range(len(array)):
    while sum < m and end < n:
        sum += array[end]
        end += 1
    if sum == m:
        cnt += 1
    sum -= array[start]
print(cnt)