<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<select name="classId" style="width:260px;height:40px">
    <option value="">--不限--</option>
    <c:forEach var="item" items="${classes}">
        <option value="${item.id}">${item.className}</option>
    </c:forEach>
</select>