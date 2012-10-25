package org.fxc.woblog.domain;

import javax.persistence.*;
import java.util.*;

/**
 * <p>The description about this file.</p>
 * Author: Leo Sun
 * Blog: http://fuxinci.com/
 * Date: 10/17/12
 * Since: 0.1
 */
@Entity
@Table(name = "wb_post_term")
public class PostTerm extends BaseModel {

    private Long postId;

    private Long termId;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "termId",insertable = false,updatable = false)
    private Term term;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "postId", insertable = false, updatable = false)
    private Post post;

    @Transient
    private String termName;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public Long getTermId() {
        return termId;
    }

    public void setTermId(Long termId) {
        this.termId = termId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }
}
