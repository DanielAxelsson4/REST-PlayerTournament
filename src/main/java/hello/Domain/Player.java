package hello.Domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;



@Entity (name="player")
@Table(name = "player")
public class Player {
    
    private int playerId;
    private String playerName;
    private String playerEmail;
    
    
    private Set<Tournament> tournaments;

    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="playerId")
	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public String getPlayerEmail() {
		return playerEmail;
	}

	public void setPlayerEmail(String playerEmail) {
		this.playerEmail = playerEmail;
	}
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "player_tournament", joinColumns = @JoinColumn(name = "playerId", referencedColumnName = "playerId"), inverseJoinColumns = @JoinColumn(name = "tourId", referencedColumnName = "tourId"))
	public Set<Tournament> getTournaments() {
		return tournaments;
	}

	public void setTournaments(Set<Tournament> tournaments) {
		this.tournaments = tournaments;
	}

	
	
	
	public Player(String name, String email) {
		super();
		this.playerName = name;
		this.playerEmail = email;
	}

	public Player() {
	}

	@Override
    public String toString() {
        String result = String.format(
                "Player [id=%d, name='%s']%n",
                playerId, playerName);
        if (tournaments != null) {
            for(Tournament tournament : tournaments) {
            	
                result += String.format(
                        "Tournament[id=%d, name='%s']%n",
                        tournament.getTourId(), tournament.getTourName());
            }
        }

        return result;
    }
	
	
    
    
}

