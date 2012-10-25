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

public interface PostDao extends JpaRepository<Post, Long>,JpaSpecificationExecutor<Post> {
}
