package repo;

import model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CityRepo extends JpaRepository<City, Integer> {

    @Query("from City u where u.city_name = :name")
   City findByName(String name);

}
