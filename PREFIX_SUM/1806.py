n, m = map(int,input().split())
array = list(map(int, input().split()))
INF = int(1e9)
min_size = INF

sum, end, cnt = 0, 0, 0
for start in range(len(array)):
    while sum < m and end < n:
        sum += array[end]
        end += 1
    if sum >= m:
        min_size = min(min_size, end - start)
    sum -= array[start]

if min_size == INF:
    print(0)
else:
    print(min_size)