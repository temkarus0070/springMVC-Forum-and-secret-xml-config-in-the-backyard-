package org.temkarus0070.MvcApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.temkarus0070.MvcApp.models.Post;

import javax.transaction.Transactional;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
}
