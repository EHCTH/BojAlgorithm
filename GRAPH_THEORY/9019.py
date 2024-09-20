def bfs(a, b):
    queue = [a]
    word_list = {a: ""}
    array = ["D", "S", "L", "R"]
    for depth in range(1, 10000):
        new_queue = []
        for now in queue:
            index = 0
            d = (now * 2) % 10000
            s = (now - 1) if now != 0 else 9999
            l = (10 * now + (now // 1000)) % 10000
            r = (now // 10 + (now % 10) * 1000) % 10000
            for i in [d, s, l, r]:
                if b in word_list:
                    return word_list[b]
                if i not in word_list:
                    word_list[i] = word_list[now] + array[index]
                    new_queue.append(i)
                index += 1
        queue = [item for item in new_queue]


for T in range(int(input())):
    a, b = map(int, input().split())
    print(bfs(a, b))