import sys
import collections
n = int(input())

queue = collections.deque()
for i in range(n):
    num = list(map(int, sys.stdin.readline().strip().split()))
    l = len(queue)
    if num[0] == 1:
        queue.appendleft(num[1])
    elif num[0] == 2:
        queue.append(num[1])
    elif num[0] == 3:
        print(queue.popleft() if l else -1)
    elif num[0] == 4:
        print(queue.pop() if l else -1)
    elif num[0] == 5:
        print(len(queue))
    elif num[0] == 6:
        print(0 if l else 1)
    elif num[0] == 7:
        print(queue[0] if l else -1)
    elif num[0] == 8:
        print(queue[-1] if l else -1)