package coffeeshop.cubby.controller;

import coffeeshop.cubby.dto.ClientDto;
import coffeeshop.cubby.services.CubbyDataSourceService;
import coffeeshop.cubby.structure.Client;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/clients")
@AllArgsConstructor
@Log
public class ClientController {

    private final CubbyDataSourceService cubbyService;

    @PostMapping("/save")
    public ClientDto saveClient(@RequestBody ClientDto clientDto) throws ValidationException {
        log.info("Handling save users: " + clientDto);
        return cubbyService.saveClient(clientDto);
    }

    @GetMapping("/findAll")
    public List<ClientDto> findAllClients() {
        log.info("Handling find all clients request");
        return cubbyService.getAllClients();
    }

    @GetMapping("/findByPhone")
    public ClientDto findByPhone(@RequestParam long phone) {
        log.info("Handling find by phone request: " + phone);
        return cubbyService.findByPhone(phone);
    }

//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Void> deleteUsers(@PathVariable Integer id) {
//        log.info("Handling delete user request: " + id);
//        cubbyService.deleteUser(id);
//        return ResponseEntity.ok().build();
//    }
}
