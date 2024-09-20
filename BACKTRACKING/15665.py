n, m = map(int, input().split())
arr = list(map(int, input().split()))
arr.sort()
answer = [[] for _ in range(m)]

def solutions(idx):
    if idx == m:
        print(*answer)
    else:
        tmp = 0
        for i in range(n):
            if tmp != arr[i]:
                tmp = arr[i]
                answer[idx] = arr[i]
                solutions(idx + 1)

solutions(0)