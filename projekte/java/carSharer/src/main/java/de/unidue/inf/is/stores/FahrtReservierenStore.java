package de.unidue.inf.is.stores;

import de.unidue.inf.is.utils.DBUtil;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FahrtReservierenStore implements Closeable {
    private Connection connection;
    private boolean complete;

    public FahrtReservierenStore() throws StoreException {
        try {
            connection = DBUtil.getExternalConnection();
        }
        catch (SQLException e) {
            throw new StoreException(e);
        }
    }

    public boolean FahrtReservieren(int fid,int bid,int anzahl)  {


        /* check if the user"bid" already reserve this trip"fid" before.  the result will be saved in Res0 so if it empty is's mean that user didn't reserve before so I will proceed if not return false*/
        try(PreparedStatement preparedStatement0 = connection.prepareStatement("select kunde from dbp109.reservieren r where r.kunde=? and r.fahrt=?")){
            preparedStatement0.setInt(1,bid);
            preparedStatement0.setInt(2,fid);
            ResultSet Res0=preparedStatement0.executeQuery();
            if(!Res0.next()){

                /* check if this trip"fid" is offen  if Res contains result it's mean that the conditions are fulfilled */
                try (PreparedStatement preparedStatement= connection.

                        prepareStatement("SELECT f.FID,f.MAXPLAETZE,r.anzPlaetze,f.STATUS from dbp109.fahrt f left JOIN (SELECT fahrt,sum(anzPlaetze)AS anzPlaetze FROM dbp109.reservieren r GROUP BY fahrt)r ON f.fid=r.fahrt where f.fid=?  AND f.status='offen'"))
                {

                    preparedStatement.setInt(1,fid);
                    ResultSet Res= preparedStatement.executeQuery();
                    Res.next();
                    /*check if the needed"anzahl" places equal or more than available if it return true will proceed otherwise will not*/

                    if(Res.getInt("MAXPLAETZE")>=Res.getInt("anzPlaetze")){

                        try (PreparedStatement preparedStatement1=connection.
                                prepareStatement("insert into  dbp109.reservieren (kunde,fahrt,anzPlaetze) values (?,?,?)")){
                            preparedStatement1.setInt(1,bid);
                            preparedStatement1.setInt(2,fid);
                            preparedStatement1.setInt(3,anzahl);
                            preparedStatement1.executeUpdate();

                            System.out.println("hello from fahert reservieren store,  insert new reserve : successed ");
                            return true;
                        }

                    }

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("hello from fahrt reservieren store,  insert new reserve : failed ");
        return false;
    }


    /** löschen **/
    public boolean FahrtLoeschen(int fid,int bid){
        /* if the user already book the trip and rate it. it will delete the row which contains kunde,fahrt from reservieren and benutzer,fahrt from schreiben , as well as
         * is user booked the trip but didn't rate it , well delete row of kunde, fahrt from reservieren because we used left join
         * if he didn't find any row to delete will throw exception and return false
         *  */
        try (PreparedStatement preparedStatement1=connection.
                prepareStatement("delete from dbp109.reservieren r where r.kunde=? and r.fahrt=?")){
            preparedStatement1.setInt(1,bid);
            preparedStatement1.setInt(2,fid);
            preparedStatement1.executeUpdate();
            System.out.println("the trip deleted suc from reservieren");
            try(PreparedStatement preparedStatement2=connection.
                    prepareStatement("select * from dbp109.schreiben s where s.benutzer=? and s.fahrt=?")){
                System.out.println("bid: "+bid+"/"+fid);
                preparedStatement2.setInt(1,bid);
                preparedStatement2.setInt(2,fid);
                ResultSet Res2=preparedStatement2.executeQuery();
                if(Res2.next()){
                    try(PreparedStatement preparedStatement3=connection.
                            prepareStatement("delete from dbp109.schreiben s where s.benutzer=? and s.fahrt=?")){
                        preparedStatement3.setInt(1,bid);
                        preparedStatement3.setInt(2,fid);
                        preparedStatement3.executeUpdate();
                        System.out.println("the trip  rate deleted suc from schreiben");

                        return true;
                    }

                }else return true;

            }
        }catch (SQLException throwables) { throwables.printStackTrace(); }
        System.out.println("the trip delete failed");

        return false;
    }



    public void complete() {
        complete = true;
    }


    @Override
    public void close() throws IOException {
        if (connection != null) {
            try {
                if (complete) {
                    connection.commit();
                }
                else {
                    connection.rollback();
                }
            }
            catch (SQLException e) {
                throw new StoreException(e);
            }
            finally {
                try {
                    connection.close();
                }
                catch (SQLException e) {
                    throw new StoreException(e);
                }
            }
        }
    }

}
