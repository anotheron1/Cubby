package coffeeshop.cubby.services;

import coffeeshop.cubby.converter.ClientConverter;
import coffeeshop.cubby.dto.ClientDto;
import coffeeshop.cubby.repository.ClientRepository;
import coffeeshop.cubby.structure.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.Objects.isNull;

@Service
@Slf4j
public class CubbyDataSourceServiceImpl implements CubbyDataSourceService {

    private final ClientRepository clientRepository;
    private final ClientConverter clientConverter;

    public CubbyDataSourceServiceImpl(ClientRepository clientRepository, ClientConverter clientConverter) {
        this.clientRepository = clientRepository;
        this.clientConverter = clientConverter;
    }

    @Override
    public List<ClientDto> getAllClients() {
        return StreamSupport.stream(clientRepository.findAll().spliterator(), false)
                .map(clientConverter::fromClientToClientDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClientDto> findAllByCupCount(int cupCount) {
        return clientRepository.findAllByCupCount(cupCount)
                .stream()
                .map(clientConverter::fromClientToClientDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDto findByPhone(long phone) {
        Client clientByPhone = clientRepository.findByPhone(phone);
        if (clientByPhone != null) {
            return clientConverter.fromClientToClientDto(clientByPhone);
        }
        return null;
    }

    @Override
    public void deleteClient(Integer clientId) {
        clientRepository.deleteById(clientId);
    }

    @Override
    public ClientDto saveClient(ClientDto clientDto) throws ValidationException {
        validateClientDto(clientDto);
        Client savedClient = clientRepository.save(clientConverter.fromClientDtoToClient(clientDto));
        return clientConverter.fromClientToClientDto(savedClient);
    }

    @Override
    public ClientDto incrementCupCountById(int clientId) {
        Client client = clientRepository.findById(clientId).get();
        client.setCupCount(client.getCupCount() + 1);
        if (client.getCupCount() >= 6) {
            client.setCupCount(0);
        }
        client.setAllCup(client.getAllCup() + 1);
        return clientConverter.fromClientToClientDto(clientRepository.save(client));
    }

    @Override
    public ClientDto decrementCupCountByPhone(ClientDto clientDto) throws ValidationException {
        validateClientDto(clientDto);
        Client client = clientConverter.fromClientDtoToClient(clientDto);
        client.setCupCount(client.getCupCount() - 1);
        if (client.getCupCount() == -1) {
            client.setCupCount(0);
        }
        return clientConverter.fromClientToClientDto(clientRepository.save(client));
    }

    private void validateClientDto(ClientDto clientDto) throws ValidationException {
        if (isNull(clientDto)) {
            throw new ValidationException("Object client is null");
        }
    }
}
