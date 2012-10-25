/**   
 * Constants
 *  
 * @author http://fuxinci.com
 * Date: May 18, 2012
 */   
package org.fxc.woblog;

public interface Constants {
    Long INVALID_ID = 0L;
    String PAGE_SIZE = "10";
    String PAGE_INDEX = "0";
    int DEFAULT_PAGE_SIZE = 10;
    int DEFAULT_PAGE_INDEX = 0;
    String ERROR_MESSAGE = "Error_Message";
    String SUCCESS_MESSAGE = "Success_Message";
    String  INTERNATIONALIZATION_ERROR = "internationalization.error";

    // column name
    String ID = "id";
    String TAXONOMY = "taxonomy";

    // for request or session
    String VIEW_LIST_POST = "listPost";
    String VIEW_EDIT_POST = "editPost";
    String VIEW_POST = "post";
    String VIEW_CATEGORY = "category";
    String VIEW_TAG = "tag";
    String VIEW_NOT_FOUND = "404";

    String NEW_TAG_PREFIX = "NEW_TAG_";
}
