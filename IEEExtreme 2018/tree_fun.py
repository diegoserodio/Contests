x = []
y = []
cab = []
bnd = []
value = []
numbers = []
index = []
terminal = []
status = 0

n = list(input().split(' '))
nodes = int(n[0])
op = int(n[1])

for l in range(nodes):
	index.append(0)

for i in range(nodes-1):
	pair = list(input().split(' '))
	a = int(pair[0])
	b = int(pair[1])
	x.append(a)
	x.append(b)
for j in range(op):
	data = list(input().split(' '))
	d = int(data[0])
	e = int(data[1])
	f = int(data[2])
	cab.append(d)
	bnd.append(e)
	value.append(f)

for u in  range(nodes):
	for v in range(len(x)):
		if u == x[v]:
			status = status + 1
	if status == 1:
		terminal.append(u)
		status = 0
	else:
		status = 0
		

print(x)
print(cab)
print(bnd)
print(terminal)
#print(max(index))
	