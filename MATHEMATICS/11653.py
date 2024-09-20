n = int(input())
for i in range(2, int(n ** (1/2) + 1)):
    while n % i == 0:
        print(i)
        n //= i
if n != 1: #N이 소수일경우
    print(n)