package org.fxc.woblog.services;

import org.fxc.woblog.Constants;
import org.fxc.woblog.dao.CommentDao;
import org.fxc.woblog.dao.PostDao;
import org.fxc.woblog.domain.Comment;
import org.fxc.woblog.domain.enmu.CommentStatus;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>CommentService</p>
 * Author: Leo Sun
 * Blog: http://fuxinci.com/
 * Date: 11/7/12
 * Since: 0.1
 */
@Service
@Transactional
public class CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private PostDao postDao;


    public Comment save(Comment comment) {

        return commentDao.save(comment);
    }

    public Page<Comment> listComment(final Long postId, int pageIndex, int pageSize) {
        if (postId != null) {
            return commentDao.findAll(new Specification<Comment>() {
                public Predicate toPredicate(Root<Comment> commentRoot, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    return cb.and(cb.equal(commentRoot.<Long>get("postId"), postId),
                                  cb.equal(commentRoot.<CommentStatus>get("commentStatus"),CommentStatus.APPROVED));
                }
            }, new PageRequest(pageIndex, pageSize, null, Constants.ID));
        } else {
            return commentDao.findAll(new PageRequest(pageIndex, pageSize, null, Constants.ID));
        }
    }

    public Page<Comment> listComment(final CommentStatus status, int pageIndex, int pageSize) {
        if (status != null) {
            return commentDao.findAll(new Specification<Comment>() {
                public Predicate toPredicate(Root<Comment> commentRoot, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    return cb.equal(commentRoot.<CommentStatus>get("commentStatus"), status);
                }
            }, new PageRequest(pageIndex, pageSize, null, Constants.ID));
        } else {
            return commentDao.findAll(new PageRequest(pageIndex, pageSize, null, Constants.ID));
        }
    }

    public Map<Object,Object> getCountByStatus(){
        List<Object> list =  commentDao.getCountByStatus();
        Map<Object,Object> statusCount = new HashMap<Object, Object>();
        for(Object obj : list){
            statusCount.put(((Object[])obj)[0].toString(),((Object[])obj)[1]);
        }
        return statusCount;
    }

    public int edit(List<Map<String,Object>> commentList, String status) {
        int count = 0;
        for (Map<String,Object> comment : commentList) {
            count += edit(comment, status);
        }
        return count;
    }

    public int edit(Map<String,Object> comment, String status) {
        try {
            CommentStatus oStatus = CommentStatus.valueOf(status);
            Long id = Long.parseLong(comment.get("id").toString());
            CommentStatus nStatus = CommentStatus.valueOf(comment.get("commentStatus").toString());

            commentDao.updateCommentStatus(id,oStatus);
            if (!CommentStatus.APPROVED.equals(nStatus)&&CommentStatus.APPROVED.equals(oStatus)) {
                postDao.addCommentCount(Long.parseLong(comment.get("postId").toString()));
            }else if (CommentStatus.APPROVED.equals(nStatus)&&!CommentStatus.APPROVED.equals(oStatus)) {
                postDao.reduceCommentCount(Long.parseLong(comment.get("postId").toString()));
            }
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
