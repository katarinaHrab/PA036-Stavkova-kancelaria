<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" nadpis="Overview of Leagues and Events">
    <s:layout-component name="telo">
        <s:useActionBean beanclass="cz.muni.fi.pa036.betting.web.EventActionBean" var="actionBean"/>
        
            <table class="zakladni">
                <tr>
                    <th>Name</th>
                    <th>Sport</th>
                    <th>Country</th>
                    <th>Events</th>
                </tr>
                <c:forEach items="${actionBean.allLeagues}" var="league">
                    <tr>
                        <td>
                            <c:out value="${league.name}" />
                        </td>
                        <td>
                            <c:out value="${league.sport.kindofsport}"/>
                        </td>
                        <td>
                            <c:out value="${league.country.name}"/>
                        </td>

                        <td>
                            <s:link beanclass="cz.muni.fi.pa036.betting.web.EventActionBean" event="eventsByLeague">
                                    <s:param name="league.id" value="${league.id}"/>
                                    <img src="${pageContext.request.contextPath}/img/list.png" alt="events" title="events"></s:link>  
                        </td>
                    </tr>
                </c:forEach>
            </table>   
    </s:layout-component>
</s:layout-render>

