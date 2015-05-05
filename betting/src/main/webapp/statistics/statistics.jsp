<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" nadpis="Your statistics">
    <s:layout-component name="telo">
        <s:useActionBean beanclass="cz.muni.fi.pa036.betting.web.StatisticsActionBean" var="stats"/>
        <table class="zakladni">
            <tr>
                <td>
                    Tickets placed
                </td>
                <td>
                    <c:out value="${stats.ticketsCount}"/>
                </td>  
                <td>                    
                </td> 
            </tr>
            <tr>
                <td>
                    Tickets won
                </td>
                <td>
                    <c:out value="${stats.ticketsWon}"/>
                </td> 
                <td>
                    <c:out value="${stats.ticketsWonPercentage}"/>%
                </td> 
            </tr>
            <tr>
                <td>
                    Tickets lost
                </td>
                <td>
                    <c:out value="${stats.ticketsLost}"/>
                </td> 
                <td>
                    <c:out value="${stats.ticketsLostPercentage}"/>%
                </td> 
            </tr>            
            <tr>
                <td>
                    Money deposit
                </td>
                <td>
                    <c:out value="${stats.moneyDeposit}"/>$
                </td>
                <td>
                    
                </td> 
            </tr>
            <tr>
                <td>
                    Money won
                </td>
                <td>
                    <c:out value="${stats.moneyWon}"/>$
                </td> 
                <td>
                    
                </td> 
            </tr>
            <tr>
                <td>
                    <b>Total Balance</b>
                </td>
                <td>
                    <c:out value="${stats.totalBalance}"/>$
                </td> 
                <td>
                    
                </td> 
            </tr>
        </table>
        </s:layout-component>
</s:layout-render>
