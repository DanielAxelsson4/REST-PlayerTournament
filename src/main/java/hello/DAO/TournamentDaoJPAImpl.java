package hello.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import hello.Domain.Tournament;
import hello.Services.RecordNotFoundException;


@Component
public class TournamentDaoJPAImpl implements TournamentDao{

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Tournament> getAllTournaments() {
		return em.createQuery("select tournament from tournament as tournament")
				.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Tournament> getTournamentsById(Integer tourId) {
		
		return (List<Tournament>)em.createQuery("select tournament from tournament as tournament where tournament.tourId=:tourId")
				.setParameter("tourId", tourId)
				.getResultList();
		
	}

	@Override
	public void create(Tournament tournament) {
		em.persist(tournament);
		
	}

	@Override
	public void update(Tournament tournamentToUpdate) throws RecordNotFoundException {
		em.merge(tournamentToUpdate);
		
	}

	@Override
	public void delete(Tournament oldTournament) throws RecordNotFoundException {
		oldTournament = em.merge(oldTournament);
		em.remove(oldTournament);
		
	}

	@Override
	public Tournament getById(Integer tourId) throws RecordNotFoundException {
		try
		{			
			return (Tournament)em.createQuery("select tournament from tournament as tournament where tournament.tourId=:tourId")
					.setParameter("tourId", tourId)
			.getSingleResult();
			
		}
		catch (NoResultException e)
		{
			throw new RecordNotFoundException();
		}
	}


	
	
	

	
	
	
}
