package uz.pdp.store.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.store.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
