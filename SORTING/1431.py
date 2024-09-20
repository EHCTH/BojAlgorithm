n = int(input())
arr = [input() for _ in range(n)]
def solutions(word):
    sum = 0
    for i in word:
        if i.isdigit():
            sum += int(i)
    return sum



arr.sort(key= lambda x: (len(x), solutions(x), x))
for i in arr:
    print(i)