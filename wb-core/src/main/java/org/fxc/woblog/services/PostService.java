/**
 * <p>PostService</p>
 * Author: Leo Sun
 * Blog: http://fuxinci.com/
 * Date: 5/22/12
 * Since: 0.1
 */
package org.fxc.woblog.services;

import org.fxc.woblog.Constants;
import org.fxc.woblog.dao.PostDao;
import org.fxc.woblog.dao.TermDao;
import org.fxc.woblog.domain.Post;
import org.fxc.woblog.domain.PostTerm;
import org.fxc.woblog.domain.Term;
import org.fxc.woblog.domain.enmu.Taxonomy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

@Service
public class PostService
{
    @Autowired
    private PostDao postDao;

    @Autowired
    private TermDao termDao;

    @Transactional
    public Post save(Post post)
    {
        if(post.getId()== Constants.INVALID_ID){
            post.setCreateDate(new Date());
        }
        post.setModifiedDate(new Date());

        if(!post.getPostTerms().isEmpty()){
            for(PostTerm postTerm:post.getPostTerms())
            {
                if (postTerm.getTermName() != null && postTerm.getTermId() == null) {
                    Term term = termDao.save(new Term(postTerm.getTermName()));
                    postTerm.setTermId(term.getId());
                }
            }
        }
        return postDao.save(post);
    }

    @Transactional
    public void deletePost(List<String> idList) {
        for (String id : idList) {
            postDao.delete(Long.parseLong(id));
        }
    }

    @Transactional
    public Post findPost(Long id) {
        return postDao.findOne(id);
    }

    @Transactional
    public Page<Post> listPost(int pageSize, int pageIndex) {
        return postDao.findAll(new PageRequest(pageIndex, pageSize, null, Constants.ID));
    }
}
