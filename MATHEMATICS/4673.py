a = set()
b = set(range(10001))
for i in range(10001):
	for k in str(i):
		i += int(k)
	a.add(i)
result = sorted(b-a)
for i in result:
	print(i)