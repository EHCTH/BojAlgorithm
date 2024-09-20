n = int(input())  # 과목 수
test_list = list(map(int, input().split())) # 70 80 90
max_score = max(test_list)
result = []
for score in test_list:
    result.append(score / max_score*100)
avg = sum(result)/n
print(avg)