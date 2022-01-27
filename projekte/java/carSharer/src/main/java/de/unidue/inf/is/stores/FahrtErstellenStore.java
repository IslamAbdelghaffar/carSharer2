package de.unidue.inf.is.stores;

import de.unidue.inf.is.domain.Fahrt;
import de.unidue.inf.is.utils.DBUtil;

import java.io.IOException;
import java.sql.*;

import java.io.Closeable;

public final class FahrtErstellenStore implements Closeable {
    private Connection connection;
    private boolean complete;

    public FahrtErstellenStore() throws StoreException {
        try {
            connection = DBUtil.getExternalConnection();

        }
        catch (SQLException e) {
            throw new StoreException(e);
        }
    }


    public Fahrt FahrtErstellen(String Von,String Nach,String DateTime,int maxPlaetze,float fahrkosten,int anbieter,int Transportmittel,String Beschreibung) throws SQLException {
        Fahrt fahrt=null;
        try(PreparedStatement preparedStatement=connection
                .prepareStatement("INSERT INTO dbp109.fahrt (startort, zielort, fahrtdatumzeit, maxPlaetze, fahrtkosten, status, anbieter, transportmittel, beschreibung) values(?,?,?,?,?,?,?,?,?)")){
            fahrt=new Fahrt(Von,Nach,DateTime,maxPlaetze,fahrkosten,"offen",anbieter,Transportmittel,Beschreibung) ;
            preparedStatement.setString(1,fahrt.getStartort());
            preparedStatement.setString(2,fahrt.getZielort());
            preparedStatement.setString(3, fahrt.getFahrtdatumzeit());
            preparedStatement.setInt(4,fahrt.getMaxPlaetze());
            preparedStatement.setFloat(5,fahrt.getFahrtkosten());
            preparedStatement.setString(6,fahrt.getStatus());
            preparedStatement.setInt(7,fahrt.getAnbieter());
            preparedStatement.setInt(8,fahrt.getTransportmittel());
            preparedStatement.setString(9,fahrt.getBeschreibung());
            int x= preparedStatement.executeUpdate();
            connection.commit();
            return fahrt;
        }
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
