import collections
import sys
dict = collections.defaultdict(int)
array = []
n, m = map(int, input().split())
for _ in range(n):
	word = sys.stdin.readline().rstrip()
	if len(word) >= m:
		dict[word] += 1
		
for i, v in dict.items():
	array.append((v, len(i), i))
for i in sorted(array, key=lambda x : (-x[0], -x[1], x[2])):
	print(i[2])