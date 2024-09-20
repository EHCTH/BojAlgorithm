def solutions(n):
    if n == 1:
        return 0
    for i in range(2, int(n ** (1/2)) + 1):
        if n % i == 0:
             return 0
    return 1

while True:
    n = int(input())
    cnt = 0
    if n == 0:
        break
    for i in range(n + 1, 2 * n + 1):
        cnt += solutions(i)
    print(cnt)