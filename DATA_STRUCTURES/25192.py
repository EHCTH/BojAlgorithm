n = int(input())
dict = {}
cnt = 0

for _ in range(n):
    word = input()
    if word =="ENTER":
        for key, value in dict.items():
            if value == 1:
                cnt += 1
        dict = {}
    else:
        if word not in dict:
            dict[word] = 1

for key,value in dict.items():
    if value == 1:
        cnt += 1

print(cnt)