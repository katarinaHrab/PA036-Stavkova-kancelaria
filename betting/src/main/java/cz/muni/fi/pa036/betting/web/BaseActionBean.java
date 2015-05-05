package cz.muni.fi.pa036.betting.web;

import cz.muni.fi.pa036.betting.model.Ticket;
import cz.muni.fi.pa036.betting.model.User;
import javax.servlet.http.HttpServletRequest;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;

public class BaseActionBean implements ActionBean {

    private ActionBeanContext context;

    @Override
    public void setContext(ActionBeanContext context) {
        this.context = context;
    }

    @Override
    public ActionBeanContext getContext() {
        return context;
    }

    private int page;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    private int limit = 100;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     * Returns logged User from Session
     *
     * @return User object or null
     */
    public User getLoggedUser() {
        return ((User) this.getContext().getRequest().getSession().getAttribute("user"));
    }

    /**
     * Helper function - returns GET or POST parameter Short version of
     * getContext().getRequest().getParameter(name);
     *
     * @param name Name of the parameter
     * @return String value of request parameter
     */
    public String getRequestParam(String name) {
        return getContext().getRequest().getParameter(name);
    }
    
    public Object getSessionParam(String name) {
        return getContext().getRequest().getSession().getAttribute(name);
    }
    
    public void setSessionParam(String name, Object value) {
        getContext().getRequest().getSession().setAttribute(name, value);
    }
    
    public void removeSessionParam(String name) {
        getContext().getRequest().getSession().removeAttribute(name);
    }
    
    public Ticket getCurrentTicket() {
        return (Ticket) getSessionParam(TicketActionBean.SESSION_TICKET);
    }

    public boolean getIsUserAdmin() {
        return (getLoggedUser() != null)
                && (getLoggedUser().getIsUserAdmin());
    }

    public String getURLWithoutContextPath() {
        HttpServletRequest request = this.getContext().getRequest();
        return request.getScheme() + "://" + request.getServerName() + ":"
                + request.getServerPort();
    }

    public String getURLWithContextPath() {
        HttpServletRequest request = this.getContext().getRequest();
        return request.getScheme() + "://" + request.getServerName() + ":"
                + request.getServerPort() + request.getContextPath();
    }

    public String getCurrentPath() {
        String path = context.getRequest().getRequestURI();
        if (context.getRequest().getAttribute("javax.servlet.forward.request_uri") != null
                && !((String) context.getRequest().getAttribute("javax.servlet.forward.request_uri")).isEmpty()) {
            path = (String) context.getRequest().getAttribute("javax.servlet.forward.request_uri");
        }
        String contextPath = context.getServletContext().getContextPath();
        if (!"".equals(contextPath) && path.startsWith(contextPath)) {
            path = path.substring(contextPath.length());
        }
        if (context.getRequest().getQueryString() != null) {
            path += "?" + context.getRequest().getQueryString();
        }
        if (!(context.getRequest().getRequestURI().equals(""))) {
            return path;
        } else {
            return "";
        }
    }
}
