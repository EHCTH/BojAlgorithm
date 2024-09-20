import sys
sys.setrecursionlimit(10**4)
n = int(input())
tree = list(map(int, input().split()))
d = int(input())
cnt = 0

def dfs(tree, d):
    tree[d] = -1e9
    for i in range(n):
        if d == tree[i]:
            dfs(tree, i)

dfs(tree, d)
for i in range(n):
    if tree[i] != -1e9 and i not in tree:
        cnt += 1
print(cnt)