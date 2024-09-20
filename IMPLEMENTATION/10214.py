T = int(input())
for i in range(T):
    Y = 0 
    K = 0
    for j in range(9):
        y, k = map(int, input().split())
        Y += y
        K += k
    if Y > K:
        print('Yonsei')
    elif Y < K:
        print('Korea')
    else:
        print("Draw")