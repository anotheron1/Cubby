package coffeeshop.cubby.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientDto {
    private int clientId;
    private String name;
    private long phone;
    private int cupCount;
}
