/**
 * <p>TermDao</p>
 * Author: Leo Sun
 * Blog: http://fuxinci.com/
 * Date: 5/21/12
 * Since: 0.1
 */
package org.fxc.woblog.dao;

import org.fxc.woblog.domain.Term;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TermDao extends JpaRepository<Term, Long> ,JpaSpecificationExecutor<Term> {
}
