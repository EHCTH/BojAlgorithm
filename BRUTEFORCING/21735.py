N, M = map(int, input().split())
arr = [0] + list(map(int, input().split()))
answer = -1e9
def solutions(n, sm, idx):
    global answer
    if n > M:
        return
    if n <= M:
        answer = max(answer, sm)
    if idx + 1 <= N:
        solutions(n + 1, sm + arr[idx + 1], idx + 1)
    if idx + 2 <= N:
        solutions(n + 1, (sm // 2)+ arr[idx + 2], idx + 2)

solutions(0, 1, 0)
print(answer)