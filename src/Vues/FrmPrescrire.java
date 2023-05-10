package Vues;

import Controlers.*;
import Tools.ConnexionBDD;
import Tools.ModelJTable;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.event.*;


import java.sql.SQLException;
import java.text.SimpleDateFormat;


public class FrmPrescrire extends JFrame
{
    private JPanel pnlRoot;
    private JLabel lblTitre;
    private JLabel lblNumero;
    private JLabel lblDate;
    private JLabel lblNomMedecin;
    private JTextField txtNumeroConsultation;
    private JComboBox cboPatients;
    private JComboBox cboMedecins;
    private JButton btnInserer;
    private JTable tblMedicaments;
    private JPanel pnlDate;
    private JLabel lblNomPatient;
    private JLabel lblMedicaments;
    private JDateChooser dcDateConsultation;

    CtrlConsultation ctrlConsultation;
    CtrlMedecin ctrlMedecin;
    CtrlPatient ctrlPatient;
    CtrlMedicament ctrlMedicament;
    CtrlPrescrire ctrlPrescrire;
    ModelJTable mdl;
    public FrmPrescrire() throws SQLException, ClassNotFoundException {
        this.setTitle("Prescrire");
        this.setContentPane(pnlRoot);
        this.pack();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        ConnexionBDD maCnx = new ConnexionBDD();

        // Gestion de la date : ne pas supprimer les 3 lignes de code
        dcDateConsultation = new JDateChooser();
        dcDateConsultation.setDateFormatString("yyyy-MM-dd");
        pnlDate.add(dcDateConsultation);

        // A vous de jouer

        ctrlPatient = new CtrlPatient();
        ctrlConsultation = new CtrlConsultation();
        ctrlMedecin= new CtrlMedecin();
        ctrlMedicament = new CtrlMedicament();
        mdl= new ModelJTable();
        mdl.loadDatasMedicaments(ctrlMedicament.getAllMedicaments());
        tblMedicaments.setModel(mdl);





        //txtNumeroConsultation.setText(String.valueOf(ctrlConsultation.getLastNumberOfConsultation()+1));
        txtNumeroConsultation.setText("35");


        for (String unPatient: ctrlPatient.getAllPatients()){
            cboPatients.addItem(unPatient);
        }
        for (String unMedecin :ctrlMedecin.getAllMedecins()){
            cboMedecins.addItem(unMedecin);
        }

        btnInserer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ctrlConsultation.InsertConsultation(Integer.parseInt(txtNumeroConsultation.getText()),pnlDate.toString(),ctrlPatient.getIdPatientByName(cboPatients.getSelectedItem().toString()),ctrlMedecin.getIdMedecinByName(cboMedecins.getSelectedItem().toString()));
            }
        });
    }
}
