//Controller
package de.unidue.inf.is;

import de.unidue.inf.is.domain.Fahrt;
import de.unidue.inf.is.stores.HauptSeiteStore;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public final class HauptSeiteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // give a user
        int  user=5;


        try (HauptSeiteStore hauptSeiteStore=new HauptSeiteStore()){

            // get response from controller and save it in list
            List<Fahrt>MeineReservfahrte= hauptSeiteStore.getMeineReservFahrten(user);
            List<Fahrt>offeneFahrten = hauptSeiteStore.getOffeneFahrten();



            /******** send meine reservierte fahrten to viewer *******/
            request.setAttribute("benutzer",user);
            request.setAttribute("ReservFahrten", MeineReservfahrte);
            request.setAttribute("offeneFahrten",offeneFahrten);

            request.getRequestDispatcher("HauptSeite.ftl").forward(request, response);


            hauptSeiteStore.complete();
        }

    }
}

