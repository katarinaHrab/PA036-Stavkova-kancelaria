<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" nadpis="Sports">
    <s:layout-component name="telo">
        <s:useActionBean beanclass="cz.muni.fi.pa036.betting.web.SportActionBean" var="actionBean"/>
        
            <s:form beanclass="cz.muni.fi.pa036.betting.web.SportActionBean" action="/sport/add">
                <s:submit name="add" value="New sport"/>
            </s:form>
        
            <table class="zakladni">
                <tr>
                    <th>ID</th>
                    <th>Sport</th>
                    <th>Action</th>
                </tr>
                <c:forEach items="${actionBean.allSports}" var="sport">
                    <tr>
                        <td>
                            <c:out value="${sport.id}"/>
                        </td>
                        <td>
                            <c:out value="${sport.kindofsport}"/>
                        </td>

                        <td>
                            <c:if test="${actionBean.isUserAdminOrBookmaker}">
                                <s:link beanclass="cz.muni.fi.pa036.betting.web.SportActionBean" event="edit">
                                    <s:param name="sport.id" value="${sport.id}"/>
                                    <img src="${pageContext.request.contextPath}/img/pencil.png" alt="edit sport" title="edit sport"></s:link>
                                <s:link beanclass="cz.muni.fi.pa036.betting.web.SportActionBean" event="delete" onclick="return confirm('Really delete?')">
                                    <s:param name="sport.id" value="${sport.id}"/>
                                    <img src="${pageContext.request.contextPath}/img/cross-script.png" alt="delete sport" title="delete sport"></s:link>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>           
            
            <s:form beanclass="cz.muni.fi.pa036.betting.web.SportActionBean" action="/sport/add">
                <p style="text-align: right">
                <s:submit name="add" value="New sport"/>
                </p>
            </s:form>

    </s:layout-component>
</s:layout-render>
