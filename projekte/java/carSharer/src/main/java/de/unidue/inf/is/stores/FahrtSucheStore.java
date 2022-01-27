package de.unidue.inf.is.stores;

import de.unidue.inf.is.domain.Fahrt;
import de.unidue.inf.is.utils.DBUtil;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FahrtSucheStore implements Closeable {
    private static FahrtSucheStore instance;

    private Connection connection;
    private boolean complete;

    public FahrtSucheStore() throws StoreException {
        try {
            connection = DBUtil.getExternalConnection();

        }
        catch (SQLException e) {
            throw new StoreException(e);
        }
    }

    public static FahrtSucheStore getInstance() {
        if (instance == null) {
            instance = new FahrtSucheStore();
        }

        return instance;
    }


    public List<Fahrt> FahrtSuche(String StartOrt, String ZielOrt, String date){
        List<Fahrt> fahrtSuche  = new ArrayList<>();
        try (PreparedStatement preparedStatement=connection.prepareStatement("SELECT * from dbp109.fahrt f where (LCASE(f.startort) like ? or f.startort like ?) and (LCASE(f.zielort) like ? or f.zielort like ?) and f.fahrtdatumzeit >=?" )){
            preparedStatement.setString(1,StartOrt + "%");
            preparedStatement.setString(2,StartOrt + "%");
            preparedStatement.setString(3,ZielOrt + "%");
            preparedStatement.setString(4,ZielOrt + "%");
            preparedStatement.setString(5,date);
            ResultSet Res= preparedStatement.executeQuery();
            System.out.println("I am in fahrtsuche now");
            while (Res.next()){
                System.out.println("I am here");
                Fahrt fahrt= new Fahrt(Res.getInt("fid"), Res.getString("startort"), Res.getString("zielort"), Res.getInt("fahrtkosten"));
                fahrtSuche.add(fahrt);
                System.out.println("Hello from suche store I did the search: I found sth ");
            }
            System.out.println("I am after while now");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println("Hello from suche store I did the search:  nothing ");

        return fahrtSuche;
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
