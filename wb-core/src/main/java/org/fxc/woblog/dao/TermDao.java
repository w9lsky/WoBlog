/**
 * Term
 *
 * @author fuxinci.com
 * Date: May 21, 2012
 */
package org.fxc.woblog.dao;

import org.fxc.woblog.domain.Term;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TermDao extends JpaRepository<Term, Long> ,JpaSpecificationExecutor<Term> {
}
