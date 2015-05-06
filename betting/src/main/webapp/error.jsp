<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
 
<s:layout-render name="/layout.jsp" nadpis="Error">
    <s:layout-component name="telo">
        <s:errors/>
        <c:if test="${exception != null}">
            <h2>We are sorry, but there was an exception:(</h2>
            You can try to go <a href="javascript:history.back()">back</a>.
            <br/><br/><br/>
            <h3>Error information:</h3>
            <h4>Type: <c:out value="${exception['class'].name}"></c:out></h4>
            <h4>Message: <c:out value="${exception.message}"></c:out></h4>
            <br/><br/>
            <strong>Stacktrace:</strong>
            <pre><c:out value="${stackTrace}"></c:out></pre>
        </c:if>
    </s:layout-component>
</s:layout-render>
