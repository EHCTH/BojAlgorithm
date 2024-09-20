import collections, sys
n, m = map(int, sys.stdin.readline().split())
arr = [sys.stdin.readline().strip() for _ in range(m)]
dict = collections.defaultdict(int)
idx = 1
for i in range(m):
    dict[arr[i]] = idx
    idx += 1
answer = sorted(dict.items(), key = lambda x: x[1])
for i in answer[:n]:
    print(i[0])