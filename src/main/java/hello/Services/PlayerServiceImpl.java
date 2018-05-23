package hello.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hello.DAO.PlayerDao;
import hello.Domain.Player;
import hello.Domain.Tournament;

@Transactional
@Service
public class PlayerServiceImpl implements PlayerService{


	private PlayerDao playerdao;

	@Autowired
	public PlayerServiceImpl(PlayerDao playerdao)
	{
		this.playerdao = playerdao;
	}




	@Override
	public Player newPlayer(Player newPlayer) 
	{
		//		if (newPlayer.getPlayerId()== null)
		//		{
		//			// generate an ID using some kind of business rule.
		//			String newId = java.util.UUID.randomUUID().toString();
		//			
		//			newPlayer.getPlayerId(newId);
		//		}
		playerdao.create(newPlayer);
		return newPlayer;
	}

	@Override
	public void updatePlayer(Player changedPlayer) throws PlayerNotFoundException{
		try
		{
			playerdao.update(changedPlayer);
		}
		catch (RecordNotFoundException e)
		{
			throw new PlayerNotFoundException();
		}
	}

	@Override
	public void deletePlayer(Player oldPlayer) throws PlayerNotFoundException{
		try
		{
			playerdao.delete(oldPlayer);
		}
		catch (RecordNotFoundException e)
		{
			throw new PlayerNotFoundException();
		}
	}

	@Override
	public Player findPlayerById(Integer playerId) throws PlayerNotFoundException{
		try
		{
			return playerdao.getById(playerId);
		}
		catch (RecordNotFoundException e)
		{
			throw new PlayerNotFoundException();
		}
	}

	@Override
	public List<Player> findPlayersByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public List<Player> findPlayersById(Integer id) {
		return playerdao.getPlayersById(id);
	}


	@Override
	public boolean validRegistration(List<Player> listPlayerId, List<Tournament> listTournamentId, List<Tournament> listAllTournaments, Integer playerId, Integer tourId) {

		boolean playerRegisteredInParent = false;
		boolean tournamentHasParent = false;
		boolean validRegistration = false;
		boolean playerAlreadyInTournament = false;

		// Conditions for registering
		
			// for every tournament a player has check if tournamentParentId is the same as tourId (player registered in a parent tournament
			// Tournament that has tour_id as tourId AND that tournamentParentId used to find another tournament
			// and that tournament tourId must be same as tournamentParentId
			
			
			for (Tournament tour1 : listTournamentId) {
				Integer tournParentId = tour1.getTournamentparentId();
				for (Tournament tourAll : listAllTournaments) {
					Integer i2 = tourAll.getTourId();
					if (i2 == tournParentId) {
						// is the player registered in this tournament

						for (Player player : tourAll.getPlayers()) {
							Integer playerIdInteger = player.getPlayerId();
							Integer playerIdInteger2 = playerId;
							if (playerIdInteger.equals(playerIdInteger2)) {
								playerRegisteredInParent = true;
								break;
							}
							else if (!playerIdInteger.equals(playerId)) {
								playerRegisteredInParent = false;
							}
						}
					}
				}
			}

			// Check if tournament has a parent tournament and check if player is already in tournament

			for (Tournament next:listTournamentId) {
				Integer tournParent = next.getTournamentparentId();
				
				// If the player already is in the tournament
				if(next.getPlayers().contains(listPlayerId.get(0))) {
					playerAlreadyInTournament = true;
				}
				
				

				for (Tournament next2:listAllTournaments) {
					Integer i4 = next2.getTourId();
					if (i4 == tournParent ) {
						tournamentHasParent = true;
						break;
					}
					else {
						tournamentHasParent = false;
					}
				}
			}



			System.out.println("tournamentHasParent: " + tournamentHasParent + " | playerRegisteredInParent: " + playerRegisteredInParent + " | playerAlreadyInTournament " + playerAlreadyInTournament);

			if ((tournamentHasParent == true && playerRegisteredInParent == true && playerAlreadyInTournament == false) || tournamentHasParent == false && playerAlreadyInTournament == false) {

				validRegistration = true;
				return validRegistration;
			}
			else if (playerRegisteredInParent == false || playerAlreadyInTournament == true){
				validRegistration = false;
				return validRegistration;
			}
		
		return validRegistration;
	}
}
