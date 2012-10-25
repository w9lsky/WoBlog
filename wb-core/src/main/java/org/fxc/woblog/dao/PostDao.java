/**
 * PostMapper
 *
 * @author 傅心词
 * Date: 2012-5-22
 */
package org.fxc.woblog.dao;

import org.fxc.woblog.domain.Post;
import org.fxc.woblog.domain.Term;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PostDao extends JpaRepository<Post, Long>,JpaSpecificationExecutor<Post> {
}
