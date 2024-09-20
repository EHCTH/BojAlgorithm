n = int(input())
for _ in range(n):
    li = list(map(int, input().split()))
    li.sort()
    print(li[-3])