n = int(input())
m = int(input())
graph = [list(map(int, input().split())) for _ in range(n)]
parent = [-1 for _ in range(n + 1)]
for i in range(1, n + 1):
    parent[i] = i
run_order = list(map(int ,input().split()))
def find_parent(x):
    if parent[x] != x:
        parent[x] = find_parent(parent[x])
    return parent[x]

def union(a, b):
    a = find_parent(a)
    b = find_parent(b)
    if a < b:
        parent[b] = a
    else:
        parent[a] = b

for i in range(n):
    for j in range(n):
        if graph[i][j] == 1:
            union(i + 1, j + 1)

for i in range(1, m):
    if find_parent(run_order[i]) != find_parent(run_order[0]):
        print("NO")
        exit(0)
print("YES")