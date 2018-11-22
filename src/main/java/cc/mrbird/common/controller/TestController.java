package cc.mrbird.common.controller;

import cc.mrbird.common.annotation.Limit;
import cc.mrbird.job.dao.PersonMapper;
import cc.mrbird.job.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class TestController {

    @Autowired
    private PersonMapper personMapper;
    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger();

    /**
     * 测试限流注解，下面配置说明该接口 60秒内最多只能访问 10次，保存到redis的键名为 limit_test，
     * 即 prefix + "_" + key，也可以根据 IP 来限流，需指定limitType = LimitType.IP
     */
    @Limit(key = "test", period = 60, count = 10, name = "resource", prefix = "limit")
    @GetMapping("/test")
    public int testLimiter() {
        return ATOMIC_INTEGER.incrementAndGet();
    }

    @GetMapping("/users")
    public List<Person> getPerson(){
        return personMapper.selectAll();
    }

    @GetMapping("/users/{id}")
    public Person getPersonByMsw(@PathVariable Integer id){
        return personMapper.selectByMsw(id);
    }
}
