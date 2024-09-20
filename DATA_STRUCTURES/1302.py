n = int(input())
di = dict()

for i in range(n):
    name = input()
    if name not in di:
        di[name] = 1
    else:
        di[name] += 1
        
max_value = max(di.values())

answer = []
for key, value in di.items():
    if value == max_value:
        answer.append(key)

answer = sorted(answer)
print(answer[0])