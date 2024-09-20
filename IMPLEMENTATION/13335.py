import collections
n, w, l = map(int, input().split())
truck = list(map(int, input().split()))
q_truck = collections.deque()
for i in truck:
    q_truck.append(i)

bridge = collections.deque([0] * w)
answer = 0
cur_weight = 0
while q_truck:
    answer += 1
    cur_weight -= bridge.popleft()
    if cur_weight + q_truck[0] <= l:
        cur_weight += q_truck[0]
        bridge.append(q_truck.popleft())
    else:
        bridge.append(0)
print(answer + len(bridge))