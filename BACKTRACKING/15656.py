n, m = map(int, input().split())
arr = list(map(int, input().split()))
arr.sort()
answer = []
def solution(idx):
    if m == idx:
        print(*answer)
    else:
        for i in range(n):
            answer.append(arr[i])
            solution(idx + 1)
            answer.pop()
solution(0)