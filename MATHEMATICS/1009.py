import sys 
input = sys.stdin.readline
n = int(input())
results = []
for i in range(n):
    a, b = map(int, input().split())
    if a % 10 == 0:
        results.append(10)
    else:
        c = b % 4 + 4
        data = str(a ** c)[-1]
        results.append(data)

for result in results:
    print(result)