n, k = map(int, input().split())
def dfs(b, cnt):
    if b == n:
        return cnt + 1
    if b % 2 == 0:
        return dfs(b // 2, cnt + 1)
    elif '1' in str(b)[1:]:
        return dfs(int(str(b)[:-1]), cnt + 1)
    return -1
print(dfs(k, 0))