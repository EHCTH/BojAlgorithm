import collections
def func(k):
    if k == len(N):
        max_num = 0
        for i in range(len(arr)):
            max_num = max(max_num, int(arr[i]))

        if max_num == len(arr):
            for i in range(len(arr)):
                print(int(arr[i]), end=' ')
            exit(0)
        return
    if k < len(N) and int(N[k]) <= 50 and not visit[int(N[k])]:
        visit[int(N[k])] = 1
        arr.append(N[k])
        func(k + 1)
        arr.pop()
        visit[int(N[k])] = 0
    if k + 1 < len(N) and int(N[k:k + 2]) <= 50 and not visit[int(N[k:k + 2])]:
        visit[int(N[k:k + 2])] = 1
        arr.append(N[k:k + 2])
        func(k + 2)
        visit[int(N[k:k + 2])] = 0
        arr.pop()


arr = collections.deque()
N = input()
visit = [0] * 51
func(0)