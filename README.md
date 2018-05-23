# REST-PlayerTournament
REST API for registering players in tournaments

Note that this API requires a player to register before he can register in a tournament.


You can either use a browser or rest-shell to use this API.

Player mappings:

Get a player by id:
GET http://localhost:8080/player/1

Get all players:
GET http://localhost:8080/players

Create a player:
POST http://localhost:8080/player?playerName=Elton&playerEmail=elton@gmail.com

Update a player:
PUT http://localhost:8080/player/1?playerEmail=elton@hotmail.com

Delete a player:
DELETE http://localhost:8080/player/1


Tournament mappings:

Get a tournament by id:
GET http://localhost:8080/tournament/1

Get all tournaments:
GET http://localhost:8080/tournaments

Create a tournament:
POST http://localhost:8080/tournament?tourName=ChessTournament&amountOfPlayers=40&tournamentparentId=3
tournamentparentId, amountOfPlayers are Optional parameters

Delete a tournament:
DELETE http://localhost:8080/tournament/1

Update a tournament:
PUT http://localhost:8080/tournament/1?amountOfPlayers=99
All parameters are optional


Register player in tournament mappings:

Register player (with id 5) in tournament (with id 2)
POST http://localhost:8080/register/5/2

Unregister player (with id 5) in tournament (with id 2):
DELETE http://localhost:8080/register/5/2