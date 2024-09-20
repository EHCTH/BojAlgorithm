n, m = map(int, input().split())
array = list(map(int, input().split()))
answer= [[] for _ in range(n)]
visited = [False] * 10001
array.sort()
def solutions(num):
    if num == m:
        print(*answer[:m])
    else:
        for i in range(n):
            if not visited[i]:
                visited[i] = True
                answer[num] = array[i]
                solutions(num + 1)
                visited[i] = False
solutions(0)