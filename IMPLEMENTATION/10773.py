n = int(input())
result = []
for _ in range(n):
	num= int(input())
	result.append(num) if num != 0 else result.pop()
print(sum(result))