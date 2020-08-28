# trivia



Dependencies:

MongoDB
Local install instructions for Mac
https://docs.mongodb.com/manual/tutorial/install-mongodb-on-os-x/
mongod --config /usr/local/etc/mongod.conf
$ more /usr/local/etc/mongod.conf 
systemLog:
  destination: file
  path: /usr/local/var/log/mongodb/mongo.log
  logAppend: true
storage:
  dbPath: /usr/local/var/mongodb
net:
  bindIp: 127.0.0.1
