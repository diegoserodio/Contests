data = list(input().split(' '))
n = int(data[0])
m = int(data[1])
s = int(data[2])
time = 0

def counttime(b, way, check):
	global time
	time = time + (b*way) + check

while True:
	if n % 2 == 0:
		n = n - 1
		bomb = (n//2)+1
		counttime(bomb-1, m, s)
		n = bomb
		if bomb - 1 <= 1:
			print(time)
			break
	else:
		n = n - 1
		bomb = (n//2)+1
		counttime(bomb-1, m, s)
		n = bomb
		if bomb - 1 <= 1:
			print(time)
			break
