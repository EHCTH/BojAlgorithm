li = []
for i in range(5):
    data = int(input())
    li.append(data)
li.sort()

a = sum(li) // 5
b = li[5//2]
print(a)
print(b)