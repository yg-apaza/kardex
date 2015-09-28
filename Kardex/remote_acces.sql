update user set host='%' where user='root' and host='localhost';
flush privileges;