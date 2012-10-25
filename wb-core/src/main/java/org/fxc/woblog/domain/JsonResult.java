package org.fxc.woblog.domain;

import java.io.Serializable;

/**
 * <p>The description about this file.</p>
 * Author: Leo Sun
 * Blog: http://fuxinci.com/
 * Date: 8/22/12
 * Since: 0.1
 */
public class JsonResult implements Serializable{

    private boolean success;
    private String message;

    public JsonResult() {}

    public JsonResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
