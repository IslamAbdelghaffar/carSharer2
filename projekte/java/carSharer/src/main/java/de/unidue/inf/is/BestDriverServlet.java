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
    int user=5;
    @Override
    public void doGet (HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        /******* get data from store *********/
        benutzer benutzers=BestDriverStore.getInstance().getBestDriverEmail();
        List<Fahrt> fahrts = BestDriverStore.getInstance().BestDriverData();
        float average= BestDriverStore.getInstance().getAverage();

        /*** send data to viewwe ****/

        request.setAttribute("benutzers",benutzers);
        request.setAttribute("fahrts",fahrts);
        request.setAttribute("average",average);
        request.setAttribute("user",user);

        request.getRequestDispatcher("BestDriver.ftl").forward(request,response);
        BestDriverStore.getInstance().complete();
    }


}
