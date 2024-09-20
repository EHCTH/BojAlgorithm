from collections import deque

def bfs(v):
    queue = deque([v])
    visited = [False] * (n + 1)
    visited[v] = True
    count = 1

    while queue:
        node = queue.popleft()
        for adj_node in graph[node]:
            if not visited[adj_node]:
                queue.append(adj_node)
                visited[adj_node] = True
                count += 1

    return count

n, m = map(int, input().split())  # 컴퓨터의 개수 n, 신뢰하는 관계의 개수 m 입력

# 그래프 초기화 (인접 리스트로 표현)
graph = [[] for _ in range(n + 1)]

# 신뢰하는 관계 입력 및 그래프 구성
for _ in range(m):
    a, b = map(int, input().split())
    graph[b].append(a)

max_hacking = 0
result = []

# 모든 컴퓨터에 대해 BFS 수행
for i in range(1, n + 1):
    count = bfs(i)
    if count > max_hacking:
        max_hacking = count
        result = [i]
    elif count == max_hacking:
        result.append(i)

print(*result)  # 결과 출력