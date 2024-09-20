n = int(input())
one, zero = 1, 0
for _ in range(n):
    result = one
    one, zero = one + zero, one
print(result)