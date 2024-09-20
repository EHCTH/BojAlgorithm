m ,n = list(map(int,input().split()))

if n < 45:
    if m == 0:
        m = 23
        n += 60
    else:
        m -= 1
        n += 60
print(m,n-45)