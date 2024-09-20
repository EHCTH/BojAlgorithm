N = int(input())
graph = [list(map(int, input().split())) for _ in range(N)]
answer = 1e9


def cal(start, link):
    start_sum, link_sum = 0, 0
    for i in range(N // 2):
        for j in range(N // 2):
            start_sum += graph[start[i]][start[j]]
            link_sum += graph[link[i]][link[j]]
    return abs(start_sum - link_sum)


def solutions(n, start, link):
    global answer
    if answer == 0:
        return

    if n == N:
        if len(start) == len(link):
            total = cal(start, link)
            answer = min(answer, total)
        return

    solutions(n + 1, start + [n], link)
    solutions(n + 1, start, link + [n])


solutions(0, [], [])
print(answer)