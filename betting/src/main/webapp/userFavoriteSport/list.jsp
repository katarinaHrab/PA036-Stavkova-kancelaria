<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" nadpis="Favorite Sports">
    <s:layout-component name="telo">
        <s:useActionBean beanclass="cz.muni.fi.pa036.betting.web.UserFavoriteSportActionBean" var="actionBean"/>
        <s:useActionBean beanclass="cz.muni.fi.pa036.betting.web.SportActionBean" var="sportActionBean"/>
        <s:form beanclass="cz.muni.fi.pa036.betting.web.UserFavoriteSportActionBean" action="/userFavoriteSport/save"> 
            
        <table class="zakladni">
                <tr>
                    <th>Priority</th>
                    <th>Sport</th>
                </tr>
                <tr>
                    <td>1.</td>
                    <td>
                        <s:select id="favoriteSport1" name="favoriteSport1">
                            <s:option value="" label="Empty"></s:option>
                        <s:options-collection collection="${sportActionBean.allSports}" value="id" label="kindofsport" />
                        </s:select>
                    </td>
                </tr>
                <tr>
                    <td>2.</td>
                    <td>
                        <s:select id="favoriteSport2" name="favoriteSport2">
                            <s:option value="" label="Empty"></s:option>
                        <s:options-collection collection="${sportActionBean.allSports}" value="id" label="kindofsport" />
                        </s:select>                        
                    </td>
                </tr>
                <tr>
                    <td>3.</td>
                    <td>
                        <s:select id="favoriteSport3" name="favoriteSport3">
                            <s:option value="" label="Empty"></s:option>
                        <s:options-collection collection="${sportActionBean.allSports}" value="id" label="kindofsport" />
                        </s:select>
                    </td>
                </tr>
            </table>  
        <s:submit name="save">Save changes</s:submit>
        </s:form>
    </s:layout-component>
</s:layout-render>