<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
 
<s:layout-render name="/layout.jsp" nadpis="Detail of the event">
    <s:layout-component name="telo">
        <s:useActionBean beanclass="cz.muni.fi.pa036.betting.web.EventActionBean" var="actionBean"/>
            <table class="zakladni">
                <tr>
                    <td class="label">Id</td>
                    <td><c:out value="${actionBean.event.id}"/></td>
                </tr>
                <tr>
                    <td class="label">League</td>
                    <td><c:out value="${actionBean.event.league.name}"/></td>
                </tr>
                <tr>
                    <td class="label">Place</td>
                    <td><c:out value="${actionBean.event.place}"/></td>
                </tr>
                <tr>
                    <td class="label">Name</td>
                    <td><c:out value="${actionBean.event.name}"/></td>
                </tr>
                <tr>
                    <td class="label">Date</td>
                    <td><s:format value="${actionBean.event.date}" formatType="dateTimeCzech" /></td>
                </tr>
                <tr>
                    <td class="label">Drawodds</td>
                    <td><c:out value="${actionBean.event.drawodds}"/></td>
                </tr>
                
            </table>
    </s:layout-component>
</s:layout-render>