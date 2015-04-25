<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:useActionBean beanclass="cz.muni.fi.pa036.betting.web.CountryActionBean" var="countryActionBean"/>
<s:useActionBean beanclass="cz.muni.fi.pa036.betting.web.SportActionBean" var="sportActionBean"/>

<tr>
    <th><s:label for="name" name="league.name"/></th>
    <td><s:text id="name" name="league.name"/></td>
</tr>
<tr>
    <th><s:label for="countryId" name="league.country.id"/></th>
    <td>
        <s:select id="countryId" name="league.country.id">
            <s:options-collection collection="${countryActionBean.allCountries}" value="id" label="name" />
        </s:select>
    </td>
</tr>
<tr>
    <th><s:label for="sportId" name="league.sport.id"/></th>
    <td>
        <s:select id="sportId" name="league.sport.id">
            <s:options-collection collection="${sportActionBean.allSports}" value="id" label="kindofsport" />
        </s:select>
    </td>
</tr>