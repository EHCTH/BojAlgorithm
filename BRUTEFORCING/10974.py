N = int(input())
answer = [[] for _ in range(N)]
visited = [False] * N
def solutions(n):
    if n == N:
        print(*answer[:n])
        return
    for i in range(N):
        if not visited[i]:
            visited[i] = True
            answer[n] = i + 1
            solutions(n + 1)
            visited[i] = False
solutions(0)