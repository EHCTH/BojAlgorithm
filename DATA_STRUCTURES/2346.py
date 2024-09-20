import collections
n = int(input())
queue = collections.deque(enumerate(map(int, input().split())))
answer = [1]
v = queue.popleft()
def solutions(queue, v):
    while queue:
        if 0 < v[1]:
            for _ in range(1, v[1]):
                queue.append(queue.popleft())
            v = queue.popleft()
        else:
            for _ in range(1, -v[1]):
                queue.appendleft(queue.pop())
            v = queue.pop()
        answer.append(v[0] + 1)
solutions(queue, v)
print(*answer)