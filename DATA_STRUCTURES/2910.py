import collections
n,c = map(int,input().split())
arr = list(map(int,input().split()))
dict = collections.defaultdict(int)
idx = 0

for i in arr:
    dict[i] += 1
answer = sorted(dict.items(), key = lambda x: -x[1])
for key, value in answer:
    for i in range(value):
        print(key, end=" ")