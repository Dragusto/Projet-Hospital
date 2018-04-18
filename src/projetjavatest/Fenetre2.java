/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjavatest;

/*
 * 
 * Test de la fenêtre
 */
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.*;

/**
 *
 * Affiche dans la fenetre graphique les champs de tables et les requetes de la
 * BDD
 *
 * @author segado
 */
public class Fenetre2 extends JFrame implements ActionListener, ItemListener {
    /*
     * Attribut privés : objets de Connexion, AWT et Swing
     * 
     */

    private Connexion maconnexion;
    private final JLabel tab, req, res, lignes;
    private final JLabel nameECE, passwdECE, loginBDD, passwdBDD, nameBDD;
    private final JLabel Champ, Table, C1, C2, C3, rang;
    private final JTextField RequeteChamp, RequeteTable, RequeteC1, RequeteC2, RequeteC3, Requeterang;
    private final JTextField nameECETexte, loginBDDTexte, nameBDDTexte;
    private final JPasswordField passwdECETexte, passwdBDDTexte;
    private final JButton connect, exec1, local;
    private final java.awt.List listeDeTables, listeDeRequetes;
    private final JTextArea fenetreLignes, fenetreRes;
    private final JPanel p0, p1, nord, p2, p3, p4, p5;

    /**
     * Constructeur qui initialise tous les objets graphiques de la fenetre
     */
    public Fenetre2() {

        // creation par heritage de la fenetre
        super("Projet d'utilisation de JDBC dans MySQL");

        // mise en page (layout) de la fenetre visible
        setLayout(new BorderLayout());
        setBounds(0, 0, 400, 400);
        setResizable(true);
        setVisible(true);

        // creation des boutons
        connect = new JButton("Connexion ECE");
        local = new JButton("Connexion locale");
        exec1 = new JButton("Executer");

        // creation des listes pour les tables et les requetes
        listeDeTables = new java.awt.List(10, false);
        listeDeRequetes = new java.awt.List(10, false);

        // creation des textes
        nameECETexte = new JTextField();
        passwdECETexte = new JPasswordField(8);
        loginBDDTexte = new JTextField();
        passwdBDDTexte = new JPasswordField(8);
        nameBDDTexte = new JTextField();
        fenetreLignes = new JTextArea();
        fenetreRes = new JTextArea();
        RequeteChamp = new JTextField(null);
        RequeteTable = new JTextField(null);
        RequeteC1 = new JTextField(null);
        RequeteC2 = new JTextField(null);
        RequeteC3 = new JTextField(null);
        Requeterang = new JTextField(null);
        
        // creation des labels
        tab = new JLabel("Tables", JLabel.CENTER);
        lignes = new JLabel("Lignes", JLabel.CENTER);
        req = new JLabel("Requetes de sélection", JLabel.CENTER);
        res = new JLabel("Résultats requête", JLabel.CENTER);
        nameECE = new JLabel("login ECE :", JLabel.CENTER);
        passwdECE = new JLabel("password ECE :", JLabel.CENTER);
        loginBDD = new JLabel("login base :", JLabel.CENTER);
        passwdBDD = new JLabel("password base :", JLabel.CENTER);
        nameBDD = new JLabel("nom base :", JLabel.CENTER);
        Champ = new JLabel("Champ :", JLabel.CENTER);
        Table = new JLabel("Table :", JLabel.CENTER);
        C1 = new JLabel("Condition :", JLabel.CENTER);
        C2 = new JLabel("Condition :", JLabel.CENTER);
        C3 = new JLabel("Condition :", JLabel.CENTER);
        rang = new JLabel("Rang : ", JLabel.CENTER);
        
// creation des panneaux
        p0 = new JPanel();
        p1 = new JPanel();
        nord = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p4 = new JPanel();
        p5 = new JPanel();
        
        // mise en page des panneaux
        p0.setLayout(new GridLayout(1, 11));
        p1.setLayout(new GridLayout(1, 4));
        nord.setLayout(new GridLayout(2, 1));
        p2.setLayout(new GridLayout(1, 4));
        p3.setLayout(new GridLayout(1, 6));
        p4.setLayout(new GridLayout(1,7));
        p5.setLayout(new GridLayout(2,1));
        
        // ajout des objets graphqiues dans les panneaux
        p0.add(nameECE);
        p0.add(nameECETexte);
        p0.add(passwdECE);
        p0.add(passwdECETexte);
        p0.add(loginBDD);
        p0.add(loginBDDTexte);
        p0.add(passwdBDD);
        p0.add(passwdBDDTexte);
        p0.add(connect);
        p0.add(nameBDD);
        p0.add(nameBDDTexte);
        p0.add(local);
        p1.add(tab);
        p1.add(lignes);
        //p1.add(req);
        p1.add(res);
        nord.add("North", p0);
        nord.add("North", p1);
        p2.add(listeDeTables);
        p2.add(fenetreLignes);
        //p2.add(listeDeRequetes);
        p2.add(fenetreRes);
        p3.add(Champ);
        p3.add(RequeteChamp);
        p3.add(Table);
        p3.add(RequeteTable);
        p3.add(C1);
        p3.add(RequeteC1);
        p4.add(C2);
        p4.add(RequeteC2);
        p4.add(C3);
        p4.add(RequeteC3);
        p4.add(rang);
        p4.add(Requeterang);
        p4.add(exec1);
        p5.add(p3);
        p5.add(p4);
        
        // ajout des listeners
        connect.addActionListener(this);
        exec1.addActionListener(this);
        local.addActionListener(this);
        nameECETexte.addActionListener(this);
        passwdECETexte.addActionListener(this);
        loginBDDTexte.addActionListener(this);
        passwdBDDTexte.addActionListener(this);
        listeDeTables.addItemListener(this);
        listeDeRequetes.addItemListener(this);

        // couleurs des objets graphiques
        tab.setBackground(Color.MAGENTA);
        lignes.setBackground(Color.MAGENTA);
        req.setBackground(Color.MAGENTA);
        res.setBackground(Color.MAGENTA);
        listeDeTables.setBackground(Color.CYAN);
        fenetreLignes.setBackground(Color.WHITE);
        listeDeRequetes.setBackground(Color.GREEN);
        fenetreRes.setBackground(Color.GREEN);
        p1.setBackground(Color.LIGHT_GRAY);

        // disposition geographique des panneaux
        add("North", nord);
        add("Center", p2);
        add("South", p5);


        // pour fermer la fenetre
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                System.exit(0); // tout fermer												System.exit(0); // tout fermer
            }
        });
    }

    /**
     * Méthode privée qui initialise la liste des tables
     */
    private void remplirTables() {
        maconnexion.ajouterTable("chambre");
        maconnexion.ajouterTable("docteur");
        maconnexion.ajouterTable("employe");
        maconnexion.ajouterTable("hospitalisation");
        maconnexion.ajouterTable("infirmier");
        maconnexion.ajouterTable("malade");
        maconnexion.ajouterTable("service");
        maconnexion.ajouterTable("soigne");
    }

    /**
     * Méthode privée qui initialise la liste des requetes de selection KI
     */
    private void remplirRequetes() {
       /* maconnexion.ajouterRequete("SELECT code_service FROM chambre;");
        maconnexion.ajouterRequete("SELECT no_chambre FROM chambre;");
        maconnexion.ajouterRequete("SELECT AVG (Emp.sal) FROM Emp, Mission WHERE Emp.empno = Mission.empno;");
        maconnexion.ajouterRequete("SELECT Dept.*, Emp.* FROM Dept, Emp WHERE Dept.deptno=Emp.deptno AND comm>0;");
        maconnexion.ajouterRequete("SELECT hiredate, empno, ename FROM Emp WHERE (((hiredate)>='1981-05-01' And (hiredate)<'1981-05-31'))ORDER BY hiredate;");
        maconnexion.ajouterRequete("SELECT ename, job FROM Emp ORDER BY job;");
        maconnexion.ajouterRequete("SELECT DISTINCT dname, job FROM Dept, Emp WHERE Dept.deptno=Emp.deptno AND job='Clerk';");
    */}

    /**
     * Méthode privée qui initialise la liste des requetes de MAJ
     */
    private void remplirRequetesMaj() {
      /*  // Requêtes d'insertion
        maconnexion.ajouterRequeteMaj("INSERT INTO Dept (deptno,dname,loc) VALUES (50,'ECE','Paris');");

        // Requêtes de modification
        maconnexion.ajouterRequeteMaj("UPDATE Chambre SET no_chambre=110 WHERE no_chambre=101;");

        // Requêtes de suppression
        maconnexion.ajouterRequeteMaj("DELETE FROM Chambre WHERE no_chambre= 101 ;");
*/
    }

    /**
     *
     * Afficher les tables
     */
    public void afficherTables() {
        for (String table : maconnexion.tables) {
            listeDeTables.add(table);
        }
    }

    /**
     *
     * Afficher les lignes de la table sélectionnée
     */
    public void afficherLignes(String nomTable) {
        try {
            ArrayList<String> liste;

            // effacer les résultats
            fenetreLignes.removeAll();

            // recupérér les résultats de la table selectionnee
            liste = maconnexion.remplirChampsTable(nomTable);

            // afficher les champs de la table selectionnee 
            fenetreLignes.setText("");
            for (String liste1 : liste) {
                fenetreLignes.append(liste1);
            }

            // recuperer la liste de la table sélectionnée
            String requeteSelectionnee = "select * from " + nomTable + ";";
            liste = maconnexion.remplirChampsRequete(requeteSelectionnee);

            // afficher les lignes de la requete selectionnee a partir de la liste
            for (String liste1 : liste) {
                fenetreLignes.append(liste1);
            }

        } catch (SQLException e) {
            // afficher l'erreur dans les résultats
            fenetreRes.setText("");
            fenetreRes.append("Echec table SQL");
            e.printStackTrace();

        }
    }

    /**
     *
     * Afficher les requetes de selection et de MAJ dans la fenetre
     */
    public void afficherRequetes() {
        for (String requete : maconnexion.requetes) {
            listeDeRequetes.add(requete);
        }
    }
    
     public void afficherRequetesMaj() {
        for (String requete : maconnexion.requetesMaj) {
            listeDeRequetes.add(requete);
        }
    }

    /**
     *
     * Afficher et retourner les résultats de la requete sélectionnée
     *
     * @param requeteSelectionnee
     */
    public ArrayList<String> afficherRes(String requeteSelectionnee) throws SQLException {
        ArrayList<String> liste = null;
        try {

            // effacer les résultats
            fenetreRes.removeAll();

            // recupérér les résultats de la requete selectionnee
            liste = maconnexion.remplirChampsRequete(requeteSelectionnee);

            // afficher les lignes de la requete selectionnee a partir de la liste
            fenetreRes.setText("");
            for (String liste1 : liste) {
                fenetreRes.append(liste1);
            }
        } catch (SQLException e) {
            // afficher l'erreur dans les résultats
            fenetreRes.setText("");
            fenetreRes.append("Echec requete SQL");
        }
        return liste;
    }

    /**
     *
     * Pour gerer les actions sur les boutons on utilise la fonction
     * actionPerformed
     *
     * @param evt
     */
    @Override
    @SuppressWarnings("CallToThreadDumpStack")
    public void actionPerformed(ActionEvent evt) {
        Object source = evt.getSource();

        // tester cas de la commande evenementielle
        if (source == connect) {
            ArrayList<String> liste;
            String passwdECEString = new String(passwdECETexte.getPassword());
            String passwdBDDString = new String(passwdBDDTexte.getPassword());
            try {
                try {
                    // tentative de connexion si les 4 attributs sont remplis
                    maconnexion = new Connexion(nameECETexte.getText(), passwdECEString,
                            loginBDDTexte.getText(), passwdBDDString);

                    // effacer les listes de tables et de requêtes
                    listeDeTables.removeAll();
                    listeDeRequetes.removeAll();

                    // initialisation de la liste des requetes de selection et de MAJ
                    remplirTables();
                    remplirRequetes();
                    remplirRequetesMaj();

                    // afficher la liste de tables et des requetes
                    afficherTables();
                    afficherRequetes();
                    afficherRequetesMaj();

                    // se positionner sur la première table et requête de selection
                    listeDeTables.select(0);
                    listeDeRequetes.select(0);

                    // afficher les champs de la table sélectionnée
                    String nomTable = listeDeTables.getSelectedItem();

                    // recuperer les lignes de la table selectionnee
                    afficherLignes(nomTable);

                    // recuperer la liste des lignes de la requete selectionnee
                    String requeteSelectionnee = listeDeRequetes.getSelectedItem();

                    // afficher les résultats de la requete selectionnee
                    afficherRes(requeteSelectionnee);
                } catch (ClassNotFoundException cnfe) {
                    System.out.println("Connexion echouee : probleme de classe");
                    cnfe.printStackTrace();
                }
            } catch (SQLException e) {
                System.out.println("Connexion echouee : probleme SQL");
                e.printStackTrace();
            }
        } else if (source == local) {
            ArrayList<String> liste;
            try {
                try {
                    // tentative de connexion si les 4 attributs sont remplis
                    maconnexion = new Connexion(nameBDDTexte.getText(), "root", "");

                    // effacer les listes de tables et de requêtes
                    listeDeTables.removeAll();
                    listeDeRequetes.removeAll();

                    // initialisation de la liste des requetes de selection et de MAJ
                    remplirTables();
                    remplirRequetes();
                    remplirRequetesMaj();

                    // afficher la liste de tables et des requetes
                    afficherTables();
                    afficherRequetes();
                    afficherRequetesMaj();

                    // se positionner sur la première table et requête de selection
                    listeDeTables.select(0);
                    listeDeRequetes.select(0);

                    // afficher les champs de la table sélectionnée
                    String nomTable = listeDeTables.getSelectedItem();

                    // recuperer les lignes de la table selectionnee
                    afficherLignes(nomTable);

                    // recuperer la liste des lignes de la requete selectionnee
                    String requeteSelectionnee = listeDeRequetes.getSelectedItem();

                    // afficher les résultats de la requete selectionnee
                    afficherRes(requeteSelectionnee);
                   
                } catch (ClassNotFoundException cnfe) {
                    System.out.println("Connexion echouee : probleme de classe");
                    cnfe.printStackTrace();
                }
            } catch (SQLException e) {
                System.out.println("Connexion echouee : probleme SQL");
                e.printStackTrace();
            }
        } else if (source == exec1) {
            
            // récupérer le texte de la requête
            String requetechamp = RequeteChamp.getText() ; 
            String requetetable = RequeteTable.getText();
            String requetec1 = RequeteC1.getText();
            String requetec2 = RequeteC2.getText();
            String requetec3 = RequeteC3.getText();
            String requeterand = Requeterang.getText();
            String requeteSelectionnee = null;
            // effacer les résultats
            fenetreRes.removeAll();
            if (!"".equals(requetechamp) )
            {
                requeteSelectionnee ="SELECT " + requetechamp;
            }
            if (!"".equals(requetetable))
            {
                requeteSelectionnee = requeteSelectionnee + " FROM " + requetetable ;
            }
            if (!"".equals(requetec1))
            {
                requeteSelectionnee = requeteSelectionnee + " WHERE " +requetec1;
                if (!"".equals(requetec2))
                {
                    requeteSelectionnee = requeteSelectionnee + " AND " + requetec2;
                    if (!"".equals(requetec3))
                             {
                                 requeteSelectionnee = requeteSelectionnee + " AND " + requetec3 + ";";
                             }
                    else
                     {
                         requeteSelectionnee = requeteSelectionnee + ";";
                     }
                }
                else
                {
                    requeteSelectionnee = requeteSelectionnee + ";";
                }
                }
                else
            {
                requeteSelectionnee = requeteSelectionnee + ";";
            }
            System.out.println("champ= " + requetechamp);
            System.out.println("table = " + requetetable);
            System.out.println("c1 = " + requetec1);
            System.out.println("c2 = " + requetec2);
            System.out.println("c3 = " + requetec3);
            System.out.println(requeteSelectionnee);
            

            
            try {
                // afficher les résultats de la requete selectionnee
                if (afficherRes(requeteSelectionnee) != null) {
                    maconnexion.ajouterRequete(requeteSelectionnee);
                    maconnexion.ajouterRequeteMaj(requeteSelectionnee);
                    listeDeRequetes.removeAll();
                    afficherRequetes();
                }

            } catch (SQLException ex) {

            }

        }
    }

    /**
     *
     * Pour gerer les actions sur items d'une liste on utilise la methode
     * itemStateChanged
     */
    @Override
    @SuppressWarnings("CallToThreadDumpStack")
    public void itemStateChanged(ItemEvent evt) {
        // sélection d'une requete et afficher ses résultats
        if (evt.getSource() == listeDeRequetes) {
            // recuperer la liste des lignes de la requete selectionnee
            String requeteSelectionnee = listeDeRequetes.getSelectedItem();
            try {
                afficherRes(requeteSelectionnee);
            } catch (SQLException ex) {
                Logger.getLogger(Fenetre2.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (evt.getSource() == listeDeTables) {
            // afficher les lignes de la table sélectionnée
            String nomTable = listeDeTables.getSelectedItem();
            afficherLignes(nomTable);
        }
    }
}
