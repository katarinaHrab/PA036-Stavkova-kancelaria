<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:useActionBean beanclass="cz.muni.fi.pa036.betting.web.LeagueActionBean" var="leagueActionBean"/>

<tr>
    <th><s:label for="leagueId" name="event.league.id"/></th>
    <td>
        <s:select id="leagueId" name="event.league.id">
            <s:options-collection collection="${leagueActionBean.allLeagues}" value="id" label="name" />
        </s:select>
    </td>
</tr>

<tr>
    <th><s:label for="place" name="event.place"/></th>
    <td><s:text id="place" name="event.place"/></td>
</tr>    

<tr>
    <th><s:label for="name" name="event.name"/></th>
    <td><s:text id="name" name="event.name"/></td>
</tr>

<tr>
    <th><s:label for="date" name="event.date"/></th>
    <td><s:text id="date" name="event.date"/></td>
</tr>

<tr>
    <th><s:label for="drawodds" name="event.drawodds"/></th>
    <td><s:text id="drawodds" name="event.drawodds"/></td>
</tr>
