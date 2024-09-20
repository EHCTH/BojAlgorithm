import sys
def solution(x):
    if x == 0 or x == 1:
        return False
    for i in range(2, int(n ** 0.5) + 1):
        if x % i == 0:
            return False
    return True
for _ in range(int(sys.stdin.readline())):
    n = int(sys.stdin.readline())
    while True:
        if solution(n):
            print(n)
            break
        else:
            n += 1