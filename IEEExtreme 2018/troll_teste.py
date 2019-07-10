import sys
import numpy as np

seq = []
data = []
right = 0
track = 0
status = 0
answer = ""

def change(number):
	if number == 0:
		return 1
	else:
		return 0

while True:
	n = int(input())
	for u in range(n):
		data.append(0)
		seq.append(np.random.randint(0, 2))
	print(seq)
	for v in range(len(data)):
		answer = answer + " " + str(data[v])
	print("Q" + answer)
	sys.stdout.flush()
	
	while True:
		for k in range(len(seq)):
			if seq[k] == data[k]:
				right = right + 1
		answer = ""

		if right == n+1:
			for p in range(len(data)):
				answer = answer + " " + str(data[p])
			print("A" + answer)
			sys.stdout.flush()
			break
		else:
			if right > status:
				data[track] = change(data[track])
				for n in range(len(data)):
					answer = answer + " " + str(data[n])
				print("Q" + answer)
				sys.stdout.flush()
				track = track + 1
				status = right
				right = 0
			else:
				data[track-1] = change(data[track-1])
				data[track] = change(data[track])
				for m in range(len(data)):
					answer = answer + " " + str(data[m])
				print("Q" + answer)
				sys.stdout.flush()
				track = track + 1
				status = right
				right = 0
