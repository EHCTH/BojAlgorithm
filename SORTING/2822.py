n = 8
li = []
li2 = []
for _ in range(n):
    li.append(int(input()))
res = sorted(li)[3:]
print(sum(res))
for i in res:
    li2.append(li.index(i) + 1)
print(*sorted(li2))