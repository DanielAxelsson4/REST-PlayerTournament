package hello.Controllers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import hello.Domain.Player;
import hello.Domain.Tournament;
import hello.Repositories.PlayerRepository;
import hello.Services.PlayerNotFoundException;
import hello.Services.PlayerService;
import hello.Services.TournamentNotFoundException;
import hello.Services.TournamentService;

@RestController    // This means that this class is a Controller
public class PlayerController {



	@Autowired
	private PlayerService playerService;

	@Autowired
	private TournamentService tournamentService;

	@Autowired
	private PlayerRepository playerRepository;

	@PersistenceContext
	private EntityManager em;


	// Error Handlers
	// Should probably make the error messages a bit clearer in the future
	@ExceptionHandler(PlayerNotFoundException.class)
	public ResponseEntity<ClientErrorInformation> rulesForPlayerNotFound(HttpServletRequest req, Exception e) 
	{
		ClientErrorInformation error = new ClientErrorInformation(e.toString(), req.getRequestURI());
		return new ResponseEntity<ClientErrorInformation>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(TournamentNotFoundException.class)
	public ResponseEntity<ClientErrorInformation> rulesForTournamentNotFound(HttpServletRequest req, Exception e) 
	{
		ClientErrorInformation error = new ClientErrorInformation(e.toString(), req.getRequestURI());
		return new ResponseEntity<ClientErrorInformation>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(javax.persistence.OptimisticLockException.class)
	@ResponseStatus(value=HttpStatus.CONFLICT)
	public void handleConflicts() {}


	// Find
	@GetMapping(path="/player/{id}")
	public Player findPlayerById(@PathVariable Integer id) throws PlayerNotFoundException {
		return playerService.findPlayerById(id);	
	}

	// Find all
	@GetMapping(path="/players")
	public @ResponseBody List<Player> getAllPlayers() {
		return playerRepository.findAll();
	}

	// Create
	@PostMapping(path="/player")
	public @ResponseBody ResponseEntity<Player> addNewPlayer (@RequestParam String playerName, @RequestParam String playerEmail) throws TournamentNotFoundException {

		HttpHeaders headers = new HttpHeaders();
		Player createdPlayer = new Player();
		createdPlayer.setPlayerName(playerName);
		createdPlayer.setPlayerEmail(playerEmail);
		playerRepository.save(createdPlayer);
		return new ResponseEntity<Player>(createdPlayer, headers, HttpStatus.CREATED);
	}



	// Update
	@PutMapping(path="/player/{playerId}")
	public @ResponseBody ResponseEntity<Player> updatePlayerById (@PathVariable Integer playerId, @RequestParam(value = "playerName", required=false) String playerName, @RequestParam(value = "playerEmail", required=false) String playerEmail) throws TournamentNotFoundException, PlayerNotFoundException {
		HttpHeaders headers = new HttpHeaders();
		Player changedPlayer = playerService.findPlayerById(playerId);

		if(playerName != null) {
			changedPlayer.setPlayerName(playerName);
		}
		if(playerEmail != null) {
			changedPlayer.setPlayerEmail(playerEmail);
		}
		return new ResponseEntity<Player>(changedPlayer, headers, HttpStatus.ACCEPTED);
	}




	// Delete
	@DeleteMapping(path="/player/{playerId}")
	public @ResponseBody String deletePlayerById (@PathVariable Integer playerId) throws PlayerNotFoundException {

		Player oldPlayer = playerService.findPlayerById(playerId);
		playerService.deletePlayer(oldPlayer);
		return "Deleted";
	}











	// Register player in tournament

	@PostMapping(path="/register/{playerId}/{tourId}")
	public @ResponseBody String registerPlayer(@PathVariable Integer playerId, @PathVariable Integer tourId) throws TournamentNotFoundException {

		boolean validRegistration = false;

		List<Player> listPlayerId = playerService.findPlayersById(playerId);
		List<Tournament> listTournamentId = tournamentService.findTournamentsById(tourId);
		List<Tournament> listAllTournaments = tournamentService.findAllTournaments();

		// ERROR HANDLING

		if (listPlayerId.isEmpty()) {
			return "Player does not exist";
		}
		else if (listTournamentId.isEmpty()) {
			return "Tournament does not exist";
		}

		validRegistration = playerService.validRegistration(listPlayerId, listTournamentId, listAllTournaments, playerId, tourId);
		if (validRegistration == true) {
			// Register here
			Player player = listPlayerId.get(0);
			Tournament tournament = tournamentService.findTournamentById(tourId);
			player.getTournaments().add(tournament);
			playerRepository.save(player);
			return "Registered";
		}
		else {
			return "Invalid registration, try again";
		}

	}


	// Unregister a player from a tournament
	@DeleteMapping(path="/register/{playerId}/{tourId}")
	public @ResponseBody ResponseEntity<Player> unregisterPlayer(@PathVariable Integer playerId, @PathVariable Integer tourId) throws TournamentNotFoundException, PlayerNotFoundException {
		HttpHeaders headers = new HttpHeaders();
		Player player = playerService.findPlayerById(playerId);
		Tournament tournament = tournamentService.findTournamentById(tourId);
		player.getTournaments().remove(tournament);
		playerRepository.save(player);
		return new ResponseEntity<Player>(player, headers, HttpStatus.ACCEPTED);

	}






}
