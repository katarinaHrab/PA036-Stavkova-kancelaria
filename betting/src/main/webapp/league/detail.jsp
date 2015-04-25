<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
 
<s:layout-render name="/layout.jsp" nadpis="Detail of league">
    <s:layout-component name="telo">
        <s:useActionBean beanclass="cz.muni.fi.pa036.betting.web.LeagueActionBean" var="actionBean"/>
            <table class="zakladni">
                <tr>
                    <td class="label">ID</td>
                    <td><c:out value="${actionBean.league.id}"/></td>
                </tr>
                <tr>
                    <td class="label">Name</td>
                    <td><c:out value="${actionBean.league.name}"/></td>
                </tr>
                <tr>
                    <td class="label">Country</td>
                    <td><c:out value="${actionBean.league.country.name}"/></td>
                </tr>
                <tr>
                    <td class="label">Sport</td>
                    <td><c:out value="${actionBean.league.sport.kindofsport}"/></td>
                </tr>
            </table>
    </s:layout-component>
</s:layout-render>
