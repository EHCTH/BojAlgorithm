n, w, h = map(int, input().split())
bs = ((w * w) + (h * h)) ** 0.5
for _ in range(n):
    s = int(input())
    if bs >= s:
        print("DA")
    else:
        print("NE")