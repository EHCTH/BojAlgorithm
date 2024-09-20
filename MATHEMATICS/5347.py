n = int(input())
def gcd(a, b):
    if b == 0:
        return a
    return gcd(b, a % b)

def lcm(a, b):
    return a // gcd(a, b) * b

for _ in range(n):
    a, b = map(int, input().split())
    print(lcm(a, b))