/**
 * <p>PostTermDao</p>
 * Author: Leo Sun
 * Blog: http://fuxinci.com/
 * Date: 10/25/12
 * Since: 0.1
 */
package org.fxc.woblog.dao;

import org.fxc.woblog.domain.PostTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PostTermDao extends JpaRepository<PostTerm, Long>, JpaSpecificationExecutor<PostTerm> {
}
