# trivia

http://localhost:8080/purge
curl --get "http://localhost:8080/purge"

http://localhost:8080/signin?name=Lee&email=glroland@hotmail.com
curl --get "http://localhost:8080/signin?name=Lee&email=glroland@hotmail.com"

http://localhost:8080/lobby?playerId=5f5ae178e613b7625c8d51be
curl --get "http://localhost:8080/lobby?playerId=5f5ae178e613b7625c8d51be" | jq

http://localhost:8080/clearLobbies
curl --get "http://localhost:8080/clearLobbies"

http://localhost:8080/games?playerId=5f5ae178e613b7625c8d51be
curl --get "http://localhost:8080/games?playerId=5f5ae178e613b7625c8d51be" | jq


back at ya test change

Dependencies:

MongoDB
Local install instructions for Mac
https://docs.mongodb.com/manual/tutorial/install-mongodb-on-os-x/
mongod --config /usr/local/etc/mongod.conf
