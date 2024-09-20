import collections, sys
n, m  = map(int, sys.stdin.readline().split())
dict1 = collections.defaultdict(int)
dict2 = dict()
for i in range(1, n + 1):
    word = sys.stdin.readline().strip()
    dict1[word] = i
    dict2[i] = word
for _ in range(m):
    a = sys.stdin.readline().strip()
    if a.isdigit():
        print(dict2[int(a)])
    else:
        print(dict1[a])