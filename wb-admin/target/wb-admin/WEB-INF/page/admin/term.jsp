<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="include.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="info.${taxonomy}.title"/></title>
</head>
<body>
<div class="title">
    <h2><spring:message code="info.${taxonomy}.title"/></h2>

    <div class=col_left>
        <form:form id="category" action="${admin_url}/addTerm?taxonomy=${taxonomy}" commandName="term" method="post">
            <div id="ajax_response">
                <div class='error'>
                    <form:errors path="*"/>
                </div>
            </div>
            <h3><spring:message code="info.${taxonomy}.add"/></h3>
            <%--<input type="hidden" id="id">--%>
            <form:hidden path="id"/>
            <label><spring:message code="info.term.name"/></label>

            <p>
                <form:input path="name"/>
            </p>

            <p>
                这将是它在站点上显示的名字。</p>
            <label>别名</label>

            <p>
                <form:input path="slug"/>
            </p>

            <p>“别名”是在 URL 中使用的别称，它可以令 URL 更美观。通常使用小写，只能包含字母，数字和连字符（-）。</p>

            <c:if test="${taxonomy=='category'}">
                <label>父级</label>

                <p>
                    <form:select path="parent">
                        <c:if test="${TermList.content != null}">
                            <c:forEach var="category" items="${TermList.content}">
                                <form:option value="${category.id}">${category.name}</form:option>
                            </c:forEach>
                        </c:if>
                    </form:select>
                </p>

                <p>分类目录和标签不同，它可以有层级关系。您可以有一个“音乐”分类目录，在这个目录下可以有叫做“流行”和“古典”的子目录。</p>
            </c:if>
            <label>描述</label>

            <p>
                <form:textarea path="description" rows="5" cols="40"></form:textarea>
            </p>

            <p>描述只会在一部分主题中显示。</p>

            <p>
                <form:button name="add_term_btn"><spring:message code="info.${taxonomy}.add"/></form:button>
            </p>
        </form:form>
    </div>
    <div class="col_right">
        <p>

        <div style="float:left;left:10px;">
            <select class="operation">
                <option value=""><spring:message code="info.batchOperation"/></option>
                <option value="delete"><spring:message code="info.delete"/></option>
            </select>
        <button class="apply_btn" name="${taxonomy}"><spring:message code="info.listPost.apply"/></button>
        </div>
        <div style="float:right;margin-right:20px;">
            ${TermList.size}<spring:message code="info.listPost.objCount"/>
        </div>
        </p>
        <table class="wb-list-table">
            <thead>
            <tr>
                <th scope="col" id="cb_head" class="check-column"><input type="checkbox" class="select_all_checkbox"
                                                                         value=""></th>
                <th scope="col" id="name_head" class=""><spring:message code="info.term.name"/></th>
                <th scope="col" id="description_head" class=""><spring:message code="info.term.description"/></th>
                <th scope="col" id="slug_head" class=""><spring:message code="info.term.slug"/></th>
                <th scope="col" id="posts_head" class="column-posts"><spring:message code="info.term.post"/></th>
            </tr>
            </thead>
            <tfoot>
            <tr>
                <th scope="col" id="cb_foot" class="check-column"><input type="checkbox" class="select_all_checkbox"
                                                                         value=""></th>
                <th scope="col" id="name_foot" class=""><spring:message code="info.term.name"/></th>
                <th scope="col" id="description_foot" class=""><spring:message code="info.term.description"/></th>
                <th scope="col" id="slug_foot" class=""><spring:message code="info.term.slug"/></th>
                <th scope="col" id="posts_foot" class="column-posts"><spring:message code="info.term.post"/></th>
            </tr>
            </tfoot>
            <tbody>
            <c:if test="${TermList.content != null}">
                <c:forEach var="term" items="${TermList.content}" varStatus="status">
                    <c:choose>
                        <c:when test="${status.index%2==0}">
                            <tr class="odd" id="${term.id}">
                        </c:when>
                        <c:otherwise>
                            <tr class="even" id="${term.id}">
                        </c:otherwise>
                    </c:choose>
                    <th class="check-column"><input type="checkbox" value="${term.id}"></th>
                    <td class="name column-name"><a href="javascript:void(0)" onclick="" id="name">${term.name}</a>

                        <div class="row-action" style='visibility:hidden;'>
                            <a href="javascript:void(0)" onclick="updateTerm(${term.id})"><spring:message code="info.edit"/></a>|
                                <%--<a href="javascript:void(0)" onclick="">快速编辑</a>|--%>
                            <a href="javascript:void(0)" onclick="deleteTerm('${term.id}')"><spring:message code="info.delete"/></a>
                        </div>
                    </td>
                    <td class="description column-description">${term.description}</td>
                    <td class="slug column-slug">${term.slug}</td>
                    <td class="count column-count">${term.count}
                        <input type="hidden" id="${term.id}" class="parent" value="${term.parent}">
                    </td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
        <p>

        <div style="float:left;left:10px;">
            <select class="operation">
                <option value=""><spring:message code="info.batchOperation"/></option>
                <option value="delete"><spring:message code="info.delete"/></option>
            </select>
        <button class="apply_btn"  name="${taxonomy}"><spring:message code="info.listPost.apply"/></button>
        </div>
        <div style="float:right;margin-right:20px;">
            ${TermList.size}<spring:message code="info.listPost.objCount"/>
        </div>
        </p>
        <br>

        <p>注意：删除分类目录不会把该分类目录下的文章一并删除。文章会被归入“未分类”分类目录。</p>
    </div>
</div>

<script type="text/javascript" src="js/term.js"></script>
<link rel="stylesheet" href="css/admin/wtable.css">
<link rel="stylesheet" href="css/admin/term.css">
</body>
</html>