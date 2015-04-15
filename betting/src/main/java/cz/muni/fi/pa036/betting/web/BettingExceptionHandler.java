package cz.muni.fi.pa036.betting.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sourceforge.stripes.config.Configuration;
import net.sourceforge.stripes.exception.ExceptionHandler;

public class BettingExceptionHandler implements ExceptionHandler {
    @Override
    public void init(Configuration configuration) throws Exception { }

    @Override
    public void handle(Throwable throwable,
                       HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {


        request.setAttribute("exception", throwable);
        
        StringWriter errors = new StringWriter();
        throwable.printStackTrace(new PrintWriter(errors));
        request.setAttribute("stackTrace", errors.toString());
        
        request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
}