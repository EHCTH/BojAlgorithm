n, m = map(int, input().split())
sharkX, sharkY = n // 2, n // 2
graph = [list(map(int, input().split())) for _ in range(n)]
blizzard_magic = [[0, 0]] + [list(map(int, input().split())) for _ in range(m)]
matrix_change_array = {}
steps = [(0, 0), (-1, 0), (1, 0), (0, -1), (0, 1)]
answer = [0, 0, 0]


def marble_explosion(tilted, is_explosion):
    cnt = 1
    for i in range(1, len(tilted) - 1):
        if tilted[i] == 0:
            break
        elif tilted[i] == tilted[i + 1]:
            cnt += 1
        else:
            if cnt >= 4:
                cur_num = tilted[i]
                is_explosion = True
                for j in range(i, i - cnt, -1):
                    tilted[j] = -1
                answer[cur_num - 1] += cnt
            cnt = 1
    if is_explosion:
        return paste_able_marble(tilted, False)
    else:
        return tilted


def paste_able_marble(array, is_explosion):
    tilted = [0] * (n ** 2)
    idx = 1
    for i in range(1, n ** 2):
        if array[i] == -1:
            continue
        tilted[idx] = array[i]
        idx += 1
    return marble_explosion(tilted, is_explosion)


def matrix_array(sharkX, sharkY, cnt, idx):
    i, j = sharkX, sharkY
    array = [0] * (n ** 2)
    array[idx] = graph[i][j]
    idx += 1
    matrix_change_array[(sharkX, sharkY)] = idx
    while True:
        for y in range(j - 1, j - cnt - 1, -1):
            j = y
            array[idx] = graph[i][j]
            matrix_change_array[(i, j)] = idx

            idx += 1
        if not i and not j:
            break
        for x in range(i + 1, i + cnt + 1):
            i = x
            matrix_change_array[(i, j)] = idx
            array[idx] = graph[i][j]
            idx += 1
        if cnt != n - 1:
            cnt += 1
        for y in range(j + 1, j + cnt + 1):
            j = y
            array[idx] = graph[i][j]
            matrix_change_array[(i, j)] = idx
            idx += 1
        for x in range(i - 1, i - cnt - 1, -1):
            i = x
            array[idx] = graph[i][j]
            matrix_change_array[(i, j)] = idx
            idx += 1
        if cnt != n - 1:
            cnt += 1
    return array


def transform(array):
    cnt = 1
    idx = 1
    transform_array = [0] * (n ** 2)
    for i in range(1, len(array) - 1):
        if idx >= n * n:
            break
        if array[i] == array[i + 1]:
            cnt += 1
        else:
            transform_array[idx] = cnt
            transform_array[idx + 1] = array[i]
            idx += 2
            cnt = 1
    if array[len(array) - 1] != transform_array[-1] and idx + 1 <= n ** 2:
        transform_array[idx] = cnt
        transform_array[idx + 1] = array[len(array) - 1]
    return transform_array


def solutions(array):
    for d, s in blizzard_magic:
        if d == 0 and s == 0:
            continue
        tmpX, tmpY = sharkX, sharkY
        for _ in range(s):
            nx, ny = tmpX + steps[d][0], tmpY + steps[d][1]
            array[matrix_change_array[(nx, ny)]] = -1
            tmpX, tmpY = nx, ny
        array = transform(paste_able_marble(array, False))


array = matrix_array(sharkX, sharkY, 1, 0)
solutions(array)
sum = 0
for i in range(len(answer)):
    sum += answer[i] * (i + 1)
print(sum)