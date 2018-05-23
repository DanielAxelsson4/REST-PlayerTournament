package hello.Domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity (name="tournament")
@Table(name = "tournament")
public class Tournament implements Serializable
{
    
    private int tourId;
    private String tourName;
    private Integer amountOfPlayers;
    private Integer tournamentparentId;
    private int playerId;

    
    private Set<Player> players;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="tourId")
	public int getTourId() {
		return tourId;
	}
    
    
    
    
    
    
    
    
    
	



    @Column(name="tournamentparentId")
	public Integer getTournamentparentId() {
		return tournamentparentId;
	}

	public void setTournamentparentId(Integer tournamentparentId) {
		this.tournamentparentId = tournamentparentId;
	}

	public void setTourId(int tourId) {
		this.tourId = tourId;
	}


	public void setTourName(String tourName) {
		this.tourName = tourName;
	}

	public int getAmountOfPlayers() {
		return amountOfPlayers;
	}

	public void setAmountOfPlayers(int amountOfPlayers) {
		this.amountOfPlayers = amountOfPlayers;
	}


	public String getTourName() {
		return tourName;
	}


	
	@JsonIgnore
	@ManyToMany(mappedBy = "tournaments")
	public Set<Player> getPlayers() {
		return players;
	}

	public void setPlayers(Set<Player> players) {
		this.players = players;
	}













	public Tournament() {
	}

	public Tournament(String name, int amountOfPlayers) {
		this.tourName = name;
		this.amountOfPlayers = amountOfPlayers;
		
	}




	public Tournament(String tourName, int amountOfPlayers, Integer tournamentparentId) {
		this.tourName = tourName;
		this.amountOfPlayers = amountOfPlayers;
		this.tournamentparentId = tournamentparentId;
	}
	
	

	
	
	
	
	
    
    
}

