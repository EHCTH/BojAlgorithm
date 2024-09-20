N = int(input())
arr = [list(map(int, input().split())) for _ in range(N)]
answer = 1e9
def solutions(n, sour, bitter):
    global answer
    if answer == 1:
        return
    if n == N:
        if sour != 1 and bitter:
            answer = min(answer, abs(sour - bitter))
        return
    solutions(n + 1, sour * arr[n][0], bitter + arr[n][1])
    solutions(n + 1, sour, bitter)
solutions(0, 1, 0)
print(answer)