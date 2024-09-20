n, m = map(int, input().split())
array = list(map(int, input().split()))
array.sort()
answer = [[] for _ in range(n)]

def solutions(nums, next):
    if nums == m:
        print(*answer[:m])
    else:
        tmp = 0
        for i in range(next, n):
            if tmp != array[i]:
                tmp = array[i]
                answer[nums] = array[i]
                solutions(nums + 1, i + 1)


solutions(0, 0)