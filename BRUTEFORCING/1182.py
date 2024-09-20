n, m = map(int, input().split())
arr = list(map(int, input().split()))
answer = 0
def solutions(cnt, sum):
    global answer
    if cnt == n:
        if sum == m:
            answer += 1
        return
    solutions(cnt + 1, sum + arr[cnt])
    solutions(cnt + 1, sum)

solutions(0, 0)
if not m:
    print(answer - 1)
else:
    print(answer)