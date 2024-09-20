N = int(input())
arr = list(map(int, input().split()))
add, sub, mul, div = map(int, input().split())
min_num, max_num = 1e9, -1e9


def solutions(n, total):
    global add, sub, mul, div, min_num, max_num
    if n == N:
            min_num = min(min_num, total)
            max_num = max(max_num, total)
            return
    else:
        if add != 0:
            add -= 1
            solutions(n + 1, total + arr[n])
            add += 1
        if sub != 0:
            sub -= 1
            solutions(n + 1, total - arr[n])
            sub += 1
        if mul != 0:
            mul -= 1
            solutions(n + 1, total * arr[n])
            mul += 1
        if div != 0:
            div -= 1
            solutions(n + 1, int(total / arr[n]))
            div += 1


solutions(1, arr[0])
print(max_num)
print(min_num)