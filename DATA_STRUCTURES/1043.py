n, m = map(int, input().split())
answer_person = list(map(int, input().split()))[1:]
parent = [i for i in range(n + 1)]
l = []

def find_parent(x):
    if parent[x] != x:
        parent[x] = find_parent(parent[x])
    return parent[x]

def union_parent(a, b):
    a = find_parent(a)
    b = find_parent(b)
    if a in answer_person and b in answer_person:
        return

    if a in answer_person:
        parent[b] = a

    elif b in answer_person:
        parent[a] = b

    else:
        if a < b:
            parent[b] = a
        else:
            parent[a] = b

for _ in range(m):
    party = list(map(int, input().split()))
    len_party, party = party[0], party[1:]
    for i in range(len_party - 1):
        union_parent(party[i], party[i + 1])
    l.append(party)

answer = 0
for party in l:
    for i in range(len(party)):
        if find_parent(party[i]) in answer_person:
            break
    else:
        answer += 1
print(answer)