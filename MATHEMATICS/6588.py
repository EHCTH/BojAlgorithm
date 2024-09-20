# // Python
# arr1 = [1, 2, 3, 4, 5]
# arr2 = [6, 7, 8, 9, 10]
# rst = list(map(lambda x1, x2: x1 + x2, arr1, arr2))
# print(rst)
import sys
input = sys.stdin.readline
n = 1000001
decimal_list = [True] * n
decimal_list[0] = False
decimal_list[1] = False

for i in range(2, int(n * 1/2)):
    if decimal_list[i]:
        for j in range(i * i, n, i):
            decimal_list[j] = False

while True:
    num = int(input())
    if not num:
        break
    a = 0
    b = num
    is_find = False
    while a <= b:
        if decimal_list[a] and decimal_list[b]:
            print(f"{num} = {a} + {b}")
            is_find = True
            break
        a += 1
        b -= 1
    if not is_find:
        print("\"Goldbach's conjecture is wrong.\"")