package de.unidue.inf.is.stores;

import de.unidue.inf.is.utils.DBUtil;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class FahrtBewrtenStore implements Closeable {
    private Connection connection;
    private boolean complete;

    public FahrtBewrtenStore() throws StoreException {
        try {
            connection = DBUtil.getExternalConnection();

        }
        catch (SQLException e) {
            throw new StoreException(e);
        }
    }

    public boolean FahrtBewerten(int benutzerid, int fahrtid,String beschreibung,String erstllungsdatum ,int bewertung) {
        boolean check;
        // check if user rate before or not
        try (PreparedStatement preparedStatement0=connection.
                prepareStatement("select benutzer from dbp109.schreiben where benutzer=? and fahrt=?")){
            System.out.println(":"+benutzerid+"/"+fahrtid);
            preparedStatement0.setInt(1,benutzerid);
            preparedStatement0.setInt(2,fahrtid);
            ResultSet res=preparedStatement0.executeQuery();
            System.out.println("after check if user rate before or not");
            if(!res.next()){
                // insert beschreibung , date , rate in bewertung table and get bewertung id
                try (PreparedStatement preparedStatement= connection.
                        prepareStatement("SELECT beid from new table (insert into dbp109.bewertung (textnachricht,erstellungsdatum,rating) values (?,?,?))")){
                    preparedStatement.setString(1,beschreibung);
                    preparedStatement.setString(2,erstllungsdatum);
                    preparedStatement.setInt(3,bewertung);
                    ResultSet Res1= preparedStatement.executeQuery();
                    Res1.next();
                    int BewertungID= Res1.getInt("beid");
                    // insert bewertung in schreiben table
                    try (PreparedStatement preparedStatement2= connection.
                            prepareStatement("insert into dbp109.schreiben (benutzer,fahrt,bewertung) values(?,?,?)")){
                        preparedStatement2.setInt(1,benutzerid);
                        preparedStatement2.setInt(2,fahrtid);
                        preparedStatement2.setInt(3,BewertungID);
                        preparedStatement2.executeUpdate();
                        System.out.println("insert bewertung in schreiben table succeed");
                        return true;

                    }
                }

            }else

                return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
