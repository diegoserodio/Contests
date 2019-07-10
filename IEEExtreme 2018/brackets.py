rounds = []
square = []
seq = []
data = list(input())
somau = 0
somav = 0

for i in range(len(data)):
	if data[i] == "(":
		rounds.append(-1)
		seq.append(1)
	elif data[i] == ")":
		rounds.append(1)
		seq.append(2)
	elif data[i] == "[":
		square.append(-2)
		seq.append(3)
	elif data[i] == "]":
		square.append(2)
		seq.append(4)

pairs = (len(rounds) + len(square)) / 2

if (len(rounds) % 2) != 0 or (len(square) % 2) != 0:
	print("Impossível") 
elif pairs % 2 != 0: 
	print("Impossível")
else:
	for u in range(len(rounds)):
		somau = somau + rounds[u]
	for v in range(len(square)):
		somav = somav + square[v]
	if somau != 0 or somav != 0:
		print("Impossível")
	else:
		print("Não sei")