l, c = map(int, input().split())
# 암호는 서로 다른 L개의 알파벳 소문자들로 구성되며 최소 한 개의 모음(a, e, i, o, u)과 최소 두 개의 자음으로 구성되어 있다고 알려져 있다.
# 또한 정렬된 문자열을 선호하는 조교들의 성향으로 미루어 보아 암호를 이루는 알파벳이 암호에서 증가하는 순서로 배열되었을 것이라고 추측된다.
# 즉, abc는 가능성이 있는 암호이지만 bac는 그렇지 않다.
# 새 보안 시스템에서 조교들이 암호로 사용했을 법한 문자의 종류는 C가지가 있다고 한다.
# 이 알파벳을 입수한 민식, 영식 형제는 조교들의 방에 침투하기 위해 암호를 추측해 보려고 한다.
# C개의 문자들이 모두 주어졌을 때, 가능성 있는 암호들을 모두 구하는 프로그램을 작성하시오.
array = list(input().split())
set = ['a', 'e', 'i', 'o', 'u']
array.sort()
answer = []
def solutions(cnt, next):
    if cnt == l:
        vo_cnt, co_cnt = 0, 0
        for i in answer:
            if i in set:
                vo_cnt += 1
            else:
                co_cnt += 1
        if vo_cnt >= 1 and co_cnt >= 2:
            print("".join(answer))
    else:
        for i in range(next, c):
            answer.append(array[i])
            solutions(cnt + 1, i + 1)
            answer.pop()


solutions(0, 0)