package hello.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import hello.Domain.Player;
import hello.Services.RecordNotFoundException;


@Component
public class PlayerDaoJPAImpl implements PlayerDao{

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Player> getAllPlayers() {
		return em.createQuery("select player from player as player")
				 .getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Player> getPlayersById(Integer playerId) {
		
		return (List<Player>)em.createQuery("select player from player as player where player.playerId=:playerId")
				.setParameter("playerId", playerId)
				.getResultList();
		
	}

	@Override
	public void create(Player player) {
		em.persist(player);
		
	}

	@Override
	public void update(Player playerToUpdate) throws RecordNotFoundException {
		em.merge(playerToUpdate);
		
	}

	@Override
	public void delete(Player oldPlayer) throws RecordNotFoundException {
		oldPlayer = em.merge(oldPlayer);
		em.remove(oldPlayer);
		
	}

	@Override
	public Player getById(Integer playerId) throws RecordNotFoundException {
		try
		{			
			
			System.out.println("playerId is " + playerId);
			return (Player)em.createQuery("select player from player as player where player.playerId=:playerId")
			.setParameter("playerId", playerId)
			.getSingleResult();
			
		}
		catch (NoResultException e)
		{
			throw new RecordNotFoundException();
		}
	}


	
	
	

	
	
	
}
