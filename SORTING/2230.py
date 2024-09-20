n, m = map(int, input().split())
min_num = int(2e9)
arr = [int(input()) for _ in range(n)]
arr.sort()
start, end = 0, 0
while start <= end and end < len(arr):
    num = arr[end] - arr[start]
    if num < m:
        end += 1
    else:
        min_num = min(min_num, num)
        start += 1
print(min_num)