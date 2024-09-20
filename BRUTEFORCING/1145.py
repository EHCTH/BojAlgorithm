arr = list(map(int, input().split()))
min = min(arr)
while True:
    cnt = 0
    for i in arr:
        if min % i == 0:
            cnt += 1
    if cnt > 2:
        break
    min += 1
print(min)