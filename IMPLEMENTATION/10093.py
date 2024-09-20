a, b = map(int, input().split())
min_value = min(a, b)
max_value = max(a, b)
result = max_value - min_value - 1
if max_value == min_value or min_value + 1 == max_value:
    result = 0    
print(result)
for i in range(min_value + 1, max_value):
    print(i, end=" ")