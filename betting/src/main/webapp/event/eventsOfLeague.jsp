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
                   <th>Bet</th> 
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
                           <table style="width:100%">
                               <tr>
                                    <th>Name</th>
                                    <th>Odds</th>
                                    <th></th>
                                </tr>
                                <c:if test="${event.drawodds>1}">
                                <tr style="background-color: #66CCFF">
                                    <td>Draw</td>
                                    <td>
                                        <c:out value="${event.drawodds}"/>
                                    </td>
                                    <td>
                                       <s:form beanclass="cz.muni.fi.pa036.betting.web.LeagueActionBean" action="/league/add">
                                            <s:submit name="bet" value="Bet"/>
                                        </s:form> 
                                    </td>
                                </tr>
                                </c:if>
                                <c:forEach items="${event.eventCompetitors}" var="eventCompetitor">
                                    <tr style="background-color:#fff">
                                        <td>
                                            <c:out value="${eventCompetitor.competitor.name}" />
                                        </td>
                                        <td>
                                            <c:out value="${eventCompetitor.odds}"/>
                                        </td>
                                        <td>
                                       <s:form beanclass="cz.muni.fi.pa036.betting.web.LeagueActionBean" action="/league/add">
                                            <s:submit name="bet" value="Bet"/>
                                        </s:form> 
                                    </td>
                                    </tr>
                                </c:forEach>
                           </table>
                        </td>
                    </tr>
                </c:forEach>
            </table>   
    </s:layout-component>
</s:layout-render>

