N = int(input())
s = input().split()
visited = [False] * 10
max_arr = ''
min_arr = ''

def check_cal(pre, cur, s):
    if s == '<':
        return pre < cur
    else:
        return pre > cur

def solutions(n, arr):
    global max_arr, min_arr
    if n == (N + 1):
        if not min_arr:
            min_arr = arr
        else:
            max_arr = arr
        return

    for i in range(10):
        if not visited[i]:
            if not n or check_cal(int(arr[-1]), i, s[n - 1]):
                visited[i] = True
                solutions(n + 1, arr + str(i))
                visited[i] = False
solutions(0, '')
print(max_arr)
print(min_arr)