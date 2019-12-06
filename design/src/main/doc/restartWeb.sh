nohup java -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/home/hump/web/%date:~0,4%%date:~5,2%%date:~8,2%%time:~0,2%%time:~3,2%%time:~6,2%_heapdump.hprof -jar web-1.0-SNAPSHOT.jar   >out.log 2>1 &
tail -1000f out.log
