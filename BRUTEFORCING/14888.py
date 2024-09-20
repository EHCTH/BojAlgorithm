n = int(input())
array = list(map(int,input().split()))
add, sub, mul, div = map(int, input().split())
max_sum = -1e9
min_sum = 1e9


def solution(now, next):
    global max_sum, min_sum, add, sub, mul, div
    if next == n:
        min_sum = min(now, min_sum)
        max_sum = max(now, max_sum)

    else:
        if add != 0:
            add -= 1
            solution(now + array[next], next + 1)
            add += 1
        if sub != 0:
            sub -= 1
            solution(now - array[next], next + 1)
            sub += 1
        if mul != 0:
            mul -= 1
            solution(now * array[next], next + 1)
            mul += 1
        if div != 0:
            div -= 1
            solution(int(now / array[next]), next + 1)
            div += 1
    return max_sum, min_sum


num = solution(array[0], 1)
for i in num:
    print(i)