package hello.DAO;

import java.util.List;

import hello.Domain.Tournament;
import hello.Services.RecordNotFoundException;

public interface TournamentDao {

	public List<Tournament> getAllTournaments();
	public Tournament getById(Integer tourId) throws RecordNotFoundException;
	public List<Tournament> getTournamentsById(Integer tourId);
	public void create(Tournament tournament);
	public void update(Tournament changedTournament) throws RecordNotFoundException;
	public void delete(Tournament oldTournament) throws RecordNotFoundException;
	
}
