n, k = map(int, input().split())
def solutions(n):
    for i in range(2, int((n ** (1 / 2))) + 1):
        if n % i == 0:
            return False
    return True

answer = []
for i in range(2, n + 1):
    if solutions(i):
        for j in range(i, n + 1, i):
            if j not in answer:
                answer.append(j)
print(answer[k - 1])