//model of hauptseite
package de.unidue.inf.is.stores;

import de.unidue.inf.is.domain.Fahrt;
import de.unidue.inf.is.domain.reservieren;
import de.unidue.inf.is.utils.DBUtil;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class HauptSeiteStore implements Closeable {

    private  Connection connection;
    private boolean complete;



    public HauptSeiteStore() throws StoreException {
        try {
            connection = DBUtil.getExternalConnection();
            connection.setAutoCommit(false);
        }
        catch (SQLException e) {
            throw new StoreException(e);
        }
    }

    public  List<Fahrt> getMeineReservFahrten(int user) throws StoreException{
        List<Fahrt> fahrts =new ArrayList<>();

        try {PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT kunde,fahrt,startort,zielort,STATUS FROM dbp109.reservieren r INNER JOIN dbp109.fahrt f ON r.fahrt=f.fid where kunde=?");{
            preparedStatement.setInt(1,user);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){


                Fahrt fahrt= new Fahrt(resultSet.getInt("fahrt"),
                        resultSet.getString("STARTORT"),
                        resultSet.getString("ZIELORT"),
                        resultSet.getString("STATUS"));
                System.out.println(fahrt.getFid());
                fahrts.add(fahrt);

            }
            return fahrts;

        }

        } catch (SQLException e) {
            throw new StoreException(e);
        }
    }

    public List<Fahrt> getOffeneFahrten(){
        List<Fahrt> offeneFahrten = new ArrayList<>();
        try {
            PreparedStatement preparedStatement=connection
                    .prepareStatement("select f.fid,f.STARTORT,f.zielort,f.STATUS,f.maxPlaetze,f.fahrtkosten, r.anzPlaetze FROM dbp109.fahrt f left JOIN (SELECT fahrt,count(anzPlaetze)AS anzPlaetze FROM dbp109.reservieren r GROUP BY fahrt)r ON f.fid=r.fahrt WHERE f.status='offen'");
            ResultSet res= preparedStatement.executeQuery();
            while (res.next()){
                //save first part of returned table of type Fahrt
                Fahrt OffeneFahrt= new Fahrt(res.getInt("Fid"),
                        res.getString("STARTORT"),
                        res.getString("ZIELORT"),
                        res.getFloat("FAHRTKOSTEN"),
                        (res.getInt("MAXPLAETZE")-res.getInt("ANZPLAETZE")));
                offeneFahrten.add(OffeneFahrt);
            }
            return offeneFahrten;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return offeneFahrten;
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
