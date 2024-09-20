n = int(input())
setLi = {'ChongChong'}

for _ in range(1, n+1):
    a, b = input().split()

    if a in setLi:
        setLi.add(b)

    if b in setLi:
        setLi.add(a)

print(len(setLi))