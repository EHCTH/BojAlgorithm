n = int(input())
word = set()
for _ in range(n):
    a = input()
    word.add(a)

print("\n".join(sorted(word, key = lambda x: (len(x), x[::]))))