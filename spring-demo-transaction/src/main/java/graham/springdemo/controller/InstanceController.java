package graham.springdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import graham.springdemo.domain.Instance;
import graham.springdemo.mapper.InstanceMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/instance")
public class InstanceController {

    @Resource
    private InstanceMapper instanceMapper;

    @GetMapping("/get")
    public Instance getInstanceById() {
        return instanceMapper.selectOne(new QueryWrapper<Instance>().eq("id", 1));
    }
}
