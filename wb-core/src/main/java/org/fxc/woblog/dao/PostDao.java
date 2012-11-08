/**
 * <p>PostDao</p>
 * Author: Leo Sun
 * Blog: http://fuxinci.com/
 * Date: 5/22/12
 * Since: 0.1
 */
package org.fxc.woblog.dao;

import org.fxc.woblog.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PostDao extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {

    @Modifying
    @Query(value="update Post p set p.commentCount = p.commentCount+1 where p.id = ?1")
    public void addCommentCount(Long id);

    @Modifying
    @Query(value="update Post p set p.commentCount = p.commentCount-1 where p.id = ?1")
    public void reduceCommentCount(Long id);
}
