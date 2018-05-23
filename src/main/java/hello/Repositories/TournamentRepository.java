package hello.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.Domain.Tournament;

public interface TournamentRepository extends JpaRepository<Tournament, Long>
{
	
	//HashSet<Tournament> getTournamentByPlayerId(String playerId);

}
