a, b = input().split()
answer = -1
visited = [False] * len(a)
def solutions(n, sm):
    global answer
    if n >= 1:
        if int(sm[0]) == 0:
            return
    if n == len(a):
        if int(sm) < int(b):
            answer = max(answer, int(sm))
        return
    for i in range(len(a)):
        if not visited[i]:
            visited[i] = True
            solutions(n + 1, sm + str(a[i]))
            visited[i] = False
solutions(0, '')
print(answer)