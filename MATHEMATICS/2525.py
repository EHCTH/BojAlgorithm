h, m = map(int, input().split())
n = int(input())
def solution():
    global h, m
    m = m + n
    if m >= 60:
        h += m // 60
        m %= 60
    if h >= 24:
        h -= 24
solution()
print(h, m)