import collections
# print(0b0011 ^ 0b0100) 7
# print(0b0011 ^ 1 << 2) 7

# print(0b0011 ^ (1 << 19)) 524291
# print(0b0011 ^ (1 << 20)) 1048579


n = int(input())
m = int(input())
arr = list(map(int, input().split()))
distance = [-1e9] * 1000001
queue = collections.deque()
for i in arr:
    distance[i] = 0
    queue.append(i)
def solutions(queue):
    while queue:
        x = queue.popleft()
        for i in range(20):
            nx = x ^ (1 << i)
            if 0 <= nx <= n and distance[nx] == -1e9:
                distance[nx] = distance[x] + 1
                queue.append(nx)
    return max(distance)

print(solutions(queue))