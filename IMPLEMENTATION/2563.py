graph = [[0] * 101 for i in range(101)]
cnt = 0
n = int(input())
for i in range(n):
    a, b = map(int, input().split())
    for i in range(10):
        for j in range(10):
            graph[a + i][b + j] = 1
            
            
for i in graph:
    cnt += sum(i)
print(cnt)