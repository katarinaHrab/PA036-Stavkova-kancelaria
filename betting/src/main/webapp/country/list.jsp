<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" nadpis="Countries">
    <s:layout-component name="telo">
        <s:useActionBean beanclass="cz.muni.fi.pa036.betting.web.CountryActionBean" var="actionBean"/>
        
            <s:form beanclass="cz.muni.fi.pa036.betting.web.CountryActionBean" action="/country/add">
                <s:submit name="add" value="New country"/>
            </s:form>
        
            <table class="zakladni">
                <tr>
                    <th>ID</th>
                    <th>Country</th>
                    <th>Action</th>
                </tr>
                <c:forEach items="${actionBean.allCountries}" var="country">
                    <tr>
                        <td>
                            <c:out value="${country.id}"/>
                        </td>
                        <td>
                            <c:out value="${country.name}"/>
                        </td>

                        <td>
                            <c:if test="${actionBean.isUserAdminOrBookmaker}">
                                <s:link beanclass="cz.muni.fi.pa036.betting.web.CountryActionBean" event="edit">
                                    <s:param name="country.id" value="${country.id}"/>
                                    <img src="${pageContext.request.contextPath}/img/pencil.png" alt="edit country" title="edit country"></s:link>
                                <s:link beanclass="cz.muni.fi.pa036.betting.web.CountryActionBean" event="delete" onclick="return confirm('Really delete?')">
                                    <s:param name="country.id" value="${country.id}"/>
                                    <img src="${pageContext.request.contextPath}/img/cross-script.png" alt="delete country" title="delete country"></s:link>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>   
        
            <s:form beanclass="cz.muni.fi.pa036.betting.web.CountryActionBean" action="/Country/add">
                <p style="text-align: right">
                <s:submit name="add" value="New country"/>
                </p>
            </s:form>
                
        </div>

    </s:layout-component>
</s:layout-render>
