num = int(input())
arr = list(str(num))
visited = [False] * len(arr)
answer = 1e9


def solutions(n, sm):
    global answer
    if n == len(arr):
        total = int(sm)
        if num < total and total < answer:
            answer = total
        return
    for i in range(len(arr)):
        if n == 0 and arr[n] == 0:
            continue
        if not visited[i]:
            visited[i] = True
            solutions(n + 1, sm + arr[i])
            visited[i] = False

solutions(0, '')
if answer == 1e9:
    print(0)
else:
    print(answer)