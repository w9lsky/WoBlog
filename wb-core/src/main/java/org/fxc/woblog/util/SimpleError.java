/**
 * SimpleError
 *
 * @author http://fuxinci.com
 * Date: Aug 20, 2012
 */

package org.fxc.woblog.util;

import org.fxc.woblog.Constants;
import org.springframework.validation.ObjectError;

public class SimpleError extends ObjectError {
    public SimpleError(String[] codes) {
        this(Constants.ERROR_MESSAGE, codes, null);
    }

    public SimpleError(String[] codes, Object... arguments) {
        super(Constants.ERROR_MESSAGE, codes, arguments, Constants.INTERNATIONALIZATION_ERROR);
    }

    public SimpleError(String objectName, String[] codes, Object... arguments) {
        super(objectName, codes, arguments, Constants.INTERNATIONALIZATION_ERROR);
    }
}
