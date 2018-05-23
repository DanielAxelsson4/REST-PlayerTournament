package hello.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hello.DAO.TournamentDao;
import hello.Domain.Tournament;

@Transactional
@Service
public class TournamentServiceImpl implements TournamentService{


	private TournamentDao tournamentdao;

	@Autowired
	public TournamentServiceImpl(TournamentDao tournamentdao)
	{
		this.tournamentdao = tournamentdao;
	}




	@Override
	public Tournament newTournament(Tournament newTournament) 
	{
		tournamentdao.create(newTournament);
		return newTournament;
	}

	@Override
	public void updateTournament(Tournament changedTournament) throws TournamentNotFoundException{
		try
		{
			tournamentdao.update(changedTournament);
		}
		catch (RecordNotFoundException e)
		{
			throw new TournamentNotFoundException();
		}
	}

	@Override
	public void deleteTournament(Tournament oldTournament) throws TournamentNotFoundException{
		try
		{
			tournamentdao.delete(oldTournament);
		}
		catch (RecordNotFoundException e)
		{
			throw new TournamentNotFoundException();
		}
	}

	@Override
	public Tournament findTournamentById(Integer tourId) throws TournamentNotFoundException{
		try
		{
			return tournamentdao.getById(tourId);
		}
		catch (RecordNotFoundException e)
		{
			throw new TournamentNotFoundException();
		}
	}

	@Override
	public List<Tournament> findTournamentsByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public List<Tournament> findTournamentsById(Integer id) {
		return tournamentdao.getTournamentsById(id);
	}
	
	@Override
	public List<Tournament> findAllTournaments() {
		return tournamentdao.getAllTournaments();
	}

}
