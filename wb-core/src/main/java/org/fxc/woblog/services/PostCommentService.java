package org.fxc.woblog.services;

import org.fxc.woblog.dao.PostCommentDao;
import org.fxc.woblog.domain.PostComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>PostCommentService</p>
 * Author: Leo Sun
 * Blog: http://fuxinci.com/
 * Date: 11/2/12
 * Since: 0.1
 */
@Service
public class PostCommentService {

    @Autowired
    private PostCommentDao postCommentDao;

    public PostComment save(PostComment postComment) {
        return postCommentDao.save(postComment);
    }
}
