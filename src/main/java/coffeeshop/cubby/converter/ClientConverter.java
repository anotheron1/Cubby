package coffeeshop.cubby.converter;

import coffeeshop.cubby.dto.ClientDto;
import coffeeshop.cubby.structure.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientConverter {
    public ClientDto fromClientToClientDto(Client clients) {
        return ClientDto.builder()
                .clientId(clients.getClientId())
                .name(clients.getName())
                .phone(clients.getPhone())
                .cupCount(clients.getCupCount())
                .build();
    }

    public Client fromClientDtoToClient(ClientDto clientDto) {
        return Client.builder()
                .clientId(clientDto.getClientId())
                .name(clientDto.getName())
                .phone(clientDto.getPhone())
                .cupCount(clientDto.getCupCount())
                .build();
    }
}
