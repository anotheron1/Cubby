package coffeeshop.cubby;

import coffeeshop.cubby.services.CubbyDataSourceService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class CubbyApplicationTests {

    @Autowired
    private CubbyDataSourceService cubbyService;

    @Test
    void contextLoads() {
        System.out.println(cubbyService.getAllClients().toString());
        System.out.println(cubbyService.findAllByCupCount(0).toString());
        System.out.println(cubbyService.findByPhone(89110470213L).toString());
    }

}
