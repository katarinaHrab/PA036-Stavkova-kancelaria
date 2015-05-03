<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" nadpis="${actionBean.league.name}">
    <s:layout-component name="telo">
        <s:useActionBean beanclass="cz.muni.fi.pa036.betting.web.EventActionBean" var="actionBean"/>
        <table class="zakladni">
                <tr>
                    <th>Name</th>
                    <th>Place</th>
                    <th>Date</th>
                    <th>DrawOdds</th>
                </tr>
                <c:forEach items="${actionBean.allEventsByLeague}" var="event">
                    <tr>
                        <td>
                            <c:out value="${event.name}" />
                        </td>
                        <td>
                            <c:out value="${event.place}"/>
                        </td>
                       <td>
                            <c:out value="${event.date.toLocaleString()}"/>
                        </td>

                       <td>
                            <c:out value="${event.drawodds}"/>
                        </td>
                    </tr>
                </c:forEach>
            </table>   
    </s:layout-component>
</s:layout-render>

