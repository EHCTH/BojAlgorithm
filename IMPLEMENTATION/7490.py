def solutions(n, sign, next, cur, s):
    if n == N:
        next += (cur * sign)
        if next == 0:
            print(s)
        return
    solutions(n + 1, sign, next, cur * 10 + (n + 1), s + ' ' + str(n + 1))
    solutions(n + 1, 1, next + (sign * cur), n + 1, s + '+' + str(n + 1))
    solutions(n + 1, -1, next + (sign * cur), n + 1, s + '-' + str(n + 1))


for T in range(int(input())):
    N = int(input())
    solutions(1, 1, 0, 1, '1')
    print()