n, m = map(int, input().split())
arr = list(map(int, input().split()))
arr.sort()
answer = []


def solutions(idx, num):
    if idx == m:
        print(*answer)
    else:
        for i in range(num, n):
            answer.append(arr[i])
            solutions(idx + 1, i + 1)
            answer.pop()


solutions(0, 0)