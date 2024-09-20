n = int(input())
cnt_1 = 0
for _ in range(n):
    a = int(input())
    if a == 0:
        cnt_1 += 1
    else:
        cnt_1 -= 1
if cnt_1 >= 0:
    print("Junhee is not cute!")
else:
    print("Junhee is cute!")