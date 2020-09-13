package coffeeshop.cubby.services;

import coffeeshop.cubby.converter.ClientConverter;
import coffeeshop.cubby.dto.ClientDto;
import coffeeshop.cubby.repository.ClientRepository;
import coffeeshop.cubby.structure.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.ArrayList;
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
    public ClientDto saveClient(ClientDto clientDto) throws ValidationException {
        validateClientDto(clientDto);
        Client savedClient = clientRepository.save(clientConverter.fromClientDtoToClient(clientDto));
        return clientConverter.fromClientToClientDto(savedClient);
    }

    private void validateClientDto(ClientDto clientDto) throws ValidationException {
        if (isNull(clientDto)) {
            throw new ValidationException("Object client is null");
        }
    }
}
