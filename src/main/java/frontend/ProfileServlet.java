package frontend;


import base.AccountService;
import main.AccountServiceImpl;
import main.ContextService;
import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Виталий on 06.03.2015.
 */
public class ProfileServlet extends HttpServlet {
    private AccountService accountService = new AccountServiceImpl();
    private ContextService contextService;

    public ProfileServlet(ContextService contextService) {
        this.contextService = contextService;
        accountService = (AccountService) contextService.get(accountService.getClass());
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_OK);


        Map<String, Object> pageVariables = new HashMap<>();
        HttpSession session = request.getSession();


        if (!accountService.isAuthorised(session.getId())) {
            response.sendRedirect("/api/v1/auth/signin");
        }
        String name = "", password = "", email = "";
        if (accountService.isExist(session.getId())) {
            name = accountService.getSessions(session.getId()).getLogin();
            password = accountService.getSessions(session.getId()).getPassword();
            email = accountService.getSessions(session.getId()).getEmail();
        }
        pageVariables.put("login", name);
        pageVariables.put("password", password);
        pageVariables.put("email", email);

        response.getWriter().println(PageGenerator.getPage("profile.html", pageVariables));

    }


    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        response.setStatus(HttpServletResponse.SC_OK);

        Map<String, Object> pageVariables = new HashMap<>();
        response.getWriter().println(PageGenerator.getPage("profile.html", pageVariables));
    }
}