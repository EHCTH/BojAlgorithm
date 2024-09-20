n = int(input())
cnt = 0
visited = [False] * n
visited1 = [False] * ((2 * n) -1)
visited2 = [False] * ((2 * n) -1)

def soultion(count):
    global cnt
    if count == n:
        cnt += 1
    else:
        for j in range(n):
            if not visited[j] and not visited1[count + j] and not visited2[count - j + n - 1]:
                visited[j] = True # 열
                visited1[count + j] = True # 좌측 대각
                visited2[count - j + n - 1] = True # 우측 대각
                soultion(count + 1)
                visited[j] = False
                visited1[count + j] = False
                visited2[count - j + n - 1] = False
soultion(0)
print(cnt)