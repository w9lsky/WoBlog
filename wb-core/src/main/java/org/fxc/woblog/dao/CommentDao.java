package org.fxc.woblog.dao;

import org.fxc.woblog.domain.Comment;
import org.fxc.woblog.domain.enmu.CommentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 * <p>The description about this file.</p>
 * Author: Leo Sun
 * Blog: http://fuxinci.com/
 * Date: 11/6/12
 * Since: 0.1
 */
public interface CommentDao extends JpaRepository<Comment, Long>,JpaSpecificationExecutor<Comment> {

    @Query("select c.commentStatus,count(c.commentStatus) from Comment c group by c.commentStatus")
    public List<Object> getCountByStatus();

    @Modifying
    @Query("update Comment c set c.commentStatus = ?2 where c.id=?1")
    public int updateCommentStatus(Long id,CommentStatus status);
}
