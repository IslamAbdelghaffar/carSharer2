package de.unidue.inf.is;

import de.unidue.inf.is.domain.Fahrt;
import de.unidue.inf.is.domain.benutzer;
import de.unidue.inf.is.domain.bewertung;
import de.unidue.inf.is.domain.reservieren;
import de.unidue.inf.is.stores.FahrtDetailsStore;
import de.unidue.inf.is.stores.FahrtReservierenStore;
import org.omg.CORBA.Request;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class FahrtDetailsServlet  extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static reservieren  neureservieren;
    /*do get responsible for show the content of fahrtdeatils page*/
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int user=5;
        try (FahrtDetailsStore fahrtDetailsStore=new FahrtDetailsStore()){
            /* get farht id and benutzer id and save them */
            //System.out.println("bid:   "+neureservieren.getKunde()+"/"+neureservieren.getFahrt());

            neureservieren = new reservieren(Integer.parseInt(request.getParameter("fid")),Integer.parseInt(request.getParameter("bid")));



            //Aktionenlist
            Fahrt fahrt= fahrtDetailsStore.getFahrtDetails(neureservieren.getFahrt());
            benutzer benutzer=fahrtDetailsStore.getBenutzer();
            //bewertung
            List<bewertung> bewertung=fahrtDetailsStore.getbewertung(neureservieren.getFahrt());

            // contains user's email in the same order as bewertungen
            List<benutzer> benutzersemails=fahrtDetailsStore.getBenutzers();
            // Durschschnittsrating for fart = fid
            int Durschschnittrating=fahrtDetailsStore.getAveragerate(neureservieren.getFahrt());

            /**bewertung request**/
            request.setAttribute("average",Durschschnittrating);
            request.setAttribute("benutzerBewertungEmail",benutzersemails);
            request.setAttribute("bewertung",bewertung);

            /**aktionliste request**/
            request.setAttribute("benutzer",benutzer);
            request.setAttribute("user",user);
            /**Informationen request**/
            request.setAttribute("FahrtDetails",fahrt);

            /**forward all data to viewer**/
            request.getRequestDispatcher("FahrtDetails.ftl").forward(request, response);
            fahrtDetailsStore.complete();
        }
    }






    /*do post responsible for getting the attributes from fahrtdetails page and handling the (reservieren/loeschen) processes */
    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        FahrtReservierenStore fahrtReservierenStore= new FahrtReservierenStore();



        if(request.getParameter("action").equals("reservieren")){
            /***
             * set anzahl to object neureservieren
             * **/

            neureservieren.setAnzPlaetze(Integer.parseInt(request.getParameter("Anzahl")));


            if(fahrtReservierenStore.FahrtReservieren(neureservieren.getFahrt(),neureservieren.getKunde(),neureservieren.getAnzPlaetze())){

                try (FahrtDetailsStore fahrtDetailsStore=new FahrtDetailsStore()){
                    //Aktionenlist
                    Fahrt fahrt= fahrtDetailsStore.getFahrtDetails(neureservieren.getFahrt());
                    benutzer benutzer=fahrtDetailsStore.getBenutzer();
                    //bewertung
                    List<bewertung> bewertung=fahrtDetailsStore.getbewertung(neureservieren.getFahrt());

                    // contains user's email in the same order as bewertungen
                    List<benutzer> benutzersemails=fahrtDetailsStore.getBenutzers();
                    // Durschschnittsrating for fart = fid
                    int Durschschnittrating=fahrtDetailsStore.getAveragerate(neureservieren.getFahrt());

                    /**bewertung request**/
                    request.setAttribute("average",Durschschnittrating);
                    request.setAttribute("benutzerBewertungEmail",benutzersemails);
                    request.setAttribute("bewertung",bewertung);

                    /**aktionliste request**/
                    request.setAttribute("benutzer",benutzer);

                    /**Informationen request**/
                    request.setAttribute("FahrtDetails",fahrt);

                    /**forward all data to viewer**/
                    request.setAttribute("message","Succeed");
                    request.getRequestDispatcher("FahrtDetails.ftl").forward(request, response);
                    fahrtDetailsStore.complete();
                }

            } else{
                try (FahrtDetailsStore fahrtDetailsStore=new FahrtDetailsStore()){
                    //Aktionenlist
                    Fahrt fahrt= fahrtDetailsStore.getFahrtDetails(neureservieren.getFahrt());
                    benutzer benutzer=fahrtDetailsStore.getBenutzer();
                    //bewertung
                    List<bewertung> bewertung=fahrtDetailsStore.getbewertung(neureservieren.getFahrt());

                    // contains user's email in the same order as bewertungen
                    List<benutzer> benutzersemails=fahrtDetailsStore.getBenutzers();
                    // Durschschnittsrating for fart = fid
                    int Durschschnittrating=fahrtDetailsStore.getAveragerate(neureservieren.getFahrt());

                    /**bewertung request**/
                    request.setAttribute("average",Durschschnittrating);
                    request.setAttribute("benutzerBewertungEmail",benutzersemails);
                    request.setAttribute("bewertung",bewertung);

                    /**aktionliste request**/
                    request.setAttribute("benutzer",benutzer);

                    /**Informationen request**/
                    request.setAttribute("FahrtDetails",fahrt);

                    /**forward all data to viewer**/
                    request.setAttribute("message","Failed");
                    request.getRequestDispatcher("FahrtDetails.ftl").forward(request, response);
                    fahrtDetailsStore.complete();
                }
            }
        }




        else if(request.getParameter("action").equals("loeschen")){
            /***
             * get fahrt id and benutzer id and anzahl von plaetze and save it
             * **/

            if(fahrtReservierenStore.FahrtLoeschen(neureservieren.getFahrt(),neureservieren.getKunde())){
                try {

                    HauptSeiteServlet hauptSeiteServlet =new HauptSeiteServlet();
                    hauptSeiteServlet.doGet(request,response);
                    fahrtReservierenStore.complete();
                } catch (ServletException e) {
                    e.printStackTrace(); }

            }
            else
                try {

                    HauptSeiteServlet hauptSeiteServlet =new HauptSeiteServlet();
                    hauptSeiteServlet.doGet(request,response);
                    fahrtReservierenStore.complete();
                } catch (ServletException e) {
                    e.printStackTrace(); }
        }

        else
            doGet(request,response);


    }
}
