N, k = map(int, input().split())
li = []
def solutions(sm):
    if sum(sm) > N:
        return
    if sum(sm) == N:
        li.append(sm)
        return
    for i in range(1, 3 + 1):
        solutions(sm + [i])
solutions([])
li.sort()
try:
    print("+".join(map(str, li[k - 1])))
except:
    print(-1)