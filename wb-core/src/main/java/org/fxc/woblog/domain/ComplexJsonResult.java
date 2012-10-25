package org.fxc.woblog.domain;

import java.util.List;

/**
 * <p>The description about this file.</p>
 * Author: Leo Sun
 * Blog: http://fuxinci.com/
 * Date: 8/22/12
 * Since: 0.1
 */
public class ComplexJsonResult<T> extends JsonResult {
    private List<T> successList;
    private List<T> errorList;

    public List<T> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<T> errorList) {
        this.errorList = errorList;
    }

    public List<T> getSuccessList() {
        return successList;
    }

    public void setSuccessList(List<T> successList) {
        this.successList = successList;
    }
}
