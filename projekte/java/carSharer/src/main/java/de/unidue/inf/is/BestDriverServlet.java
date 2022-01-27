package de.unidue.inf.is;

import de.unidue.inf.is.domain.Fahrt;
import de.unidue.inf.is.domain.benutzer;
import de.unidue.inf.is.stores.BestDriverStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BestDriverServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public  static de.unidue.inf.is.domain.schreiben schreiben;

    @Override
    public void doGet (HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        /******* get data from store *********/
        List<benutzer> benutzers=BestDriverStore.getInstance().getBenutzers();
        List<Fahrt> fahrts = BestDriverStore.getInstance().BestDriverData();

        /*** send data to viewwe ****/
        request.setAttribute("benutzers",benutzers);
        request.setAttribute("fahrts",fahrts);

        request.getRequestDispatcher("BestDriver.ftl").forward(request,response);
    }


}
