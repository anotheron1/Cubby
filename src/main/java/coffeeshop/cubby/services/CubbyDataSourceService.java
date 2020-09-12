package coffeeshop.cubby.services;

import coffeeshop.cubby.structure.Client;

import java.util.List;

public interface CubbyDataSourceService {
    List<Client> getAllClients();

    List<Client> findAllByCupCount(int cupCount);

    Client findByPhone(long phone);
}
