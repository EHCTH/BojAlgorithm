N = int(input())
arr = [list(map(int ,input().split())) for _ in range(N)]
max_num = -1e9
def solutions(n, price):
    global max_num
    if n >= N:
        max_num = max(price, max_num)
        return
    if n + arr[n][0] <= N:
        solutions(n + arr[n][0], price + arr[n][1])
    solutions(n + 1, price)
solutions(0, 0)
print(max_num)