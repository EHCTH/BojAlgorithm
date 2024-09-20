n = int(input())
st = [list(input().split()) for _ in range(n)]
st.sort(key=lambda x: (int(x[3]), int(x[2]), int(x[1])))
print(st[-1][0])
print(st[0][0])