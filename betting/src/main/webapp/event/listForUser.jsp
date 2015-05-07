<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" nadpis="Overview of Events">
    <s:layout-component name="telo">
        <s:useActionBean beanclass="cz.muni.fi.pa036.betting.web.EventActionBean" var="eventActionBean"/>
        <s:useActionBean beanclass="cz.muni.fi.pa036.betting.web.SportActionBean" var="sportActionBean"/>
        <s:useActionBean beanclass="cz.muni.fi.pa036.betting.web.CountryActionBean" var="countryActionBean"/>
        <s:useActionBean beanclass="cz.muni.fi.pa036.betting.web.LeagueActionBean" var="leagueActionBean"/>
        
        <s:form beanclass="cz.muni.fi.pa036.betting.web.EventActionBean" action="/event/setFilterEvents">
            <fieldset>
                <table>
                    <tr>
                        <th><s:label for="sport" name="sport"/></th>
                        <td>
                            <s:select id="sportId" name="sportId">
                                <s:option value="" label="" />
                                <s:options-collection collection="${sportActionBean.allSports}" value="id" label="kindofsport" />
                            </s:select>
                        </td>
                    </tr>
                    <tr>
                        <th><s:label for="league" name="league"/></th>
                        <td>
                            <s:select id="leagueId" name="leagueId">
                                <s:option value="" label="" />
                                <s:options-collection collection="${leagueActionBean.allLeagues}" value="id" label="nameWithSport" />
                            </s:select>
                        </td>
                    </tr>
                    <tr>
                        <th></th>
                        <td><s:submit name="setFilterEvents">Filter events</s:submit></td>
                    </tr>
                </table>
            </fieldset>
        </s:form>
        
        <table class="zakladni" >
                <tr>
                    <th>Sport</th>
                    <th>League</th>
                    <th>Name</th>
                    <th>Place</th>
                    <th>Date</th>
                   <th>Bet</th> 
                </tr>
                <c:forEach items="${eventActionBean.filterEvents}" var="event">
                    <tr>
                        <td>
                            <c:out value="${event.league.sport.kindofsport}" />
                        </td>
                        <td>
                            <c:out value="${event.league.name}" />
                        </td>
                      
                        <td>
                            <c:out value="${event.name}" />
                        </td>
                        <td>
                            <c:out value="${event.place}"/>
                        </td>
                       <td>
                           <s:format value="${event.date}" formatType="dateTimeCzech" />
                        </td>

                        <td>
                           <table style="width:100%">
                               <tr>
                                    <th>Name</th>
                                    <th>Odds</th>
                                    <th></th>
                                </tr>
                                <c:if test="${event.drawodds > 1}">
                                <tr style="background-color: #66CCFF">
                                    <td>Draw</td>
                                    <td>
                                        <c:out value="${event.drawodds}"/>
                                    </td>
                                    <td>
                                        <s:form beanclass="cz.muni.fi.pa036.betting.web.TicketActionBean" action="/ticket/addToTicket">
                                            <s:hidden name="event.id" value="${event.id}" />
                                            <s:hidden name="path" value="${actionBean.currentPath}" />
                                            <s:submit name="bet" value="Bet"/>
                                        </s:form>
                                    </td>
                                </tr>
                                </c:if>
                                <c:forEach items="${event.eventCompetitors}" var="eventCompetitor">
                                    <tr style="background-color:#eee">
                                        <td>
                                            <c:out value="${eventCompetitor.competitor.name}" />
                                        </td>
                                        <td>
                                            <c:out value="${eventCompetitor.odds}"/>
                                        </td>
                                        <td>
                                        <s:form beanclass="cz.muni.fi.pa036.betting.web.TicketActionBean" action="/ticket/addToTicket">
                                            <s:hidden name="event.id" value="${event.id}" />
                                            <s:hidden name="path" value="${actionBean.currentPath}" />
                                            <s:hidden name="competitorId" value="${eventCompetitor.competitor.id}" />
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
        
        <c:forEach var="i" begin="1" end="${actionBean.pagesCount}">
            <s:link beanclass="cz.muni.fi.pa036.betting.web.EventActionBean" event="${actionBean.context.eventName}">
                <s:param name="page" value="${i}" />
                ${i}
            </s:link>
        </c:forEach>
    </s:layout-component>
</s:layout-render>
