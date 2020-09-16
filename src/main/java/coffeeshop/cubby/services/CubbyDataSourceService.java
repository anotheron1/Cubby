package coffeeshop.cubby.services;

import coffeeshop.cubby.dto.ClientDto;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface CubbyDataSourceService {
    List<ClientDto> getAllClients();

    List<ClientDto> findAllByCupCount(int cupCount);

    ClientDto findByPhone(long phone);

    void deleteClient(Integer clientId);

    ClientDto saveClient(ClientDto clientDto) throws ValidationException;

    ClientDto incrementCupCountById(int clientId);
}
