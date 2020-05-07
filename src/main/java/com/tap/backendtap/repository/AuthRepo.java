package com.tap.backendtap.repository;

import com.tap.backendtap.model.Authentication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Florina on 5/6/2020
 */
@Repository
public interface AuthRepo extends CrudRepository<Authentication, Long> {
}
