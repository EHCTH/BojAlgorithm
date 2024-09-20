import collections, sys
n, m = map(int, sys.stdin.readline().split())
dict = collections.defaultdict()
for _ in range(n):
    key, value = sys.stdin.readline().split()
    dict[key] = value
for i in range(m):
    print(dict[sys.stdin.readline().strip()])