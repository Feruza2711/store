package uz.pdp.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.store.model.Unit;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Integer> {

}
