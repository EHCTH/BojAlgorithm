n, m = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(n)]
shapes = [
    [[1, 1], [1, 1]],
    [[1, 1, 1, 1]],
    [[1, 1, 1], [0, 1, 0]], # T-모양 1
    [[1, 0], [1, 1], [0, 1]], # 꺽은 모양 1
    [[1,0], [1, 0], [1, 1]], # L-모양 1
]

def rotate(shape):
    tmp_shape = [[0] * len(shape) for _ in range(len(shape[0]))]
    for i in range(len(shape[0])):
        for j in range(len(shape)):
            tmp_shape[i][j] = shape[len(shape) - 1 - j][i]
    return tmp_shape

def symmetry(shppe):
    tmp_symmetry  = [[0] * len(shppe[0]) for _ in range(len(shppe))]
    for i in range(len(shppe)):
        for j in range(len(shppe[0])):
            tmp_symmetry[i][j] = shppe[i][len(shppe[0]) - 1 - j]
    return tmp_symmetry

def paste(x, y, shape):
    sum = 0
    for i in range(len(shape)):
        for j in range(len(shape[0])):
            if shape[i][j] == 1:
                sum += graph[i + x][j + y]
    return sum



def get_shape(idx, shape):
    if idx == 0:
        return shapes[0]
    elif idx == 1:
        return shapes[1]
    elif idx == 3:
        return shapes[2]
    elif idx == 7:
        return shapes[3]
    elif idx in [9, 15]:
        return symmetry(shape)
    elif idx == 11:
        return shapes[4]
    else:
        return rotate(shape)


def solutions():
    max_value = -1e9
    new_shape = []
    for i in range(19):
        new_shape = get_shape(i, new_shape)
        for x in range(n + 1 - len(new_shape)):
            for y in range(m + 1 - len(new_shape[0])):
                max_value = max(max_value, paste(x, y, new_shape))
    return max_value
print(solutions())