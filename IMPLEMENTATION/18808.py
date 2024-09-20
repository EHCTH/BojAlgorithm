n, m, k = map(int, input().split())
graph = [[0] * m for _ in range(n)]
root = 4
def rotate(paper):
    global r, c
    new_paper = [[0] * 12 for _ in range(12)]
    for i in range(c):
        for j in range(r):
            new_paper[i][j] = paper[r - 1 - j][i]
    r, c = c, r
    for i in range(r):
        for j in range(c):
            paper[i][j] = new_paper[i][j]

def pastable(x, y):
    for i in range(r):
        for j in range(c):
            if paper[i][j] == 1:
                if graph[x + i][y + j] == 1:
                    return False
    for i in range(r):
        for j in range(c):
            if paper[i][j] == 1:
                graph[x + i][y + j] = 1
    return True

while k:
    k -= 1
    r, c = map(int, input().split())
    paper = [[0] * 12 for _ in range(12)]
    data = [list(map(int, input().split())) for _ in range(r)]
    for i in range(r):
        for j in range(c):
            paper[i][j] = data[i][j]
    for i in range(root):
        is_paste = False
        for x in range(n  + 1 - r):
            if is_paste:
                break
            for y in range(m  + 1- c):
                if pastable(x, y):
                    is_paste = True
                    break
        if is_paste:
            break
        rotate(paper)
cnt = 0
for i in range(n):
    for j in range(m):
        cnt += graph[i][j]
print(cnt)