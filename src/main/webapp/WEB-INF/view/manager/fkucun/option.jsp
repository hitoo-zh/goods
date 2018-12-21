<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<select name="goodsId" style="width:260px;height:40px">
    <%--<option value="">--不限--</option>--%>
    <c:forEach var="item" items="${goodses}">
        <option value="${item.id}">${item.name}</option>
    </c:forEach>
</select>
