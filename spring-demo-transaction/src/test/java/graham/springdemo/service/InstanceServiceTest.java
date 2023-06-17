package graham.springdemo.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class InstanceServiceTest {

    @Resource
    private InstanceService instanceService;

    @Test
    public void testUpdateInstance() throws InterruptedException {
        instanceService.updateInstance();
    }

}