package coffeeshop.cubby.services;

import coffeeshop.cubby.repository.ClientRepository;
import coffeeshop.cubby.structure.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class CubbyDataSourceServiceImpl implements CubbyDataSourceService {

    private final ClientRepository clientRepository;

    public CubbyDataSourceServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> getAllClients() {
        return StreamSupport.stream(clientRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public List<Client> findAllByCupCount(int cupCount) {
        return clientRepository.findAllByCupCount(cupCount);
    }

    @Override
    public Client findByPhone(long phone) {
        return clientRepository.findByPhone(phone);
    }
}
