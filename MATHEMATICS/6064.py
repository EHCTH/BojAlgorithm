def gcd(a, b):
    if b == 0:
        return a
    return gcd(b, a % b)

def lcm(a, b):
    return a // gcd(a, b)  * b

for T in range(int(input())):
    m, n ,x, y = map(int, input().split())
    answer = -1
    if m == x:
        x =0
    if n == y:
        y = 0
    l = lcm(m, n)
    for i in range(x, l+ 1, m):
        if i == 0:
            continue
        if i % n == y:
            answer = i
            break
    print(answer)