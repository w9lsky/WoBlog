<%--
 * The description about this file.       
 * Author: Leo Sun
 * Blog: http://fuxinci.com/
 * Date: 11/6/12
 * Since: 0.1
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="include.jsp" %>
<html>
<head>
    <title>Simple jsp page</title>
</head>
<body>
<div id="ajax_response">
</div>
<div class="list_comment_div">

    <c:set var="Page" value="${PostCommentPage}"/>
    <p class="status_p">
        <a class="all_a" href="${admin_url}/listComment">全部(${CountMap.REJECT+CountMap.APPROVED+CountMap.SPAM+CountMap.RECYCLE})</a>|
        <a class="reject_a" href="${admin_url}/listComment?status=REJECT">未审批(${CountMap.REJECT})</a>|
        <a class="approved_a" href="${admin_url}/listComment?status=APPROVED">批准(${CountMap.APPROVED})</a>|
        <a class="spam_a" href="${admin_url}/listComment?status=SPAM">垃圾评论(${CountMap.SPAM})</a>|
        <a class="recycle_a" href="${admin_url}/listComment?status=RECYCLE">回收站(${CountMap.RECYCLE})</a>
    </p>

    <p>
    <div style="float:left;left:10px;">
        <select id="operation_0" class="operation">
            <option value=""><spring:message code="info.batchOperation"/></option>
            <option value="REJECT">驳回</option>
            <option value="APPROVED">批准</option>
            <option value="SPAM">标记为垃圾评论</option>
            <option value="RECYCLE">移到回收站</option>
        </select>
        <button class="apply_btn" onclick="applyHandler(0)"><spring:message code="info.listPost.apply"/></button>
    </div>
    <div style="float:right;margin-right:20px;">
        ${Page.numberOfElements}
        <spring:message code="info.listPost.objCount"/>

        <c:forEach var="index" begin="1" end="${Page.totalPages}">
        <c:if test="${Page.number+1==index}">
        <label style="border: 1px solid #FF0000;">
            </c:if>
            <c:if test="${Page.number+1!=index}">
            <label>
                </c:if>
                <c:if test="${RequestUrl==null}">
                <a style="text-decoration:blink;" href="${admin_url}/listComment?pageIndex=${index}">${index}</a>
            </label>
            </c:if>
            <c:if test="${RequestUrl!=null}">
            <label>
                <a style="text-decoration:blink;" href="${admin_url}/listComment?pageIndex=${index}">${index}</a>
            </label>
            </c:if>
            </c:forEach>

    </div>
    </p>
    <table class="wb-list-table">
        <thead>
        <tr>
            <th scope="col" id="cb_head" class="check-column"><input type="checkbox" class="select_all_checkbox"
                                                                     value=""></th>
            <th scope="col" id="author_head" class=""><spring:message code="info.term.name"/></th>
            <th scope="col" id="comment_head" class=""><spring:message code="info.term.description"/></th>
            <th scope="col" id="response_head" class=""><spring:message code="info.term.slug"/></th>
        </tr>
        </thead>
        <tfoot>
        <tr>
            <th scope="col" id="cb_foot" class="check-column"><input type="checkbox" class="select_all_checkbox"
                                                                     value=""></th>
            <th scope="col" id="author_foot" class=""><spring:message code="info.term.name"/></th>
            <th scope="col" id="comment_foot" class=""><spring:message code="info.term.description"/></th>
            <th scope="col" id="response_foot" class=""><spring:message code="info.term.slug"/></th>
        </tr>
        </tfoot>
        <tbody>
        <c:if test="${Page.content != null}">
            <c:forEach var="item" items="${Page.content}" varStatus="status">
                <c:if test="${Status==null||Status==item.commentStatus}">
                    <c:choose>
                        <c:when test="${status.index%2==0}">
                            <tr class="odd" id="${item.id}">
                        </c:when>
                        <c:otherwise>
                            <tr class="even" id="${item.id}">
                        </c:otherwise>
                    </c:choose>
                    <th class="check-column">
                        <input type="checkbox" value="${item.id}">
                        <input type="hidden" id="status_${item.id}" value="${item.commentStatus}">
                         <input type="hidden" id="postId_${item.id}" value="${item.post.id}">
                    </th>
                    <td class="author column-author">${item.authorName}<br>${item.authorMail}<br>${item.authorUrl}<br>${item.authorIp}
                    </td>

                    <td class="comment column-comment">${item.content}
                        <div class="row-action" style="visibility:hidden;">
                            <a href="javascript:void(0)" onclick="updateObj('${item.id}','${item.commentStatus}','REJECT')">驳回</a>|
                            <a href="javascript:void(0)" onclick="updateObj('${item.id}','${item.commentStatus}','APPROVED')">批准</a>
                            <a href="javascript:void(0)" onclick="updateObj('${item.id}')">回复</a>
                            <a href="javascript:void(0)" onclick="updateObj('${item.id}','${item.commentStatus}','SPAM')">标记为垃圾评论</a>
                            <a href="javascript:void(0)" onclick="updateObj('${item.id}','${item.commentStatus}','RECYCLE')">移到回收站</a>
                        </div>
                    </td>
                    <td class="response column-response">${item.post.title}<br>${item.post.commentCount}</td>
                    </tr>
                </c:if>
            </c:forEach>
        </c:if>
        </tbody>
    </table>
    <p>

    <div style="float:left;left:10px;">
        <select id="operation_1" class="operation">
            <option value=""><spring:message code="info.batchOperation"/></option>
            <option value="REJECT">驳回</option>
            <option value="APPROVED">批准</option>
            <option value="SPAM">标记为垃圾评论</option>
            <option value="RECYCLE">移到回收站</option>
        </select>
        <button class="apply_btn" onclick="applyHandler(1)"><spring:message code="info.listPost.apply"/></button>
    </div>
    <div style="float:right;margin-right:20px;">
        ${Page.numberOfElements}
        <spring:message code="info.listPost.objCount"/>
        <div style="float:right;margin-right:20px;">
            ${Page.numberOfElements}
            <spring:message code="info.listPost.objCount"/>

            <c:forEach var="index" begin="1" end="${Page.totalPages}">
            <c:if test="${Page.number+1==index}">
            <label style="border: 1px solid #FF0000;">
                </c:if>
                <c:if test="${Page.number+1!=index}">
                <label>
                    </c:if>
                    <c:if test="${RequestUrl==null}">
                    <a style="text-decoration:blink;" href="${admin_url}/listComment?pageIndex=${index}">${index}</a>
                </label>
                </c:if>
                <c:if test="${RequestUrl!=null}">
                <label>
                    <a style="text-decoration:blink;" href="${admin_url}/listComment?pageIndex=${index}">${index}</a>
                </label>
                </c:if>
                </c:forEach>
        </div>
    </div>
    </p>
</div>

<script type="text/javascript" src="js/listComment.js"></script>
<link rel="stylesheet" href="css/admin/wtable.css">
<link rel="stylesheet" href="css/admin/listComment.css">
</body>
</html>