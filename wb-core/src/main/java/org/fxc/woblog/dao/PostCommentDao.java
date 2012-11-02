package org.fxc.woblog.dao;

import org.fxc.woblog.domain.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * <p>PostCommentDao.</p>
 * Author: Leo Sun
 * Blog: http://fuxinci.com/
 * Date: 11/2/12
 * Since: 0.1
 */
public interface PostCommentDao extends JpaRepository<PostComment, Long>,JpaSpecificationExecutor<PostComment> {
}
