<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" nadpis="Tickets">
    <s:layout-component name="telo">
        <s:useActionBean beanclass="cz.muni.fi.pa036.betting.web.TicketActionBean" var="actionBean"/>
        
            <table class="zakladni">
                <tr>
                    <th>ID</th>
                    <th>Status</th>
                    <th>Events count</th>
                    <th>Total odds</th>
                    <th>Won money</th>
                    <th>Action</th>
                </tr>
                <c:forEach items="${actionBean.allTickets}" var="ticket">
                    <tr>
                        <td>
                            <c:out value="${ticket.id}"/>
                        </td>
                        <td>
                            <c:out value="${ticket.status.name}"/>
                        </td>
                        <td>
                            <c:out value="${ticket.ticketEventsCount}"/>
                        </td>
                        <td>
                            <c:out value="${ticket.totalTicketOdds}"/>
                        </td>
                        <td>
                            <c:if test="${ticket.totalWonMoney > 0}">
                                <c:out value="${ticket.totalWonMoney}" />
                            </c:if>
                        </td>
                        <td>
                            <s:link beanclass="cz.muni.fi.pa036.betting.web.TicketActionBean" event="detail">
                                <s:param name="ticket.id" value="${ticket.id}" />
                                Show
                            </s:link>
                            <c:if test="${actionBean.isUserAdmin and ticket.status.id == 2}">
                                <s:link beanclass="cz.muni.fi.pa036.betting.web.TicketActionBean" event="setTicketWinning" onclick="return confirm('Really set as winning?')">
                                    <s:param name="ticket.id" value="${ticket.id}"/>
                                    <img src="${pageContext.request.contextPath}/img/check.png" alt="set winning" title="set winning"></s:link>
                                <s:link beanclass="cz.muni.fi.pa036.betting.web.TicketActionBean" event="setTicketLosing" onclick="return confirm('Really set as losing?')">
                                    <s:param name="ticket.id" value="${ticket.id}"/>
                                    <img src="${pageContext.request.contextPath}/img/slash.png" alt="set losing" title="set losing"></s:link>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>           
            
    </s:layout-component>
</s:layout-render>
