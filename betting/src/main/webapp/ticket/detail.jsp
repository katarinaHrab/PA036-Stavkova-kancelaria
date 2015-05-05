<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
 
<s:layout-render name="/layout.jsp" nadpis="Detail of ticket">
    <s:layout-component name="telo">
        <s:useActionBean beanclass="cz.muni.fi.pa036.betting.web.TicketActionBean" var="actionBean"/>
            <table class="zakladni">
                <tr>
                    <td class="label">ID</td>
                    <td><c:out value="${actionBean.ticket.id}"/></td>
                </tr>
                <tr>
                    <td class="label">Status</td>
                    <td><c:out value="${actionBean.ticket.status.name}"/></td>
                </tr>
                <c:if test="${actionBean.ticket.totalWonMoney > 0}">
                    <tr>
                        <td class="label">Deposit</td>
                        <td>
                            <c:out value="${actionBean.ticket.deposit}" />
                        </td>
                    </tr>
                    <tr>
                        <td class="label">Won money</td>
                        <td>
                            <c:out value="${actionBean.ticket.totalWonMoney}" />
                        </td>
                    </tr>
                </c:if>
                <tr>
                    <td class="label">Events</td>
                    <td>
                        <table>
                            <tr>
                                <th>Event</th>
                                <th>Your tip</th>
                                <th>Bet value</th>
                                <th>Action</th>
                            </tr>
                            <c:forEach items="${actionBean.ticket.ticketEvents}" var="ticketEvent">
                                <tr>
                                    <td>${ticketEvent.event.name}</td>
                                    <td>${ticketEvent.competitorName}</td>
                                    <td>${ticketEvent.betvalue}</td>
                                    <td>
                                        <c:if test="${actionBean.ticket.status.id == 1}">
                                            <s:link beanclass="cz.muni.fi.pa036.betting.web.TicketActionBean" event="removeFromTicket">
                                                <s:param name="event.id" value="${ticketEvent.event.id}" />
                                                <s:param name="path" value="${actionBean.currentPath}" />
                                                <img src="${pageContext.request.contextPath}/img/cross-script.png" 
                                                     alt="remove from ticket" title="remove from ticket">
                                            </s:link>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td>Total:</td>
                                <td></td>
                                <td>${actionBean.ticket.totalTicketOdds} </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <c:if test="${actionBean.ticket.status.id == 1}">
                    <s:form beanclass="cz.muni.fi.pa036.betting.web.TicketActionBean" action="/ticket/closeTicket">
                        <tr>
                            <td class="label">Deposit</td>
                            <td><s:text id="deposit" name="ticket.deposit" /></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <s:submit name="closeTicket" value="closeTicket" title="Close ticket" />
                            </td>
                        </tr>
                    </s:form>
                </c:if>
            </table>
    </s:layout-component>
</s:layout-render>
