package org.fxc.woblog.domain;

import javax.persistence.*;

/**
 * <p>PostComment</p>
 * Author: Leo Sun
 * Blog: http://fuxinci.com/
 * Date: 11/2/12
 * Since: 0.1
 */
@Entity
@Table(name = "wb_post_comment")
public class PostComment extends BaseModel {

    public PostComment() {
    }


    public PostComment(Long postId, Comment comment) {
        this.postId = postId;
        this.comment = comment;
    }

    private Long postId;

    private Long commentId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "commentId", insertable = false, updatable = false)
    private Comment comment;

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
