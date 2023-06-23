package uz.pdp.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.store.model.Authority;
import uz.pdp.store.model.User;
import uz.pdp.store.model.UserAuthority;

import java.util.Set;

@Repository
public interface UserAuthorityRepository extends JpaRepository<UserAuthority, Integer> {

    @Query(value = "select u.authority from UserAuthority u where u.user = :user")
    Set<Authority> getAuthorities(User user);

}
