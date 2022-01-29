package de.unidue.inf.is;

import de.unidue.inf.is.domain.benutzer;
import de.unidue.inf.is.stores.FahrtErstellenStore;
import de.unidue.inf.is.utils.DateTimeUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FahrtErstellenServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void doGet (HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        try {
            request.getRequestDispatcher("FahrtErstellen.ftl").forward(request,response);

        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        try {
            if(request.getParameter("action").equals("FahrtErstellen")){
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd.MM.yyyy");


                String Von=request.getParameter("von");
                String Nach=request.getParameter("nach");
                int maxPlaetze=Integer.parseInt(request.getParameter("kapazität"));
                int fahrkosten=Integer.parseInt(request.getParameter("kosten"));
                int Transportmittel=Integer.parseInt(request.getParameter("Transportmittel"));
                String Beschreibung =request.getParameter("Beschreibung");

/**************************************/

                DateFormat outputFormat = new SimpleDateFormat("dd.MM.yyyy");
                DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");

                String inputText = request.getParameter("date");
                Date date = inputFormat.parse(inputText);
                String outputText = outputFormat.format(date);
                String DateTime = DateTimeUtil.convertDateAndTimeToDB2DateTime(outputText,request.getParameter("time"));
                FahrtErstellenStore fahrtErstellenStore = new FahrtErstellenStore();
/*************************************/
                if(fahrtErstellenStore.FahrtErstellen(Von, Nach, DateTime, maxPlaetze, fahrkosten, benutzer.getBid(), Transportmittel, Beschreibung) != null){
                    request.setAttribute("message","erfolgreich");
                    request.getRequestDispatcher("FahrtErstellen.ftl").forward(request,response);
                    fahrtErstellenStore.complete();
                }else {
                    request.setAttribute("message","Fehlgeschlagen,Sie Sind Kein Fahrer ,Sie Besitzen Kein Fahrerlaubnis,Sie Können Nicht Ein Fahrt Erstelln");
                    request.getRequestDispatcher("FahrtErstellen.ftl").forward(request,response);
                    fahrtErstellenStore.complete();
                }
            }  else
                doGet(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
