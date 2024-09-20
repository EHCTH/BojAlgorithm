import sys
input = sys.stdin.readline
n = int(input())
li = []
for _ in range(n):
    a = int(input())
    li.append(a)
for i in sorted(li):
    print(i)