n = int(input())
li = []
for _ in range(n):
    a, b = map(int, input().split())
    li.append((a, b))
li.sort(key = lambda x : (x[1], x[0]))
for i in li:
    print(i[0], i[1])