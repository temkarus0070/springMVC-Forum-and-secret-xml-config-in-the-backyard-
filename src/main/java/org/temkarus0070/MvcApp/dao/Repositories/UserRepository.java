package org.temkarus0070.MvcApp.dao.Repositories;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.temkarus0070.MvcApp.models.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    @Override
    @Cacheable(value = "user",key = "#s")
    Optional<User> findById(String s);

    @Override
            @CachePut(value = "user",key = "#s.username")
    <S extends User> S save(S s);

    @Override
    @CacheEvict(value = "user",allEntries = true)
    void delete(User user);

    @Override
    @CacheEvict(value = "user",allEntries = true)
    void deleteById(String s);
}
