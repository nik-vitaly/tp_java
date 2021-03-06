package frontend;

import base.AccountService;
import main.AccountServiceImpl;
import main.ContextService;
import org.json.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Виталий on 31.03.2015.
 */
public class ScoresServlet extends HttpServlet {
    private AccountService accountService = new AccountServiceImpl();
    private ContextService contextService;

    public ScoresServlet(ContextService contextService) {
        this.contextService = contextService;
        accountService = (AccountService) contextService.get(accountService.getClass());
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        JSONObject jsonResponse = new JSONObject();
        Map<String, Object> responseMap =  new HashMap<>();
        Map<String, Object> sortMap =  new HashMap<>();

        sortMap.put("by", request.getParameter("by"));
        sortMap.put("order", request.getParameter("order"));

        jsonResponse.put("status", 200);
        jsonResponse.put("body", responseMap);

        response.getWriter().println(jsonResponse);
    }

}