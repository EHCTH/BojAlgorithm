import collections
dict = collections.defaultdict(int)
word = input().upper()
for i in word:
    dict[i] += 1
cnt = 0
l = []
for i, v in dict.items():
    if max(dict.values()) == v:
        cnt += 1
        l.append(i)
if cnt == 1:
    print(l[-1])
else:
    print("?")