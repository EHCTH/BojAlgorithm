import collections
dict = collections.defaultdict(int)
word = list(input())
# answer = AABCBAA
word.sort()

for i in word:
    dict[i] += 1
cnt = 0
mid = ""
for i in dict:
    if dict[i] % 2 == 1:
        cnt += 1
        mid += i
        word.remove(i)
left = ""
if 1 < cnt:
    print("I'm Sorry Hansoo")
    exit(0)
else:
    for i in range(0, len(word), 2):
        left += word[i]
    right = left[::-1]
    print(left + mid + right)