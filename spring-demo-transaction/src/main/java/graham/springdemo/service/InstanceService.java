package graham.springdemo.service;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import graham.springdemo.domain.Instance;
import graham.springdemo.mapper.InstanceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
@Transactional
public class InstanceService {

    @Resource
    private InstanceMapper instanceMapper;

    @Resource
    private TransactionTemplate transactionTemplate;

    /**
     * 结论：事务中加入HTTP请求的回滚示例
     */
    public void updateInstance() throws InterruptedException {
        int updateCount = instanceMapper.updateById(Instance.builder()
                .id(1)
                .insValue("hangzhou2233445")
                .build());
        log.info("updateCount: {}", updateCount);

        Thread.sleep(2000);

        // 提交事务
        transactionTemplate.execute(status -> {
            try {
                int code = getInstance();
                // 处理HTTP响应
                if (code != HttpStatus.BAD_GATEWAY.value()) {
                    // 如果响应不符合预期，抛出异常
                    throw new RuntimeException("HTTP request failed or returned unexpected result");
                }
                // 事务成功完成，返回null
                return null;
            } catch (Exception e) {
                // 标记事务回滚
                status.setRollbackOnly();
                // 继续抛出异常以触发回滚
                throw e;
            }
        });

        Thread.sleep(2000);

        List<Instance> instances = instanceMapper.selectList(null);
        log.info("instancesList: {}", JSONUtil.toJsonStr(instances));
    }

    public int getInstance() {
        try (HttpResponse httpResponse = HttpRequest.get("http://127.0.0.1:18080/instance/get").execute()) {
            String body = httpResponse.body();
            log.info("body: {}", body);
            return httpResponse.getStatus();
        }
    }
}
