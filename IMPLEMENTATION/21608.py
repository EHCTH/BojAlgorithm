import collections
n = int(input())
graph = [[0] * n for _ in range(n)]
students_list = [[] for _ in range(n ** 2 + 1)]
order_list = []
for _ in range(n ** 2):
    student = list(map(int, input().split()))
    students_list[student[0]].extend(student[1:])
    order_list.append(student[0])

# 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
# 1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
# 2를 만족하는 칸도 여러 개인 경우에는 행의 번호가 가장 작은 칸으로, 그러한 칸도 여러 개이면 열의 번호가 가장 작은 칸으로 자리를 정한다.
dx = [1, 0, -1, 0]
dy = [0, -1, 0, 1]
dict = {}
for cur in range(n ** 2):
    x, y = cur // n, cur % n
    dict[cur] = (x, y)

def choice_space_graph(cur_student):
    best_friend = [0] * (n ** 2)
    space_graph = [-1] * (n ** 2)
    for cur in range(n ** 2):
        x, y = cur // n, cur % n
        if graph[x][y]:
            continue
        space_graph[cur] = 0
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if 0 <= nx < n and 0 <= ny < n:
                if not graph[nx][ny]:
                    space_graph[cur] += 1
                if graph[nx][ny] in students_list[cur_student]:
                    best_friend[cur] += 1
    max_value = max(best_friend)
    max_list = choice_best_friends(best_friend, max_value)
    return dict[max_list[0]] if len(max_list) == 1 else dict[best_friend_space(max_list, space_graph)]

def choice_best_friends(best_friends, max_value):
    max_list = []
    for i in range(n ** 2):
        if max_value == best_friends[i]:
            max_list.append(i)
    return max_list

def best_friend_space(max_list, space_graph):
    result = [0, -1]
    for i in max_list:
        if space_graph[i] > result[1]:
            result[0] = i
            result[1] = space_graph[i]
    return result[0]

def solutions(cur_student):
    nx, ny = choice_space_graph(cur_student)
    graph[nx][ny] = cur_student

for index, student in enumerate(order_list):
    solutions(student)
answer = 0
cnt_list = [0, 1, 10 ,100, 1000]
for cur in range(n ** 2):
    x, y = cur // n , cur % n
    cnt = 0
    for i in range(4):
        nx, ny = x + dx[i], y + dy[i]
        if 0 <= nx < n and 0 <= ny < n and graph[nx][ny] in students_list[graph[x][y]]:
            cnt += 1
    answer += cnt_list[cnt]
print(answer)