n = int(input())
cnt = 1
cnt_2 = 1
while cnt < n:
    cnt += (cnt_2 * 6)
    cnt_2 += 1
print(cnt_2)