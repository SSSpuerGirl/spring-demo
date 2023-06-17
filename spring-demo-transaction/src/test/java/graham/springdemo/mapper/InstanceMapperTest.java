package graham.springdemo.mapper;

import graham.springdemo.domain.Instance;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class InstanceMapperTest {

    @Resource
    private InstanceMapper instanceMapper;

    @Test
    public void insertTest() {
        instanceMapper.insert(Instance.builder()
                .insName("city").insValue("hangzhou")
                .build());
    }

    @Test
    public void queryTest() {
        instanceMapper.selectList(null).forEach(System.out::println);
    }

    @Test
    public void updateTest() {
        instanceMapper.updateById(Instance.builder()
                .id(1)
                .insValue("hangzhou")
                .build());
    }

}