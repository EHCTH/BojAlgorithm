e,s,m = map(int, input().split())
cnt = 1
while True:
    if (cnt - e) % 15 == 0 and  (cnt - s) % 28 == 0 and (cnt - m) % 19 == 0:
        print(cnt)
        break
    cnt += 1