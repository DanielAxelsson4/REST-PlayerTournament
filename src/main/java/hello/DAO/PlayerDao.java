package hello.DAO;

import java.util.List;

import hello.Domain.Player;
import hello.Services.RecordNotFoundException;

public interface PlayerDao {

	public List<Player> getAllPlayers();
	public Player getById(Integer playerId) throws RecordNotFoundException;
	public List<Player> getPlayersById(Integer playerId);
	public void create(Player player);
	public void update(Player changedPlayer) throws RecordNotFoundException;
	public void delete(Player oldCustomer) throws RecordNotFoundException;
	
}
