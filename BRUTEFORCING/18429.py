N, K = map(int, input().split())
arr = list(map(int, input().split()))
weight = 500
answer = 0
visited = [False] * N
def solutions(n, sm):
    global weight, answer
    if sm < 500:
        return
    if n == N:
        answer += 1
        return
    for i in range(N):
        if not visited[i]:
            visited[i] = True
            solutions(n + 1, sm + arr[i] - K)
            visited[i] = False
solutions(0, weight)
print(answer)