package cz.muni.fi.pa036.betting.web;

import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.ExecutionContext;
import net.sourceforge.stripes.controller.Interceptor;
import net.sourceforge.stripes.controller.Intercepts;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author Martin Jelínek
 */
@Intercepts(LifecycleStage.HandlerResolution)
public class SecurityInterceptor implements Interceptor {

    @Override
    public Resolution intercept(ExecutionContext ctx) throws Exception {
        Resolution resolution = ctx.proceed();
        //Class currentBean = ctx.getActionBean().getClass();
        if (ctx.getActionBean().getClass().isAnnotationPresent(Annotations.DoesNotRequireLogin.class)) {
            return resolution;
        }
return null;
        /*if (ctx.getActionBean().getClass().equals(UsersActionBean.class)
                && (ctx.getActionBeanContext().getEventName().equals("emailLogin")
                || ctx.getActionBeanContext().getEventName().equals("emailLoginAction"))) {
            return resolution;
        }

        if (isLoggedIn(ctx.getActionBeanContext())) {
            return resolution;
        } else {
            String path = ctx.getActionBean().getContext().getRequest().getRequestURI();
            String contextPath = ctx.getActionBean().getContext().getServletContext().getContextPath();
            if (!"".equals(contextPath) && path.startsWith(contextPath)) {
                path = path.substring(contextPath.length());
            }
            if (ctx.getActionBean().getContext().getRequest().getQueryString() != null) {
                path += "?" + ctx.getActionBean().getContext().getRequest().getQueryString();
            }
            if (!(ctx.getActionBean().getContext().getRequest().getRequestURI().equals(""))) {
                return new RedirectResolution(SecurityActionBean.class, "login").
                        addParameter("userPath", path);
            } else {
                return new RedirectResolution(SecurityActionBean.class, "login");
            }
        }*/
    }

    private boolean isLoggedIn(ActionBeanContext ctx) {
        Boolean loggedIn = (Boolean) ctx.getRequest().getSession().getAttribute("loggedIn");

        return (loggedIn != null && loggedIn);

    }
}
