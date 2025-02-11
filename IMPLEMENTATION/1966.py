import collections
T = int(input())
for _ in range(T):
    N, M = map(int, input().split())
    queue = collections.deque(map(int, input().split()))
    queue = collections.deque([(value, idx) for idx, value in enumerate(queue)])

    count = 0
    while True:
        if queue[0][0] == max(queue, key=lambda x: x[0])[0]:
            count += 1
            if queue[0][1] == M:
                print(count)
                break
            else:
                queue.popleft()
        else:
            queue.append(queue.popleft())