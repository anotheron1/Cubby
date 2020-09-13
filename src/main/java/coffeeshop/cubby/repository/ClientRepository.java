package coffeeshop.cubby.repository;

import coffeeshop.cubby.structure.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer> {
    List<Client> findAllByCupCount(int cupCount);

    Client findByPhone(long phone);
}
