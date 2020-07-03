<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>YoineDelete</title>
        <link rel="stylesheet" href="<c:url value='/css/reset.css' />">
        <link rel="stylesheet" href="<c:url value='/css/style.css' />">
        <script src="https://kit.fontawesome.com/b2f30f98dd.js" crossorigin="anonymous"></script>
    </head>
    <body>
    <form method="POST" action="<c:url value='/reports/yoine/destroy' />">
        <button class="d" type="submit" name="delete"><i class="far fa-thumbs-up delete"></i></button>
            <input type="hidden" name="_token" value="${_token}" />
    </form>
    </body>
</html>