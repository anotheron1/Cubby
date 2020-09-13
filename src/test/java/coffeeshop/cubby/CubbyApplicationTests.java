package coffeeshop.cubby;

import coffeeshop.cubby.services.CubbyDataSourceService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class CubbyApplicationTests {

    @Autowired
    private CubbyDataSourceService cubbyService;

    @Test
    void contextLoads() {
//        System.out.println(cubbyService.getAllClients().toString());
//        System.out.println(cubbyService.findAllByCupCount(1).toString());
//        System.out.println(cubbyService.findByPhone(89992607697L).toString());
        System.out.println(cubbyService.incrementCupCountById(2).toString());
//        cubbyService.deleteClient(6);
    }
}
