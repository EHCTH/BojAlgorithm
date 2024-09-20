n, m = map(int, input().split())

def solutions(d):
    for i in range(2, int(d ** (1 / 2) + 1)):
        if d % i == 0:
            return False
    return True
for i in range(n, m + 1):
    if i == 1:
        continue
    if solutions(i):
        print(i)