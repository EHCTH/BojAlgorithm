import collections

n = int(input())
arr = [input().split() for _ in range(n)]
dict = collections.defaultdict(int)
for i in range(n):
    if arr[i][1] == 'enter':
        dict[arr[i][0]] += 1
    else:
        dict[arr[i][0]] -= 1
for x, y in sorted(dict.items(), reverse=True):
    if y == 1:
        print(x)