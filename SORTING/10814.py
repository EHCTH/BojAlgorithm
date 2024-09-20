n = int(input())
li = []
for _ in range(n) :
    a,b = input().split()
    li.append((a ,b))
for i in sorted(li, key = lambda x : int(x[0])):
    print(i[0], i[1])