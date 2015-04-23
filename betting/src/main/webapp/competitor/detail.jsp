<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
 
<s:layout-render name="/layout.jsp" nadpis="Detail hráče">
    <s:layout-component name="telo">
        <s:useActionBean beanclass="cz.muni.fi.pa036.betting.web.CompetitorActionBean" var="actionBean"/>
            <table class="zakladni">
                <tr>
                    <td class="label">ID</td>
                    <td><c:out value="${actionBean.competitor.id}"/></td>
                </tr>
                <tr>
                    <td class="label">Jméno</td>
                    <td><c:out value="${actionBean.competitor.name}"/></td>
                </tr>
                <tr>
                    <td class="label">Země</td>
                    <td><c:out value="${actionBean.competitor.country.name}"/></td>
                </tr>
                <tr>
                    <td class="label">Sport</td>
                    <td><c:out value="${actionBean.competitor.sport.kindofsport}"/></td>
                </tr>
            </table>
    </s:layout-component>
</s:layout-render>