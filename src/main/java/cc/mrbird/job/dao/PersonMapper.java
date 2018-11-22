package cc.mrbird.job.dao;

import cc.mrbird.common.config.MyMapper;
import cc.mrbird.job.domain.Person;

public interface PersonMapper extends MyMapper<Person> {
    Person selectByMsw(Integer id);
}
