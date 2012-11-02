package org.fxc.woblog.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * <p>Comment.</p>
 * Author: Leo Sun
 * Blog: http://fuxinci.com/
 * Date: 11/2/12
 * Since: 0.1
 */
@Entity
@Table(name = "wb_comment")
public class Comment extends BaseModel {
    @Size(min=1, max=60)
    private String name;

    @Pattern(regexp = "^[\\w-]+(\\.[\\w-]+)*@([a-z0-9-]+(\\.[a-z0-9-]+)*?\\.[a-z]{2,6}|(\\d{1,3}\\.){3}\\d{1,3})(:\\d{4})?$")
    private String mail;

    //@Pattern("")
    @Size(min=0, max=30)
    private String url;

    @Size(min=1, max=560)
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
