from collections import deque
n, k = map(int, input().split())
INF = 100001
dp = [0] * INF
visited = [False for _ in range(INF)]
def bfs(n):
    queue = deque()
    queue.append(n)
    visited[n] = True
    while queue:
        cur = queue.popleft()
        if cur == k:
            return dp[cur]
        # *2, -1, 1 순은 정답, *2, 1, -1 은 오답;;
        for next in [cur * 2, cur - 1, cur + 1]:
            if 0 <= next < INF and not visited[next]:
                visited[next] = True
                if next == cur * 2:
                    dp[next] = dp[cur]
                else:
                    dp[next] = dp[cur] + 1
                queue.append(next)
print(bfs(n))