# a simple parser for python. use get_number() and get_word() to read
n = 0
x = []
y = []
r = []
a1 = []
a2 = []
do = True

def parser():
    while 1:
        data = list(input().split(' '))
        for number in data:
            if len(number) > 0:
                yield(number)   

input_parser = parser()

def get_word():
    global input_parser
    return next(input_parser)

def get_number():
    data = get_word()
    try:
        return int(data)
    except ValueError:
        return float(data)

def prel(num1, num2, num3):
    global x, y, p, n
    x.append(num1)
    y.append(num2)
    r.append(num3)

while True:
    n = get_number()
    for i in range(n):
        a = get_word()
        b = get_word()
        c = get_number()
        prel(a, b, c)
    for j in range(len(x)):
        for k in range(len(y)):
            if x[j] == y[k]:
                for w in range(len(x)):
                    if y[j] == x[w] and x[k] == y[w]:
                        do = False
                        break
                if do == True:
                    x.append(y[j])
                    y.append(x[k])
                    naosei = 1/(r[j]*r[k])
                    r.append(naosei)
                else:
                    do = True
    for l in range(len(y)):
        for m in range(len(x)):
            if y[l] == x[m]:
                for v in range(len(x)):
                    if x[l] == x[v] and y[m] == y[v]:
                        do = False
                        break
                if do == True:
                    x.append(x[l])
                    y.append(y[m])
                    naosei = r[l]*r[m]
                    r.append(naosei)
                else:
                    do = True
    for n in range(len(x)):
        for o in range(1, len(x)):
            if x[n] == x[o] and n != o:
                for k in range(len(x)):
                    if x[k] == y[n] and y[o] == y[k]:
                        do = False
                        break
                if do == True:
                    x.append(y[n])
                    y.append(y[o])
                    naosei = r[n]*r[o]
                    r.append(naosei)
                else:
                    do = True
    for p in range(len(y)):
        for q in range(1, len(y)):
            if y[p] == y[q] and p != q:
                for t in range(len(x)):
                    if x[p] == x[p] and x[q] == y[p]:
                        do = False
                        break
                if do == True:
                    x.append(x[p])
                    y.append(x[q])
                    naosei = r[p]*r[q]
                    r.append(naosei)
                else:
                    do = True
    for h in range(len(r)):
        r[h] = r[h] % 998244353
    
    print(x)
    print(y)
    print(r)
    g = get_number()
    for f in range(g):
       d = get_word()
       e = get_word()
       a1.append(d)
       a2.append(e)
    for u in range(len(a1)):
        for b in range(len(x)):
            if a1[u] == x[b] and a2[u] == y[b]:
                print(r[b])


