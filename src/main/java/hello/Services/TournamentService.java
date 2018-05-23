package hello.Services;

import java.util.List;

import hello.Domain.Tournament;

public interface TournamentService {

	Tournament newTournament(Tournament newTournament);

	void updateTournament(Tournament changedTournament) throws TournamentNotFoundException;
	
	void deleteTournament(Tournament oldTournament) throws TournamentNotFoundException;
	
	Tournament findTournamentById(Integer id) throws TournamentNotFoundException;
	
	public List<Tournament> findTournamentsById (Integer id);
	
	public List<Tournament> findTournamentsByName (String name);

	public List<Tournament> findAllTournaments();

	
	
	
	
	
	
	
	
	
	
}
