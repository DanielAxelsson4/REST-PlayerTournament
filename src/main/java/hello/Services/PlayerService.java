package hello.Services;

import java.util.List;

import hello.Domain.Player;
import hello.Domain.Tournament;

public interface PlayerService {

	Player newPlayer(Player newPlayer);

	void updatePlayer(Player changedPlayer) throws PlayerNotFoundException;
	
	void deletePlayer(Player oldPlayer) throws PlayerNotFoundException;
	
	Player findPlayerById(Integer id) throws PlayerNotFoundException;
	public List<Player> findPlayersById (Integer id);
	
	public List<Player> findPlayersByName (String name);

	boolean validRegistration(List<Player> listPlayerId, List<Tournament> listTournamentId,
			List<Tournament> listAllTournaments, Integer playerId, Integer tourId);

	
	
	
	
	
	
	
	
	
	
}
