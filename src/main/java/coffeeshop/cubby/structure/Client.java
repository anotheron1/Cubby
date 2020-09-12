package coffeeshop.cubby.structure;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "clients")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Client {
    @Id
    @Column(name = "client_id")
    private int clientId;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private long phone;

    @Column(name = "cup_count")
    private int cupCount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return clientId == client.clientId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId);
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId = " + clientId +
                ", name = " + name +
                ", phone = " + phone +
                ", cupCount = " + cupCount +
                '}';
    }
}
