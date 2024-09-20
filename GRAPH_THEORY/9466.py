def solutions(v):
    cur = v
    while True:
        state[cur] = visited
        cur = arr[cur]
        if state[cur] == cycle or state[cur] == not_cycle:
            cur = v
            while state[cur] == visited:
                state[cur] = not_cycle
                cur = arr[cur]
            return

        if state[cur] == visited and cur != v:
            while state[cur] != cycle:
                state[cur] = cycle
                cur = arr[cur]
            cur = v
            while state[cur] != cycle:
                state[cur] = not_cycle
                cur = arr[cur]
            return

        if state[cur] == visited and cur == v:
            while state[cur] != cycle:
                state[cur] = cycle
                cur = arr[cur]
            return
for T in range(int(input())):
    n = int(input())
    arr = [-1] + list(map(int, input().split()))
    state = [0] * (n + 1)
    visited = 1
    cycle = 2
    not_cycle = 3
    answer = 0
    for i in range(1, n + 1):
        if not state[i]:
            solutions(i)
    for i in range(1, n + 1):
        if state[i] == not_cycle:
           answer += 1
    print(answer)
# 이 전에 2솔 코드는 틀린 코드 생각해보니 2번째 경우에 2 3 2 일 경우 1은 사이클이 아니지만 3 2 는 사이클 형성가능 
# 이 전 2솔 코드는 2까지는 고려하는데 그다음 cur = arr[cur]을 안해줘가지고 3은 고려를 안함