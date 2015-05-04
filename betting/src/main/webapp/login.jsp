<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<script type="text/javascript">
    function formfocus() {
        document.getElementById('userId').focus();
    }
    window.onload = formfocus;
</script>


<s:layout-render name="/layout.jsp" nadpis="Log in">
    <s:layout-component name="telo">
        <s:form class="loginForm" beanclass="cz.muni.fi.pa036.betting.web.SecurityActionBean" action="/security/submitLogin">
            <div class="bigBlueBox">
                <div class="title">
                    <img src="${pageContext.request.contextPath}/img/user-32.png" alt="login" title="login">
                    Please enter your credentials
                </div>
                <s:hidden name="userPath" value="${userPath}"></s:hidden>
                <fieldset>
                    <table>
                    <tr>
                        <th><s:label for="userId" name="userId"/></th>
                        <td><s:text id="userId" name="userId"/></td>
                    </tr>
                    <tr>
                        <th><s:label for="password" name="password"/></th>
                        <td><s:password id="password" name="password"/></td>
                    </tr>
                    <tr>
                        <th></th>
                        <td><s:submit name="submitLogin">Log in</s:submit></td>
                    </tr>
                </table>
                </fieldset>
            </div>
        </s:form>
        
        <s:errors/>
    </s:layout-component>
</s:layout-render>