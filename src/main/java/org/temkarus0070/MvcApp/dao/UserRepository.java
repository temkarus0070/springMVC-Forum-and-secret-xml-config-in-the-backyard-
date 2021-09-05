package org.temkarus0070.MvcApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.temkarus0070.MvcApp.models.User;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
}