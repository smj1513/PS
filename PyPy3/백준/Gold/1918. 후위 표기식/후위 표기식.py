from collections import deque

infix = deque(list(input()))
operators = ["+", "-", "*", "/", "(", ")"]

isp_map = {
    "(" : 1,
    "-" : 2,
    "+" : 2,
    "*" : 3,
    "/" : 3,
    "$" : 0
}

icp_map = {
    "(" : 9,
    "-" : 2,
    "+" : 2,
    "*" : 3,
    "/" : 3,
    "$" : 10
}

operand = []
operator = []
postfix = []
operator.append("$")  # eos
while infix:
    cur_token = infix.popleft()
    if not cur_token in operators:
        postfix.append(cur_token)
    elif cur_token == ")":
        while not (operator[-1] == "(" or operator[-1] == "$"):
            postfix.append(operator.pop())
        operator.pop()
    elif isp_map[operator[-1]] < icp_map[cur_token]:
        operator.append(cur_token)
    else:
        while (isp_map[operator[-1]] >= icp_map[cur_token]):
            postfix.append(operator.pop())
        operator.append(cur_token)
while operator[-1] != "$":
    postfix.append(operator.pop())
print("".join(postfix))