package Controlers;

import Entities.Consultation;
import Tools.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CtrlConsultation
{
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public CtrlConsultation() {
        cnx = ConnexionBDD.getCnx();
    }

    public int getLastNumberOfConsultation() throws SQLException {
        int maxNumero = 0;


            ps = cnx.prepareStatement("select max(idConsult) from consultation");
            rs = ps.executeQuery();
            maxNumero = rs.getInt(1);

        return maxNumero;
    }


    public void InsertConsultation(int idConsult, String dateConsultation, int numPatient,int numMedecin)
    {

        try {
            ps = cnx.prepareStatement("insert into consultation (idConsult,dateConsult,numPatient,numMedecin)values (?,?,?,?)");
            ps.setInt(1, idConsult);
            ps.setString(2, dateConsultation);
            ps.setInt(3, numPatient);
            ps.setInt(4,numMedecin);
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }


        // A vous de jouer
    }
}
