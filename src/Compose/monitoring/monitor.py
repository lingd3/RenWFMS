# -*- coding:utf-8 -*-
import sys

def updateFile(serviceName, operation):
	filename = "/Users/gd/Desktop/RenWFMS/Compose/" + serviceName+".yml"
	lines = []
	with open(filename, "r") as f:
		lines = f.readlines()
	num = int(lines[6][16])
	if num < 4 and operation == "add":
		updateOP(serviceName)
		lines[6] = "      replicas: " + str(num+1) + "\n"
		with open(filename, "w") as f:
			f.write(''.join(lines))
			f.flush()
	elif num > 1 and operation == "dec":
		updateOP(serviceName)
		lines[6] = "      replicas: " + str(num-1) + "\n"
		with open(filename, "w") as f:
			f.write(''.join(lines))
			f.flush()

def updateOP(serviceName):
	global op
	if serviceName == "BOEngine":
		op = 1
	elif serviceName == "RenResourceService":
		op = 2
	else:
		op = 3

def checkUpdate(str1, serviceName):
	cpu = float(str1.strip("%")) / 100
	if cpu > 0.8 :    #增加
		updateFile(serviceName, "add")
	elif cpu < 0.005: #减少
		updateFile(serviceName, "dec")

f = open("/Users/gd/Desktop/RenWFMS/Compose/monitoring/temp.log", "r")
op = 0
for line in f:
	if line.find("ren_BOEngine") != -1:
		data = line.split(" ")
		checkUpdate(data[1], "BOEngine")
		if op != 0:
			break
	elif line.find("ren_RenResourceService") != -1:
		data = line.split(" ")
		checkUpdate(data[1], "RenResourceService")
		if op != 0:
			break
	elif line.find("ren_RenNameService") != -1:
		data = line.split(" ")
		checkUpdate(data[1], "RenNameService")
		if op != 0:
			break

f.close()
sys.exit(op)
