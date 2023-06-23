package uz.pdp.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.store.model.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
}
