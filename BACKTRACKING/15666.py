n, m = map(int, input().split())
arr = list(map(int, input().split()))
arr.sort()
answer = [[] for _ in range(m)]

def solutions(idx, next):
    if idx == m:
        print(*answer)
    else:
        tmp = 0
        for i in range(next, n):
            if tmp != arr[i]:
                tmp = arr[i]
                answer[idx] = arr[i]
                solutions(idx + 1, i)

solutions(0, 0)