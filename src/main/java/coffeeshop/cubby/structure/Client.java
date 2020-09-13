package coffeeshop.cubby.structure;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "clients")
@Entity
@Data
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
    public String toString() {
        return "Client{" +
                "clientId = " + clientId +
                ", name = " + name +
                ", phone = " + phone +
                ", cupCount = " + cupCount +
                '}';
    }
}
