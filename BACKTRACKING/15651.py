n, m = map(int, input().split())
arr = [[] for _ in range(m)]
visited = [False] * (n + 1)


def solutions(cnt):
    if cnt == m:
        print(*arr[:m])
    else:
        for i in range(1, n + 1):
            arr[cnt] = i
            solutions(cnt + 1)


solutions(0)