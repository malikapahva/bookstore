package com.challengers.repo;

import com.challengers.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Malika(mxp134930) on 11/6/2015.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String name);

}
