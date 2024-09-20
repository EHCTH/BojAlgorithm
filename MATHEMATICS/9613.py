import itertools
def gcd(a, b):
    if b == 0:
        return a
    return gcd(b, a % b)
for T in range(int(input())):
    arr = list(map(int, input().split()))
    N = arr[0]
    arr = arr[1:]
    li = itertools.combinations(arr, 2)
    answer = 0
    for x, y in li:
        answer += gcd(x, y)
    print(answer)