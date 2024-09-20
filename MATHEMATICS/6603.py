def solutions(idx, next):
    if idx == 6:
        print(*answer[:len(arr)])
    else:
        for i in range(next, len(arr)):
            answer[idx] = arr[i]
            solutions(idx + 1, i + 1)

while True:
    arr = list(map(int, input().split()))
    answer = [[] for _ in range(6)]
    if arr[0] == 0:
        exit(0)
    arr = arr[1:]
    solutions(0, 0)
    print()