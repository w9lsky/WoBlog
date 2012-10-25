<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="include.jsp" %>
<html>
<head>
    <title>Simple jsp page</title>
</head>
<body>
<div class="col_right">
    <c:set var="ObjPage" value="${PostPage}"/>
    <h2><spring:message code="info.listPost.title"/></h2> <a href="${admin_url}/createPost" >写文章</a>
     <div id="ajax_response">
     </div>
        <!-- before table start-->
    <p>
    <div style="float:left;left:10px;">
        <select class="operationSlt">
            <option value=""><spring:message code="info.batchOperation"/></option>
            <option value="recycle"><spring:message code="info.listPost.moveRecycle"/></option>
            <option value="delete"><spring:message code="info.listPost.physicallyDelete"/></option>
        </select>
        <button class="apply_btn"><spring:message code="info.listPost.apply"/></button>
    </div>
    <div style="float:right;margin-right:20px;">
        ${ObjPage.totalElements}<spring:message code="info.listPost.objCount"/>
    </div>
    </p>
        <!-- before table start-->

    <table class="wb-list-table">
        <thead>
        <tr>
            <th scope="col" id="cb_head" class="check-column"><input type="checkbox" class="select_all_checkbox"
                                                                     value=""></th>
            <th scope="col" id="title_head" class=""><spring:message code="info.title"/></th>
            <th scope="col" id="category_head" class=""><spring:message code="info.category"/></th>
            <th scope="col" id="tag_head" class=""><spring:message code="info.tag"/></th>
            <th scope="col" id="comment_head" class="column-posts"><spring:message code="info.comment"/></th>
            <th scope="col" id="date_head" class="column-posts"><spring:message code="info.date"/></th>
        </tr>
        </thead>
        <tfoot>
        <tr>
            <th scope="col" id="cb_foot" class="check-column"><input type="checkbox" class="select_all_checkbox"
                                                                     value=""></th>
            <th scope="col" id="title_foot" class=""><spring:message code="info.title"/></th>
            <th scope="col" id="category_foot" class=""><spring:message code="info.category"/></th>
            <th scope="col" id="tag_foot" class=""><spring:message code="info.tag"/></th>
            <th scope="col" id="comment_foot" class="column-posts"><spring:message code="info.comment"/></th>
            <th scope="col" id="date_foot" class="column-posts"><spring:message code="info.date"/></th>
        </tr>
        </tfoot>
        <tbody>
        <c:if test="${ObjPage.content != null}">
            <c:forEach var="obj" items="${ObjPage.content}" varStatus="status">
                <c:choose>
                    <c:when test="${status.index%2==0}">
                        <tr class="odd" id="${obj.id}">
                    </c:when>
                    <c:otherwise>
                        <tr class="even" id="${obj.id}">
                    </c:otherwise>
                </c:choose>
                <th class="check-column"><input type="checkbox" value="${obj.id}"></th>
                <td class="title column-title"><a href="javascript:void(0)" onclick="" id="name">${obj.title}</a>

                    <div class="row-action" style='visibility:hidden;'>
                        <a href="javascript:void(0)" onclick="updateObj(${obj.id})"><spring:message code="info.edit"/></a>|
                            <%--<a href="javascript:void(0)" onclick="">快速编辑</a>|--%>
                        <a href="javascript:void(0)" onclick="recycleObj('${obj.id}')"><spring:message code="info.listPost.moveRecycle"/></a>
                        <a href="javascript:void(0)" onclick="deleteObj('${obj.id}')"><spring:message code="info.listPost.physicallyDelete"/></a>
                    </div>
                </td>
                <c:if test="${obj.postTerms!=null}">
                    <c:forEach var="postTerm" items="${obj.postTerms}" varStatus="status">
                        <c:set var="term" value="${postTerm.term}"/>
                        <c:choose>
                            <c:when test="${term.taxonomy=='CATEGORY'}">
                                <c:if test="${category!=null}">
                                    <c:set var="category" value="${category},${term.name}"/>
                                </c:if>
                                <c:if test="${category==null}">
                                    <c:set var="category" value="${term.name}"/>
                                </c:if>
                            </c:when>
                            <c:when test="${term.taxonomy=='TAG'}">
                                <c:if test="${tag!=null}">
                                    <c:set var="tag" value="${tag},${term.name}"/>
                                </c:if>
                                <c:if test="${tag==null}">
                                    <c:set var="tag" value="${term.name}"/>
                                </c:if>

                            </c:when>
                        </c:choose>
                    </c:forEach>
                </c:if>
                <td class="category column-category">${category}</td>
                <td class="tag column-tag">${tag}</td>
                <c:remove var="category"/>
                <c:remove var="tag"/>
                <td class="comment column-comment">common</td>
                <td class="date column-date">${obj.modifiedDate}</td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>

    <!-- after table start-->
    <p>
    <div style="float:left;left:10px;">
        <select class="operationSlt">
                        <option value=""><spring:message code="info.batchOperation"/></option>
            <option value="recycle"><spring:message code="info.listPost.moveRecycle"/></option>
            <option value="delete"><spring:message code="info.listPost.physicallyDelete"/></option>
        </select>
        <%--<input type="button" value="应用" class="apply_btn"/>--%>
        <button class="apply_btn"><spring:message code="info.listPost.apply"/></button>
    </div>
    <div style="float:right;margin-right:20px;">
        ${ObjPage.totalElements}<spring:message code="info.listPost.objCount"/>
    </div>
    </p>
<!-- after table end-->
	</div>
<script type="text/javascript" src="js/listPost.js"></script>
<link rel="stylesheet" href="css/admin/wtable.css">
<link rel="stylesheet" href="css/admin/listPost.css">
</body>
</html>