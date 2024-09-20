import collections
queue = [collections.deque() for _ in range(5)]
for i in range(1, 4 + 1):
    data = list(map(int, input()))
    queue[i].extend(data)
k = int(input())
dict = {1: [2], 2: [1, 3], 3: [2, 4], 4: [3]}
def rotate(number, dir):
    if not visited[number]:
        visited[number] = True
        copy_queue = [i for i in queue[number]]
        if dir == 1:
            x = queue[number].pop()
            queue[number].appendleft(x)
            if len(dict[number]) == 2:
                cur1 = queue[dict[number][0]][2]
                cur2 = queue[dict[number][1]][8 - 2]
                cur_1, cur_2 = False, False
                if cur1 != copy_queue[8 - 2]:
                    cur_1 = True
                if cur2 != copy_queue[2]:
                    cur_2 = True

                if cur_1 and cur_2:
                    rotate(dict[number][0], -1)
                    rotate(dict[number][1], -1)
                    return
                elif cur_1:
                    rotate(dict[number][0], -1)
                    return
                elif cur_2:
                    rotate(dict[number][1], -1)
                    return
            else:
                if number == 1:
                    cur = queue[dict[number][0]][8 - 2]
                    if cur != copy_queue[2]:
                        rotate(dict[number][0], -1)
                        return
                else:
                    cur = queue[dict[number][0]][2]
                    if cur != copy_queue[8 - 2]:
                        rotate(dict[number][0], -1)
                        return
        else:
            x = queue[number].popleft()
            queue[number].append(x)
            if len(dict[number]) == 2:
                cur1 = queue[dict[number][0]][2]
                cur2 = queue[dict[number][1]][8 - 2]
                cur_1, cur_2 = False, False
                if cur1 != copy_queue[8 - 2]:
                    cur_1 = True
                if cur2 != copy_queue[2]:
                    cur_2 = True

                if cur_1 and cur_2:
                    rotate(dict[number][0], 1)
                    rotate(dict[number][1], 1)
                    return
                elif cur_1:
                    rotate(dict[number][0], 1)
                    return
                elif cur_2:
                    rotate(dict[number][1], 1)
                    return
            else:
                if number == 1:
                    cur = queue[dict[number][0]][8 - 2]
                    if cur != copy_queue[2]:
                        rotate(dict[number][0], 1)
                        return
                else:
                    cur = queue[dict[number][0]][2]
                    if cur != copy_queue[8 - 2]:
                        rotate(dict[number][0], 1)
                        return
for _ in range(k):
    visited = [False] * (5)
    number, dir = map(int, input().split())
    rotate(number, dir)
answer = 0
for i in range(1, len(queue)):
    if queue[i][0] == 1:
        answer += (2 ** (i - 1))
print(answer)