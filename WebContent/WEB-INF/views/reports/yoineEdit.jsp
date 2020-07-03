<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
<c:param name="content">
<h2>${yoine.id}のいいね</h2>


<form method="POST" action="<c:url value='/reports/yoine/destroy' />">
<button type="submit" name="count"><i class="far fa-heart fa-sm"></i></button>
    <input type="hidden" name="_token" value="${_token}" />
</form>
<%-- <script>
function confirmDestroy() {
    if(confirm("いいねを削除しますか？")) {
        document.forms[1].submit();
    }
}
</script>
--%>
</c:param>
</c:import>