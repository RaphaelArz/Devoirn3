package Controlers;

import Tools.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CtrlMedecin
{
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public CtrlMedecin() {
        cnx = ConnexionBDD.getCnx();
    }

    public ArrayList<String> getAllMedecins()
    {
        ArrayList<String> lesMedecins = new ArrayList<>();
        try {
            ps = cnx.prepareStatement("select nomMedecin from medecin");
            rs = ps.executeQuery();
            while( rs.next()){
                lesMedecins.add(rs.getString(1));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        // A vous de jouer

        return lesMedecins;
    }

    public int getIdMedecinByName(String nomMed)
    {
        int numMed = 0;
        try {
            ps = cnx.prepareStatement("select idMedecin from medecin where nomMedecin=?");
            ps.setString(1, nomMed);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        // A vous de jouer

        return numMed;
    }
}
