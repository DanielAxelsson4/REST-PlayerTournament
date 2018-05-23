package hello.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.Domain.Player;





// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
