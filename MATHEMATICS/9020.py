# 마법사 상어와 복제 내일 다시 풀어보자
def decimal(n):
    if n == 1:
        return False
    for i in range(2, int(n ** 0.5) + 1):
        if n % i == 0:
            return False
    return True


T = int(input())
for _ in range(T):
    num = int(input())
    a = num // 2
    b = num // 2
    for _ in range(num // 2):
        if decimal(a) and decimal(b):
            print(a, b)
            break
        else:
            a -= 1
            b += 1