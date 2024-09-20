a = list(map(int, input().split()))
b = list(map(int, input().split()))
def gcd(a, b):
    if b == 0:
        return a
    return gcd(b, a % b)
gcd_value = gcd(a[0] * b[1] + b[0] * a[1], a[1] * b[1])
print((a[0] * b[1] + b[0] * a[1]) // gcd_value, a[1] * b[1] // gcd_value, sep="\n")