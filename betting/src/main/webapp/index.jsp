<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
 
<s:layout-render name="/layout.jsp" nadpis="Index">
    <s:useActionBean beanclass="cz.muni.fi.pa036.betting.web.IndexActionBean" var="actionBean" />
    <s:layout-component name="telo">
        <c:choose>
            <c:when test="${empty(loggedIn) or loggedIn == false}">
                <h2>Welcome at betting portal index page</h2>
            </c:when>
            <c:otherwise>
                <h2>Welcome at betting portal index page</h2>
                You are logged in to the system.
            </c:otherwise>
        </c:choose>
    </s:layout-component>
</s:layout-render>