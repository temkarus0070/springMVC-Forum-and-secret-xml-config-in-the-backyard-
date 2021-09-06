package org.temkarus0070.MvcApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.temkarus0070.MvcApp.models.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
}
