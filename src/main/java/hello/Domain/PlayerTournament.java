package hello.Domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity (name="playertournament")
@Table(name = "playertournament")
public class PlayerTournament implements Serializable{


	private int id;
	@Column(name = "playerId")
	private Player player;
	private Tournament tournament;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "playerId")
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "tourId")
	public Tournament getTournament() {
		return tournament;
	}
	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}
	public PlayerTournament() {
	}
	public PlayerTournament(Player player, Tournament tournament) {
		this.player = player;
		this.tournament = tournament;
	}
	
	

	


}
