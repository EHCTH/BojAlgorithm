def gcd(a, b):
  if b == 0:
    return a
  return gcd(b, a % b)
  
def lcm(a, b):
    return a * b // gcd(a, b)

num = int(input())

for _ in range(num):
    a, b = map(int, input().split())
    print(lcm(a, b))