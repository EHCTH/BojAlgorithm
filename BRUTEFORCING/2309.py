a = 0
b = 0
l = [int(input()) for i in range(9)]

total = sum(l)

for i in range(9):
    for j in range(i + 1, 9):
        if total - (l[i] + l[j]) == 100:
            a = l[i]
            b = l[j]

l.remove(a)
l.remove(b)
l.sort()

for i in l:
    print(i)