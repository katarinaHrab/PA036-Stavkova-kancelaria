<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" nadpis="Sign up">
    <s:layout-component name="telo">
        <s:form class="signUpForm" beanclass="cz.muni.fi.pa036.betting.web.SecurityActionBean" action="/security/submitSignUp">
            <div class="bigBlueBox">
                <div class="title">
                    <img src="${pageContext.request.contextPath}/img/user-32.png" alt="přihlásit" title="přihlásit">
                    Fill your personal information.
                </div>
                <s:hidden name="userPath" value="${userPath}"></s:hidden>
                <fieldset>
                    <table>
                    <tr>
                        <th><s:label for="login" name="login"/></th>
                        <td><s:text id="login" name="registrationOfUser.login"/></td>
                    </tr>
                    <tr>
                        <th><s:label for="password" name="password"/></th>
                        <td><s:password id="password" name="password"/></td>
                    </tr>
                    <tr>
                        <th><s:label for="name" name="name"/></th>
                        <td><s:text id="name" name="registrationOfUser.name"/></td>
                    </tr>
                    <tr>
                        <th><s:label for="surname" name="surname"/></th>
                        <td><s:text id="surname" name="registrationOfUser.surname"/></td>
                    </tr>
                    <tr>
                        <th><s:label for="dateofbirth" name="date of birth"/></th>
                        <td><s:text id="dateofbirth" name="registrationOfUser.dateofbirth"/></td>
                    </tr>
                    <tr>
                        <th><s:label for="email" name="email"/></th>
                        <td><s:text id="email" name="email"/></td>
                    </tr>
                    <tr>
                        <th></th>
                        <td><s:submit name="submitSignUp" onclick="return confirm('By clicking OK, I confirm that I am adult and accept all legal terms.');">Sign up</s:submit></td>
                    </tr>
                </table>
                </fieldset>
            </div>
        </s:form>
        
        <s:errors/>
    </s:layout-component>
</s:layout-render>