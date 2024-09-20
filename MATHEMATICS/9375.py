import collections
for T in range(int(input())):
    dict = collections.defaultdict(int)
    n = int(input())
    for _ in range(n):
        a, b = input().split()
        dict[b] += 1
    cnt = 1
    for i, v in dict.items():
        cnt *=  dict[i] + 1
    print(cnt - 1)