import collections
n, m = map(int, input().split())
dict = collections.defaultdict(list)
dict_2 = collections.defaultdict()
for T in range(n):
    group = input()
    for num in range(int(input())):
        name = input()
        dict[group].append(name)
        dict_2[name] = group

for _ in range(m):
    member = input()
    quiz = int(input())
    if quiz == 1:
        print(dict_2[member])
    else:
        for i in sorted(dict[member]):
            print(i)