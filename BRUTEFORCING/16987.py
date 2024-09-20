N = int(input())
arr = []
for _ in range(N):
    s, w = map(int, input().split())
    arr.append([s, w])
answer = 0


def solutions(n):
    global answer
    if answer == len(arr):
        print(answer)
        exit(0)
    
    if n == N:
        cnt = 0
        for i in range(N):
            if arr[i][0] <= 0:
                cnt += 1
        answer = max(answer, cnt)
        return

    for i in range(N):
        if i != n:
            if arr[i][0] > 0 and arr[n][0] > 0:
                arr[n][0] -= arr[i][1]
                arr[i][0] -= arr[n][1]
                solutions(n + 1)
                arr[n][0] += arr[i][1]
                arr[i][0] += arr[n][1]
            else:
                solutions(n + 1)


solutions(0)
print(answer)