n, m = map(int, input().split())
arr = list(map(int ,input().split()))
arr.sort()
answer = []
visited = [False]* (n)

def solutions(idx):
    if idx == m:
        print(*answer)
    else:
        tmp = 0
        for i in range(n):
            if tmp != arr[i] and not visited[i]:
                tmp = arr[i]
                visited[i] = True
                answer.append(arr[i])
                solutions(idx + 1)
                visited[i]= False
                answer.pop()


solutions(0)