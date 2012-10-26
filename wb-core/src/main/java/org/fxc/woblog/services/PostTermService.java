/**
 * <p>PostTermService</p>
 * Author: Leo Sun
 * Blog: http://fuxinci.com/
 * Date: 10/25/12
 * Since: 0.1
 */
package org.fxc.woblog.services;

import org.fxc.woblog.Constants;
import org.fxc.woblog.dao.PostTermDao;
import org.fxc.woblog.domain.PostTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Service
public class PostTermService {

    @Autowired
    private PostTermDao postTermDao;

    public Page<PostTerm> listPostByTermId(final Long termId,int pageSize,int pageIndex){
        return postTermDao.findAll(new Specification<PostTerm>(){
            public Predicate toPredicate(Root<PostTerm> postTermRoot, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(postTermRoot.get("termId"),termId);
            }
        },new PageRequest(pageSize,pageIndex,null, Constants.ID));
    }
}
