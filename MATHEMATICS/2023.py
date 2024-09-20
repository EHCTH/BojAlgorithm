N = int(input())
answer = []


def check(d):
    d = int(d)
    if d == 1:
        return False
    for i in range(2, int(d ** (1/2)) + 1):
        if d % i == 0:
            return False
    return True


def solutions(n, word):
    if n == N:
        answer.append(word)
    for i in range(1, 10):
        if check(word + str(i)):
            solutions(n + 1, word + str(i))

solutions(0, '')
print("\n".join(answer))