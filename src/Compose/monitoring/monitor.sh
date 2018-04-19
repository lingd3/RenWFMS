#!/bin/sh 

# 日志输出   
MonitorLog=/Users/gd/Desktop/RenWFMS/Compose/monitoring/Monitor.log

Monitor() {
	echo "---------------------------------------"
	echo "[info]开始监控......[$(date +'%F %H:%M:%S')]" 

	echo "查看docker容器的cpu利用率等......"
	docker stats --no-stream  --format "{{.Container}} {{.CPUPerc}} {{.Name}}" > /Users/gd/Desktop/RenWFMS/Compose/monitoring/temp.log
	
	echo "判断是否需要增加或减少容器实例......"
	python monitor.py

	op=$?
	echo "op = $op"

	if [ $op -eq 1 ]
	then
		echo "deploy BOEngine"
		docker stack deploy -c ../BOEngine.yml ren --with-registry-auth
	elif [ $op -eq 2 ]
	then
		echo "deploy RenResourceService"
		docker stack deploy -c ../RenResourceService.yml ren --with-registry-auth
	elif [ $op -eq 3 ]
	then
		echo "deploy RenNameService"
		docker stack deploy -c ../RenNameService.yml ren --with-registry-auth
	else
		echo "No Operation"
	fi
	echo "---------------------------------------"
}
Monitor>>$MonitorLog
