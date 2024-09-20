n, m= map(int, input().split())

ar = [i for i in range(1,n+1)]

for _ in range(m):
    i,j = map(int, input().split())
    ar[i-1], ar[j-1] = ar[j-1], ar[i-1]

for i in range(n):
    print(ar[i], end = ' ')