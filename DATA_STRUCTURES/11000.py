import heapq
import sys
n = int(input())
q = [list(map(int ,sys.stdin.readline().split())) for _ in range(n)]
q.sort()
h = []
heapq.heappush(h, q[0][1])
for i in range(1, n):
    if q[i][0] < h[0]:
        heapq.heappush(h, q[i][1])
    else:
        heapq.heappop(h)
        heapq.heappush(h, q[i][1])
print(len(h))