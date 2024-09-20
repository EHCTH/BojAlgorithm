N = int(input())
k = int(input())
arr = [int(input()) for _ in range(N)]
se = set()
answer = [[] for _ in range(k)]
vistied = [False] * N
def solutions(n):
    global answer
    if n == k:
        se.add(''.join(map(str, answer)))
        return
    for i in range(N):
        if not vistied[i]:
            vistied[i] = True
            answer[n] = arr[i]
            solutions(n + 1)
            vistied[i] = False
solutions(0)
print(len(se))