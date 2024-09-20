n,m = list(map(int,input().split())) #  5 21
list_1 = list(map(int,input().split())) # 5 6 7 8 9
result = 0
for i in range(n):
	for j in range(i+1,n):
		for k in range(j+1,n):
			if list_1[i] + list_1[j] + list_1[k] > m:
				continue
			result = max(result, list_1[i] + list_1[j] + list_1[k]) # result 가 답일 수도 있으니 result를 넣어주었음\
print(result)