/**
 * <p>TermService</p>
 * Author: Leo Sun
 * Blog: http://fuxinci.com/
 * Date: 06/19/12
 * Since: 0.1
 */
package org.fxc.woblog.services;

import org.fxc.woblog.Constants;
import org.fxc.woblog.dao.TermDao;
import org.fxc.woblog.domain.Term;
import org.fxc.woblog.domain.enmu.Taxonomy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class TermService {

    @Autowired
    protected TermDao termDao;

    @Transactional
    public List<Term> findTerm(final String taxonomy) {
        return termDao.findAll(new Specification<Term>() {
            public Predicate toPredicate(Root<Term> termRoot, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(termRoot.get(Constants.TAXONOMY), Taxonomy.valueOf(taxonomy.toUpperCase()));
            }
        }, new Sort(Constants.ID));
    }

    @Transactional
    public Page<Term> findTerm(final String taxonomy, int pageSize, int pageIndex) {
        return termDao.findAll(new Specification<Term>() {
            public Predicate toPredicate(Root<Term> termRoot, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(termRoot.get(Constants.TAXONOMY), Taxonomy.valueOf(taxonomy.toUpperCase()));
            }
        }, new PageRequest(pageIndex, pageSize, null, Constants.ID));
    }

    @Transactional
    public List<Term> listTerm() {
        return termDao.findAll(new Sort(Constants.ID));
    }

    @Transactional
    public Term save(Term term) {
        return termDao.saveAndFlush(term);
    }

    @Transactional
    public void delete(int categoryId) {
        // the default category(id=0) can not be deleted.
        if (categoryId != Constants.INVALID_ID) {
            Term term = termDao.findOne((long) categoryId);
            if (term.getId() != null) {
                termDao.delete(term);
                return;
            } else {
                throw new RuntimeException("Not founded.");
            }
        }
        throw new RuntimeException("The default category(id=0) can not be deleted");
    }

    @Transactional
    public Page<Term> loadCategory(int pageSize, int pageIndex) {
        return termDao.findAll(new Specification<Term>() {
            public Predicate toPredicate(Root<Term> termRoot, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(termRoot.get(Constants.TAXONOMY), Taxonomy.CATEGORY);
            }
        }, new PageRequest(pageIndex, pageSize, null, Constants.ID));
    }
}
