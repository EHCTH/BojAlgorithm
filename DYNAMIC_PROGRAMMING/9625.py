n = int(input())
a = [0] * (45 + 1)
b = [0] * (45 + 1)
a[0] = 0
b[0] = 1
a[1] = 1
b[1] = 1
for i in range(2, n):
    a[i] = a[i - 1] + a[i - 2]
    b[i] = b[i - 1] + b[i - 2]
print(a[n - 1], b[n - 1])