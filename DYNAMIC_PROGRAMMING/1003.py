for T in range(int(input())):
    n = int(input())
    fibo = [0] * 41
    fibo[0] = 1
    fibo[1] = 1
    for i in range(2, n + 1):
        fibo[i] = fibo[i - 1] + fibo[i - 2]
    if n == 0:
        print(1, 0)
    else:
        print(fibo[n - 2], fibo[n - 1])