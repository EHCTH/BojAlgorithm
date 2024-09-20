import sys
n, m = map(int, sys.stdin.readline().split())
arr = [0] * (n + 1)
for i in range(m):
    k, s, e = map(int, sys.stdin.readline().split())
    if arr[k] <= s:
        arr[k] = e
        print('YES')
    else:
        print('NO')