n, m = map(int, input().split())
arr = list(map(int, input().split()))
arr.sort()
answer = []
def solution(idx, next):
    if m == idx:
        print(*answer)
    else:
        for i in range(next, n):
            answer.append(arr[i])
            solution(idx + 1, i)
            answer.pop()
solution(0, 0)