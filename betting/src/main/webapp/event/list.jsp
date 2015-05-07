<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" nadpis="All Events">
    <s:layout-component name="telo">
        <s:useActionBean beanclass="cz.muni.fi.pa036.betting.web.EventActionBean" var="actionBean"/>
        
            <s:form beanclass="cz.muni.fi.pa036.betting.web.EventActionBean" action="/event/add">
                <s:submit name="add" value="New event"/>
            </s:form>
        
            <table class="zakladni">
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>League</th>
                    <th>Place</th>
                    <th>Date</th>
                    <th>Drawodds</th>
                    <th>Action</th>
                </tr>
                <c:forEach items="${actionBean.allEvents}" var="event">
                    <tr>
                        <td>
                            <c:out value="${event.id}"/>
                        </td>
                        <td>
                            <s:link beanclass="cz.muni.fi.pa036.betting.web.EventActionBean" event="detail">
                                <s:param name="event.id" value="${event.id}"/>
                                <c:out value="${event.name}"/>
                            </s:link>
                        </td>
                        <td>
                            <c:out value="${event.league.name}" />
                        </td>
                        <td>
                            <c:out value="${event.place}"/>
                        </td>
                        <td>
                            <s:format value="${event.date}" formatType="dateTimeCzech" />
                        </td>
                        <td>
                            <c:out value="${event.drawodds}"/>
                        </td>
                        
                        
                        <td>
                            <c:if test="${actionBean.isUserAdminOrBookmaker}">
                                <s:link beanclass="cz.muni.fi.pa036.betting.web.EventActionBean" event="edit">
                                    <s:param name="event.id" value="${event.id}"/>
                                    <img src="${pageContext.request.contextPath}/img/pencil.png" alt="edit event" title="edit the event"></s:link>
                                <s:link beanclass="cz.muni.fi.pa036.betting.web.EventActionBean" event="editCompetitors">
                                    <s:param name="event.id" value="${event.id}"/>
                                    <img src="${pageContext.request.contextPath}/img/plus.png" alt="edit competitors" title="edit copmetitors"></s:link>
                                <s:link beanclass="cz.muni.fi.pa036.betting.web.EventActionBean" event="delete" onclick="return confirm('Are you sure you want to delete this?')">
                                    <s:param name="event.id" value="${event.id}"/>
                                    <img src="${pageContext.request.contextPath}/img/cross-script.png" alt="delete message" title="delete the event"></s:link>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>           
        
            <s:form beanclass="cz.muni.fi.pa036.betting.web.EventActionBean" action="/event/add">
                <p style="text-align: right">
                <s:submit name="add" value="New event"/>
                </p>
            </s:form>
                
        <c:forEach var="i" begin="1" end="${actionBean.pagesCount}">
            <s:link beanclass="cz.muni.fi.pa036.betting.web.EventActionBean" event="${actionBean.context.eventName}">
                <s:param name="page" value="${i}" />
                ${i}
            </s:link>
        </c:forEach>

    </s:layout-component>
</s:layout-render>