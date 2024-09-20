import collections
n, k = map(int, input().split())
max_value = 500001
visited = [[False] * max_value for _ in range(2)]

def solutions():
    cnt = 0
    queue = collections.deque()
    queue.append((n, 0))
    visited[0][n] = True
    while queue:
        cnt += 1
        sis, sec = queue.popleft()
        brother = k + sec * (sec + 1) // 2
        if brother >= max_value:
            return -1
        if visited[sec % 2][brother]:
            return sec
        sec += 1
        for next in [sis + 1, sis - 1, sis * 2]:
            if 0 <= next < max_value and not visited[sec % 2][next]:
                visited[sec % 2][next] = True
                queue.append((next, sec))
    return -1

print(solutions())