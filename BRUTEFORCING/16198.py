N = int(input())
arr = list(map(int, input().split()))
answer = -1e9

def solutions(n, sm):
    global answer
    if len(arr) == 2:
        answer = max(answer, sm)
        return
    for i in range(1, len(arr) - 1):
        total = arr[i - 1] * arr[i + 1]
        v = arr.pop(i)
        solutions(n + 1, sm + total)
        arr.insert(i, v)

solutions(0, 0)
print(answer)