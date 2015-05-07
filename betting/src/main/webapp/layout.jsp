<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<s:layout-definition>
    <s:useActionBean beanclass="cz.muni.fi.pa036.betting.web.BaseActionBean" var="actionBean" />
    <html>
        <head>
            <title><c:out value="Betting - ${nadpis}" /></title>
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery.datetimepicker.css" />
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-ui.css" />
            <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
            <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
            <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.datetimepicker.js"></script>
            <script type="text/javascript">
                $(document).ready(function() {

                /*$('input.datepicker').Zebra_DatePicker({
                    format: 'd. m. Y'
                });*/
                var append = []; var i = 0; for(var xh=0;xh<=23;xh++){for(var xm=0;xm<60;xm+=15){append[i] = ('0'+xh).slice(-2)+':'+('0'+xm).slice(-2); i++;}}
                $('input.datetimepicker').datetimepicker({
                    format:'d.m.Y H:i',
                    allowTimes: append
                    //allowTimes:['09:00', '11:00', '12:00', '21:00']
                });
                $('input.datepicker').datetimepicker({
                    timepicker:false,
                    format:'d.m.Y'
                });
                $('input.timepicker').datetimepicker({
                    datepicker:false,
                    format:'H:i'
                  });
                
                $('#helpTextIcon').click(function() {
                    $('#helpTextWrapper').toggle();
                });
             });
             </script>
            
            <s:layout-component name="hlavicka"/>        
        </head>
        <body>
            <div id="topPanel">
                <img src="${pageContext.request.contextPath}/img/bet-fi-logo.png"
                     class="logo" alt="Bet@FI" title="Bet@FI" />
                <div class="userinfo">
                    <c:choose>
                        <c:when test="${loggedIn == true}">
                            <s:link href="/security/logout">
                                <img src="${pageContext.request.contextPath}/img/user32.png" 
                                     alt="log out" title="log out" />
                            </s:link>
                            <br/>
                            <c:out value="${user.login}" />
                        </c:when>
                        <c:otherwise>
                            <s:link href="/security/signUp">
                                <img src="${pageContext.request.contextPath}/img/user-32.png" 
                                     alt="registrovat" title="Sign up" />
                            </s:link>
                            <s:link href="/security/login">
                                <img src="${pageContext.request.contextPath}/img/user-logged-out32.png" 
                                     alt="log in" title="log in" />
                            </s:link>
                            <br/>
                            <c:out value="Not logged in." />
                        </c:otherwise>
                    </c:choose>
                </div>
                
                <h1><c:out value="${nadpis}" /></h1>
            </div>

            <div id="navigace">
                <ul>
                    
                    <c:choose>
                        <c:when test="${loggedIn == true}">
                            <c:choose>
                                <c:when test="${actionBean.isUserAdminOrBookmaker == true}">
                                    <c:if test="${user.isUserAdmin}">
                                        <li>
                                            <s:link beanclass="cz.muni.fi.pa036.betting.web.TicketActionBean" event="all">
                                                All tickets
                                            </s:link>
                                        </li>
                                    </c:if>
                                    
                                    <li>
                                        <s:link href="/event/">Events</s:link>
                                    </li>
                                    
                                    <li>
                                        <s:link href="/competitor/">Competitors</s:link>
                                    </li>
                                    <li>
                                        <s:link href="/league/">Leagues</s:link>
                                    </li>
                                    <li>
                                        <s:link href="/sport/">Sports</s:link>
                                    </li>
                                    <li>
                                        <s:link href="/country/">Countries</s:link>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <c:if test="${(actionBean.currentTicket != null) and (actionBean.currentTicket.ticketEventsCount > 0)}">
                                        <li>
                                            <s:link beanclass="cz.muni.fi.pa036.betting.web.TicketActionBean" event="detail">
                                                <s:param name="ticket.id" value="${actionBean.currentTicket.id}" />
                                                Current ticket (<c:out value="${actionBean.currentTicket.ticketEventsCount}"/>)
                                            </s:link>
                                            <div class="ticketMenuData">
                                            <c:forEach items="${actionBean.currentTicket.ticketEvents}" var="ticketEvent">
                                                ${ticketEvent.event.name}: ${ticketEvent.competitorName} - ${ticketEvent.betvalue}<br/>
                                            </c:forEach>
                                            ---<br/>
                                            Total: ${actionBean.currentTicket.totalTicketOdds} 
                                            </div>
                                        </li>
                                    </c:if>
                                    <li>
                                        <s:link href="/user/account">
                                            My account
                                        </s:link>
                                    </li>    
                                    <li>
                                        <s:link beanclass="cz.muni.fi.pa036.betting.web.TicketActionBean" event="all">
                                            My tickets
                                        </s:link>
                                    </li>
                                    <li>
                                        <s:link href="/event/listForUser">Overview of Events</s:link>
                                      
                                    </li>
                                    <li>
                                        <s:link href="/statistics">Statistics</s:link>
                                    </li>                                    
                                    <li>
                                        <s:link href="/userFavoriteSport">Favorite sports</s:link>                                        
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            <li>
                                <s:link href="/index">Logged out menu</s:link>
                            </li>
                        </c:otherwise>
                    </c:choose>                                   
                    
                    
                </ul>
            </div>
            <div id="obsah">
                <s:messages/>
                <s:layout-component name="telo"/>
            </div>
            <div class="clear"></div>
            <div class="footer">
                &copy; 2015 Baniar Samuel, Hrabovská Katarína, Jelínek Martin, Malík Martin
            </div>
        </body>
    </html>
</s:layout-definition>
