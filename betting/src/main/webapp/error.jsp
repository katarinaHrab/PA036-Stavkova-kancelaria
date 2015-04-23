<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
 
<s:layout-render name="/layout.jsp" nadpis="Chyba">
    <s:layout-component name="telo">
        <s:errors/>
        <c:if test="${exception != null}">
            <h2>Je nám líto, ale došlo k výjimce :(</h2>
            Můžete se zkusit vrátit <a href="javascript:history.back()">zpět</a>.
            <br/><br/><br/>
            <h3>Informace o chybě:</h3>
            <h4>Typ: <c:out value="${exception['class'].name}"></c:out></h4>
            <h4>Zpráva: <c:out value="${exception.message}"></c:out></h4>
            <br/><br/>
            <strong>Stacktrace:</strong>
            <pre><c:out value="${stackTrace}"></c:out></pre>
        </c:if>
    </s:layout-component>
</s:layout-render>
