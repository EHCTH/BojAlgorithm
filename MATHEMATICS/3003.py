# 킹 1개, 퀸 1개, 룩 2개, 비숍 2개, 나이트 2개, 폰 8개
ex = [1, 1, 2, 2, 2, 8]
li = list(map(int, input().split()))
for i in range(len(li)):
    print(ex[i] - li[i], end=' ')