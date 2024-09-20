H, M, S = map(int, input().split())
T = int(input()) 
S += T % 60
T = T // 60
if S >= 60:
    S -= 60
    M += 1
M += T % 60
T = T // 60
if M >= 60:
    M -= 60
    H += 1
H += T % 24
if H >= 24:
    H -= 24
print(H,M,S)