package de.unidue.inf.is.stores;

import de.unidue.inf.is.domain.Fahrt;
import de.unidue.inf.is.domain.benutzer;
import de.unidue.inf.is.utils.DBUtil;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BestDriverStore implements Closeable {
    private static BestDriverStore instance;
    private Connection connection;
    private boolean complete;
    List<benutzer> benutzers;

    public List<benutzer> getBenutzers() {
        return benutzers;
    }

    public BestDriverStore() throws StoreException {
        try {
            connection = DBUtil.getExternalConnection();

        }
        catch (SQLException e) {
            throw new StoreException(e);
        }
    }
    public static BestDriverStore getInstance() {
        if (instance == null) {
            instance = new BestDriverStore();
        }

        return instance;
    }

    public List<Fahrt> BestDriverData (){
        benutzer BestDriverEmail;
        List<Fahrt> bestDriverFahrten= new ArrayList<>();
        Fahrt bestDriverFahrt;

        try(PreparedStatement preparedStatement= connection.prepareStatement("SELECT t2.fahrt,t2.startort,t2.zielort,t2.average, t2.email from (SELECT t1.fahrt,t1.startort,t1.zielort,t1.average, b.email from (select t.fahrt,t.average,f.anbieter,f.startort,f.zielort from (select s.fahrt, avg(rating) as Average  from dbp109.schreiben s inner join dbp109.bewertung b on s.bewertung=b.beid group by s.fahrt )t  left join dbp109.fahrt f on t.fahrt=f.fid )t1 left join dbp109.benutzer b on b.bid=t1.anbieter)t2 where  t2.average=(select max(average) from (select  avg(rating)as average from dbp109.schreiben s inner join dbp109.bewertung b on s.bewertung=b.beid  left join dbp109.fahrt f on s.fahrt=f.fid group by s.fahrt)t2)")){
            ResultSet Res=preparedStatement.executeQuery();
            System.out.println("Hello after exe");
            /** Driver email **/
            BestDriverEmail = new benutzer(Res.getString(" EMAIL"));
            benutzers.add(BestDriverEmail);
            System.out.println("Hello after BestDriverEmail");

            /** best driver Fahrt details  **/
            bestDriverFahrt = new Fahrt(Res.getInt("FAHRT"),Res.getString("STARTORT"),Res.getString("ZIELORT"),Res.getInt("AVERAGE"));
            bestDriverFahrten.add(bestDriverFahrt);
            System.out.println("Hello after bestDriverFahrten");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return bestDriverFahrten;
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
