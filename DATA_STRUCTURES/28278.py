import sys
input = sys.stdin.readline
n = int(input())
stack = []
for _ in range(n):
    choice = input().split()
    if choice[0] == "1":
        stack.append(choice[1])
    elif choice[0] == "2":
        if stack:
            print(stack.pop())
        else:
            print(-1)

    elif choice[0] == "3":
        print(len(stack))
    elif choice[0] == "4":
        if not stack:
            print(1)
        else:
            print(0)
    else:
        if stack:
            print(stack[-1])
        else:
            print(-1)