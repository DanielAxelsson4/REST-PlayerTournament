package hello.Controllers;

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
import org.springframework.web.bind.annotation.RestController;

import hello.Domain.Tournament;
import hello.Repositories.TournamentRepository;
import hello.Services.PlayerNotFoundException;
import hello.Services.TournamentNotFoundException;
import hello.Services.TournamentService;

@RestController 
public class TournamentController {
	@Autowired 
	private TournamentRepository tourRepository;

	@Autowired
	private TournamentService tournamentService;

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

	// Find
	@GetMapping(path="tournament/{tourId}")
	public Tournament findTournamentById(@PathVariable Integer tourId) throws TournamentNotFoundException {
		// This returns a JSON or XML with the users
		return tournamentService.findTournamentById(tourId);
	}

	// Find all
	@GetMapping(path="/tournaments")
	public @ResponseBody Iterable<Tournament> getAllTournaments() {
		// This returns a JSON or XML with the users
		return tourRepository.findAll();
	}


	// Create
	@PostMapping(path="/tournament") // Map ONLY GET Requests
	public @ResponseBody ResponseEntity<Tournament> addNewTournament (@RequestParam String tourName, @RequestParam(value = "tournamentparentId", required=false) Integer tournamentparentId,
			@RequestParam(value = "amountOfPlayers", required=false) Integer amountOfPlayers) {

		HttpHeaders headers = new HttpHeaders();
		Tournament createdTournament = new Tournament();
		createdTournament.setTourName(tourName);
		createdTournament.setAmountOfPlayers(amountOfPlayers);
		createdTournament.setTournamentparentId(tournamentparentId);
		tourRepository.save(createdTournament);
		return new ResponseEntity<Tournament>(createdTournament, headers, HttpStatus.CREATED);
	}

	// Delete
	@DeleteMapping(path="/tournament/{tourId}")
	public @ResponseBody ResponseEntity<Tournament> deleteTournamentById (@PathVariable Integer tourId) throws TournamentNotFoundException {

		HttpHeaders headers = new HttpHeaders();
		Tournament oldTournament = tournamentService.findTournamentById(tourId);
		tournamentService.deleteTournament(oldTournament);
		return new ResponseEntity<Tournament>(oldTournament, headers, HttpStatus.ACCEPTED);
	}

	// Update
	@PutMapping(path="/tournament/{tourId}")
	public @ResponseBody ResponseEntity<Tournament> updateTournamentById (@PathVariable Integer tourId, @RequestParam(value = "tourName", required=false) String tourName, @RequestParam(value = "tournamentparentId", required=false) Integer tournamentparentId,
			@RequestParam(value = "amountOfPlayers", required=false) Integer amountOfPlayers) throws TournamentNotFoundException {

		Tournament changedTournament = tournamentService.findTournamentById(tourId);
		HttpHeaders headers = new HttpHeaders();

		if(tourName != null) {
			changedTournament.setTourName(tourName);
		}
		if(tournamentparentId != null) {

			changedTournament.setTournamentparentId(tournamentparentId);
		}
		if(amountOfPlayers != null) {

			changedTournament.setAmountOfPlayers(amountOfPlayers);
		}

		tournamentService.updateTournament(changedTournament);
		return new ResponseEntity<Tournament>(changedTournament, headers, HttpStatus.ACCEPTED);
	}






















}
