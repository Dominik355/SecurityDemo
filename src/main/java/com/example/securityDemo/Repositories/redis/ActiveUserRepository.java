package com.example.securityDemo.Repositories.redis;

import com.example.securityDemo.Models.ActiveUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.io.Serializable;

@Repository
public interface ActiveUserRepository extends CrudRepository<ActiveUser, String> {

    ActiveUser deleteBySessionId(String sessionId);

    ActiveUser findBySessionId(String sessionId);

}
