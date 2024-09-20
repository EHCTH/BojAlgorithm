N = int(input())
arr = list(map(int, input().split()))
answer = 0
visited = [False] * N
li = []


def solutions(n):
    global answer
    if n == N:
        sm = 0
        for i in range(N - 1):
            sm += abs(li[i] - li[i + 1])
        answer = max(answer, sm)
        return
    for i in range(N):
        if not visited[i]:
            visited[i] = True
            li.append(arr[i])
            solutions(n + 1)
            li.pop()
            visited[i] = False


solutions(0)
print(answer)