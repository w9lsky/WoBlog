/**
 * BaseModel
 *
 * @author http://fuxinci.com
 * Date: May 18, 2012
 */
package org.fxc.woblog.domain;

import java.io.Serializable;

import org.fxc.woblog.Constants;

import javax.persistence.*;

//@Entity
@MappedSuperclass
public abstract class BaseModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = Constants.INVALID_ID;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
