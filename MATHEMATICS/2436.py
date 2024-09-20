min_sum = 200000000
g, l = map(int, input().split())
div = l // g
def gcd(a, b):
    if a % b == 0:
        return b
    return gcd(b, a % b)


for a in range(1, int(div ** (1 / 2)) + 1):
    b = int(div / a)
    if div % a == 0 and gcd(a, b) == 1:
        if b - a < min_sum:
            min_sum = b - a
            answer = f"{a*g} {b*g}"
print(answer)