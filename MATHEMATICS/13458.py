import math
n = int(input())
a = list(map(int, input().split()))
b, c = map(int, input().split())
answer = 0
for i in a:
    i -= b
    answer += 1
    if i > 0:
        answer += math.ceil((i / c))
print(answer)