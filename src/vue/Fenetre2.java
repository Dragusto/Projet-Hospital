/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

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
import controleur.*;
import controleur.Connexion;

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

    // Declaration de la fenetre Connection
    private final JTextField nameECETexte, loginBDDTexte, nameBDDTexte; // declaration des champs de texte
    private final JLabel nameECE, passwdECE, loginBDD, passwdBDD, nameBDD; // declaration des label
    private final JPasswordField passwdECETexte, passwdBDDTexte; // declaration des champs pour les mdp
    private final JButton connect, local; // declaration des boutons
    private final JPanel p0, nord; // declaration du panneau
    private final JLayeredPane p01;// declaration d'un panel

    // Declaration des fenetre connecte en local et grace a gandalf
    private JFrame connecc, connecl; // declaration de fenetre
    private final JTextField requeteSQL; // declaration des champs de texte
    private final JLabel tab, req, res, lignes;// declaration des labels
    private final JLabel vide1, vide2;
    private final JLabel nameSQL;
    private final JButton exec1, exec2, exec3, exec4, deco1, deco2, SQL; // declaration des boutons
    private final JButton rechercher, supprimer, ajouter, modifier;
    private final java.awt.List listeDeTables, listeDeRequetes; // declaration des listes
    private final JTextArea fenetreLignes, fenetreRes; // declaration des aires de texte
    private final JPanel p1, p2, p3, p311, p312, p32; // declaration des panels
    private final JScrollPane p21, p22; // declaration des panels avec scrolling en plus
    private final JOptionPane error; // declaration d'une fenetre d'erreur

    // déclaration de fenetre permetant d'utiliser les fonctions rechercher, modifier, supprimer et ajouter
    // déclaration Swing fenetre pour Rechercher
    private JFrame voir;// declaration de fenetre
    private final JLabel Champ, Table, C1, C2, C3, rang, Table2, JC1, JC2, MOY, COUNT, GROUP, SUM;// declaration des labels
    private final JTextField RequeteChamp, RequeteTable, RequeteC1, RequeteC2, RequeteC3; // declaration des champs de texte
    private final JTextField Requeterang, RequeteTable2, JointC1, JointC2, Groupement; // declaration des champs de texte
    private final JCheckBox Moyenne, Count, Somme; // declaration de boitite a cocher facon boolean
    private final JPanel p4; // declaration de panel

    // Declaration Swing fenetre pour Modifier
    private JFrame modif;// declaration de fenetre
    private final JLabel M1, M2, M3, MC1, MC2, MCT;// declaration des labels
    private final JTextField ModTable, Mod1, Mod2, Mod3, ModC1, ModC2; // declaration des champs de texte
    private final JPanel p5;// declaration de panel

    // Declaration Swing fenetre Supprimer
    private JFrame suppr;// declaration de fenetre
    private final JLabel DT, DC1, DC2;// declaration des labels
    private final JTextField DelT, DelC1, DelC2; // declaration des champs de texte
    private final JPanel p6;// declaration de panel

    // Declaration Swing fenetre Ajouter
    private JFrame ajout;// declaration de fenetre
    private final JLabel IT, IC, IV;// declaration des labels
    private final JTextField InTa, InC, InV; // declaration des champs de texte
    private final JPanel p7;// declaration de panel

    /**
     * Constructeur qui initialise tous les objets graphiques de la fenetre
     */
    public Fenetre2() {

        // creation par heritage de la fenetre
        super("Connection");

        // mise en page (layout) de la fenetre visible
        setSize(420, 400);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // creation des boutons
        connect = new JButton("Connexion ECE");
        local = new JButton("Connexion locale");
        local.setSize(new Dimension(60, 100));
        // creation boutons selection choix
        exec1 = new JButton("Rechercher");
        exec2 = new JButton("Modifier");
        exec3 = new JButton("Supprimer");
        exec4 = new JButton("Ajouter");
        deco1 = new JButton("Deconnection");
        deco2 = new JButton("Deconnection");
        SQL = new JButton("Executer");

        //Creation messages erreur
        error = new JOptionPane();

        // Boutons validant la requetes
        rechercher = new JButton("Recherche");
        ajouter = new JButton("Ajouter");
        supprimer = new JButton("Supprimer");
        modifier = new JButton("Modifier");

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
        requeteSQL = new JTextField();

        //Texte pour Rechercher
        RequeteChamp = new JTextField(null);
        RequeteTable = new JTextField(null);
        RequeteC1 = new JTextField(null);
        RequeteC2 = new JTextField(null);
        RequeteC3 = new JTextField(null);
        Requeterang = new JTextField(null);
        RequeteTable2 = new JTextField(null);
        JointC1 = new JTextField(null);
        JointC2 = new JTextField(null);
        Groupement = new JTextField(null);
        //CheckBox pour Rechercher
        Moyenne = new JCheckBox();
        Count = new JCheckBox();
        Somme = new JCheckBox();

        // Texte pour modifier
        ModTable = new JTextField(null);
        Mod1 = new JTextField(null);
        Mod2 = new JTextField(null);
        Mod3 = new JTextField(null);
        ModC1 = new JTextField(null);
        ModC2 = new JTextField(null);

        // texte pour supprimer
        DelT = new JTextField(null);
        DelC1 = new JTextField(null);
        DelC2 = new JTextField(null);

        // Texte pour ajouter
        InTa = new JTextField(null);
        InC = new JTextField(null);
        InV = new JTextField(null);

        // creation des labels
        tab = new JLabel("Tables", JLabel.CENTER);
        lignes = new JLabel("Lignes", JLabel.CENTER);
        req = new JLabel("Requetes de sélection", JLabel.CENTER);
        res = new JLabel("Résultats requête", JLabel.CENTER);
        nameECE = new JLabel("login ECE :", JLabel.CENTER);
        passwdECE = new JLabel("password ECE :", JLabel.CENTER);
        loginBDD = new JLabel("login base :", JLabel.CENTER);
        vide1 = new JLabel("  ", JLabel.CENTER);
        passwdBDD = new JLabel("password base :", JLabel.CENTER);
        nameBDD = new JLabel("           nom base :", JLabel.CENTER);
        vide2 = new JLabel("  ", JLabel.CENTER);
        nameSQL = new JLabel("Entrez votre requete de sélection :", JLabel.CENTER);

        // Label pour rechercher
        Champ = new JLabel("Champ :", JLabel.CENTER);
        Table = new JLabel("Table :", JLabel.CENTER);
        C1 = new JLabel("Condition :", JLabel.CENTER);
        C2 = new JLabel("Condition :", JLabel.CENTER);
        C3 = new JLabel("Condition :", JLabel.CENTER);
        rang = new JLabel("Rang : ", JLabel.CENTER);
        Table2 = new JLabel("Table2 : ", JLabel.CENTER);
        JC1 = new JLabel("Condition Table1 : ", JLabel.CENTER);
        JC2 = new JLabel("Condition Table2 : ", JLabel.CENTER);
        MOY = new JLabel("Moyenne : ", JLabel.CENTER);
        COUNT = new JLabel("Compter : ", JLabel.CENTER);
        SUM = new JLabel("Somme :", JLabel.CENTER);
        GROUP = new JLabel("Grouper par :", JLabel.CENTER);

        // Label pour modifier
        MCT = new JLabel("Table :", JLabel.CENTER);
        MC1 = new JLabel("Condition :", JLabel.CENTER);
        MC2 = new JLabel("Condition :", JLabel.CENTER);
        M1 = new JLabel("Modification :", JLabel.CENTER);
        M2 = new JLabel("Modification :", JLabel.CENTER);
        M3 = new JLabel("Modification :", JLabel.CENTER);

        // Label pour supprimer
        DT = new JLabel("Table :", JLabel.CENTER);
        DC1 = new JLabel("Condition :", JLabel.CENTER);
        DC2 = new JLabel("Condition :", JLabel.CENTER);

        // label pour ajouter
        IT = new JLabel("Table :", JLabel.CENTER);
        IC = new JLabel("Champs :", JLabel.CENTER);
        IV = new JLabel("Valeurs :", JLabel.CENTER);

        // creation des panneaux
        // panel pour se connecter
        p0 = new JPanel();
        p01 = new JLayeredPane();
        nord = new JPanel();

        // Panel seclection des requetes
        p1 = new JPanel();
        p2 = new JPanel();
        p21 = new JScrollPane(fenetreLignes);
        p22 = new JScrollPane(fenetreRes);
        p3 = new JPanel();
        p311 = new JPanel();
        p312 = new JPanel();
        p32 = new JPanel();

        // Panel pour Rechercher
        p4 = new JPanel();
        // Panel pour Modifier
        p5 = new JPanel();
        // Panel pour Supprimer
        p6 = new JPanel();
        // panel pour Ajouter
        p7 = new JPanel();

        // mise en page des panneaux
        p0.setLayout(new GridLayout(4, 2));
        p01.setLayout(new GridLayout(3, 2));
        p1.setLayout(new GridLayout(1, 4));
        nord.setLayout(new BoxLayout(nord, BoxLayout.PAGE_AXIS));
        p2.setLayout(new GridLayout(1, 3));
        p3.setLayout(new GridLayout(2, 1));
        p311.setLayout(new GridLayout(1, 5));
        p312.setLayout(new GridLayout(1, 5));
        p32.setLayout(new GridLayout(1, 3));

        // ajout des objets graphqiues dans les panneaux p0 p01 et nord
        p0.add(nameECE);
        p0.add(nameECETexte);
        p0.add(passwdECE);
        p0.add(passwdECETexte);
        p0.add(loginBDD);
        p0.add(loginBDDTexte);
        p0.add(passwdBDD);
        p0.add(passwdBDDTexte);
        p01.add(nameBDD);
        p01.add(nameBDDTexte);
        nord.add(p0);
        connect.setPreferredSize(new Dimension(100, 60));// redimentionne le bouton connect
        nord.add(connect);
        nord.add(vide1);
        p01.setPreferredSize(new Dimension(500, 50));// redimentionne lepanneau p01
        nord.add(p01);
        local.setPreferredSize(new Dimension(100, 60));// redimentionne le bouton local
        nord.add(local);
        nord.add(vide2);

        // ajout du panel nord dans notre fenetre
        add(nord);

        // ajout des listeners
        connect.addActionListener(this);
        local.addActionListener(this);
        nameECETexte.addActionListener(this);
        passwdECETexte.addActionListener(this);
        loginBDDTexte.addActionListener(this);
        passwdBDDTexte.addActionListener(this);
        listeDeTables.addItemListener(this);
        listeDeRequetes.addItemListener(this);

        // Ajouts des Listeners des choix
        exec1.addActionListener(this);
        exec2.addActionListener(this);
        exec3.addActionListener(this);
        exec4.addActionListener(this);
        deco1.addActionListener(this);
        deco2.addActionListener(this);
        SQL.addActionListener(this);

        // Ajouts des Listeners de confirmation
        rechercher.addActionListener(this);
        modifier.addActionListener(this);
        supprimer.addActionListener(this);
        ajouter.addActionListener(this);

        // couleurs des objets graphiques
        tab.setBackground(Color.MAGENTA);
        lignes.setBackground(Color.MAGENTA);
        req.setBackground(Color.MAGENTA);
        res.setBackground(Color.MAGENTA);
        listeDeTables.setBackground(new Color(212, 227, 247));
        fenetreLignes.setBackground(new Color(205, 208, 213));
        listeDeRequetes.setBackground(new Color(212, 227, 247));
        fenetreRes.setBackground(new Color(212, 227, 247));
        p1.setBackground(Color.LIGHT_GRAY);

        // ajout des objets graphqiues dans les panneaux p1
        p1.add(tab);
        p1.add(lignes);
        p1.add(res);
        // ajout des objets graphqiues dans les panneaux p2
        p2.add(listeDeTables);
        p2.add(p21);
        p2.add(p22);
        // ajout des objets graphique des requetes pour la fenetre gandalf
        p311.add(exec1);
        p311.add(exec2);
        p311.add(exec3);
        p311.add(exec4);
        p311.add(deco1);
        // ajout des objets graphique des requetes pour la fenetre local
        p312.add(exec1);
        p312.add(exec2);
        p312.add(exec3);
        p312.add(exec4);
        p312.add(deco2);
        // ajout de la requete sql manuel
        p32.add(nameSQL);
        p32.add(requeteSQL);
        p32.add(SQL);

        // disposition geographique des panneaux
        this.setVisible(true);

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

    }

    /**
     * Méthode privée qui initialise la liste des requetes de MAJ
     */
    private void remplirRequetesMaj() {

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
     *
     * @param nomTable
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

    // Fonction qui permet de nous retourner un String si l'utilisateur veut faire une recherche
    public String rechercher() {
        // récupérer le texte de la requête
        String requetetable2 = RequeteTable2.getText();
        String requetechamp = RequeteChamp.getText();
        String requetetable = RequeteTable.getText();
        String requetec1 = RequeteC1.getText();
        String requetec2 = RequeteC2.getText();
        String jointc1 = JointC1.getText();
        String jointc2 = JointC2.getText();
        String requetec3 = RequeteC3.getText();
        String group = Groupement.getText();
        // recupère le choix de l'utilisateur des checkbox
        boolean sum = Somme.isSelected();
        boolean moy = Moyenne.isSelected();
        boolean ct = Count.isSelected();
        // declaration d'un string
        String requeteSelectionnee = null;
        // effacer les résultats
        fenetreRes.removeAll();
        // permet de transformer la requete de l'utilisateur en SQL avec blindage si il s'est trompé
        if (!"".equals(requetechamp) || (moy == true && ct == true) || (moy == true && sum == true) || (ct == true && sum == true)) {
            if (moy == true && ct == false && sum == false && !"".equals(group)) {
                requeteSelectionnee = "SELECT " + group + ",AVG(" + requetechamp + ") ";

            } else if (ct == true && !"".equals(group)) {
                requeteSelectionnee = "SELECT " + group + ",COUNT(" + requetechamp + ") ";

            } else if (sum == true && ct == false && moy == false && "".equals(group)) {
                requeteSelectionnee = "SELECT " + group + "SUM(" + requetechamp + ") ";

            } else if (sum == true && ct == false && moy == false && !"".equals(group)) {
                requeteSelectionnee = "SELECT " + group + ",SUM(" + requetechamp + ") ";

            } else if (moy == true && ct == false && sum == false && "".equals(group)) {
                requeteSelectionnee = "SELECT " + group + "AVG(" + requetechamp + ") ";

            } else if (ct == true && moy == false && sum == false && "".equals(group)) {
                requeteSelectionnee = "SELECT " + group + "COUNT(" + requetechamp + ") ";

            } else if (ct == false && moy == false && sum == false) {
                requeteSelectionnee = "SELECT " + requetechamp;

            } else {
                // affiche le message d'erreur si requete sql fausse
                error.showMessageDialog(null, "Impossible de faire cete requete", "Message d'erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (!"".equals(requetetable)) {
            requeteSelectionnee = requeteSelectionnee + " FROM " + requetetable;
            if (!"".equals(requetetable2)) {
                requeteSelectionnee = requeteSelectionnee + " INNER JOIN " + requetetable2 + " ON " + requetetable + "." + jointc1 + " = " + requetetable2 + "." + jointc2;;
            }
        }
        if (!"".equals(requetec1)) {
            requeteSelectionnee = requeteSelectionnee + " WHERE " + requetec1;
            if (!"".equals(requetec2)) {
                requeteSelectionnee = requeteSelectionnee + " AND " + requetec2;
                if (!"".equals(requetec3)) {
                    requeteSelectionnee = requeteSelectionnee + " AND " + requetec3;
                }
            }
        }

        if (!"".equals(group)) {
            requeteSelectionnee = requeteSelectionnee + " GROUP BY " + group;
        }
        requeteSelectionnee = requeteSelectionnee + ";"; // permet de me tre un ; à la toutes fin de la requetes
        System.out.println(requeteSelectionnee);
        return (requeteSelectionnee); // retourne la requete
    }

    // Fonction qui permet de nous retourner un String si l'utilisateur veut faire une modification
    public String modifier() {
        // récupérer le texte de la requête
        String Modtable = ModTable.getText();
        String Modc1 = ModC1.getText();
        String Modc2 = ModC2.getText();
        String mod1 = Mod1.getText();
        String mod2 = Mod2.getText();
        String mod3 = Mod3.getText();
        // declaration d'un String
        String requeteSelectionnee = null;
        // effacer les résultats
        fenetreRes.removeAll();
        if (!"".equals(ModTable)) {
            requeteSelectionnee = "UPDATE " + Modtable;
        }
        if (!"".equals(mod1)) {
            requeteSelectionnee = requeteSelectionnee + " SET " + mod1;
            if (!"".equals(mod2)) {
                requeteSelectionnee = requeteSelectionnee + ", " + mod2;
                if (!"".equals(mod3)) {
                    requeteSelectionnee = requeteSelectionnee + ", " + mod3;
                }
            }
            if (!"".equals(Modc1)) {
                requeteSelectionnee = requeteSelectionnee + " WHERE " + Modc1;
                if (!"".equals(Modc2)) {
                    requeteSelectionnee = requeteSelectionnee + " AND " + Modc1;
                }
            }
        }
        requeteSelectionnee = requeteSelectionnee + " ;";
        System.out.println(requeteSelectionnee);
        return requeteSelectionnee;// retourne la requete
    }

    // Fonction qui permet de nous retourner un String si l'utilisateur veut supprimer
    public String supprimer() {
        // récupérer le texte de la requête
        String delt = DelT.getText();
        String delc1 = DelC1.getText();
        String delc2 = DelC2.getText();
        // Declaration d'un String
        String requeteSelectionnee = null;
        // effacer les résultats
        fenetreRes.removeAll();
        requeteSelectionnee = "DELETE FROM " + delt;
        if (!"".equals(delc1)) {
            requeteSelectionnee = requeteSelectionnee + " WHERE " + delc1;
            if (!"".equals(delc2)) {
                requeteSelectionnee = requeteSelectionnee + " AND " + delc2;
            }
        }
        requeteSelectionnee = requeteSelectionnee + " ;";
        System.out.println(requeteSelectionnee);
        return requeteSelectionnee;// retourne la requete
    }

    // Fonction qui permet de nous retourner un String si l'utilisateur veut ajouter
    public String ajouter() {
        // récupérer le texte de la requête
        String inta = InTa.getText();
        String inc = InC.getText();
        String inv = InV.getText();
        // Declaration d'un String
        String requeteSelectionnee = null;
        // effacer les résultats
        fenetreRes.removeAll();
        requeteSelectionnee = "INSERT INTO " + inta + "(" + inc + ") VALUES(" + inv + ");";
        System.out.println(requeteSelectionnee);
        return requeteSelectionnee; // retourne la requete
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
        Object source = null;
        source = evt.getSource();

        // tester cas de la commande evenementielle
        if (source == connect) {
            ArrayList<String> liste;
            // recupère les information fournis pas l'utilisateur
            String passwdECEString = new String(passwdECETexte.getPassword());
            String passwdBDDString = new String(passwdBDDTexte.getPassword());
            if ((!"".equals(nameECETexte.getText())) && (!"".equals(passwdECEString)) && (!"".equals(loginBDDTexte.getText())) && (!"".equals(passwdBDDString))) {
                try {
                    try {

                        // tentative de connexion si les 4 attributs sont remplis
                        maconnexion = new Connexion(nameECETexte.getText(), passwdECEString,
                                loginBDDTexte.getText(), passwdBDDString);
                        setVisible(false);
                        p3.add(p311);
                        p3.add(p32);
                        // declaration de notre nouvelle fenetre
                        connecc = new JFrame("Connecté");
                        connecc.pack();
                        connecc.setDefaultLookAndFeelDecorated(true);
                        connecc.setExtendedState(Fenetre2.MAXIMIZED_BOTH);
                        connecc.setVisible(true);
                        connecc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        // ajout des panels à la fenetre connecc
                        connecc.add("North", p1);
                        connecc.add("Center", p2);
                        connecc.add("South", p3);

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
                    // message errreur graphique
                    error.showMessageDialog(null, "Les informations saisies sont incorrect", "Message d'erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                error.showMessageDialog(null, "Les informations saisies sont incorrect", "Message d'erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else if (source == local) {
            ArrayList<String> liste;
            if (!"".equals(nameBDDTexte.getText())) {
                try {
                    try {
                        // tentative de connexion si les 4 attributs sont remplis
                        maconnexion = new Connexion(nameBDDTexte.getText(), "root", "");
                        setVisible(false);
                        p3.add(p312);
                        p3.add(p32);
                        // declaration de la fenetre connecl
                        connecl = new JFrame("Connecté");
                        connecl.pack();
                        connecl.setDefaultLookAndFeelDecorated(true);
                        connecl.setExtendedState(Fenetre2.MAXIMIZED_BOTH);
                        connecl.setVisible(true);
                        connecl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        // ajout des panels dans la fenetre
                        connecl.add("North", p1);
                        connecl.add("Center", p2);
                        connecl.add("South", p3);
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
                    //message erreur
                    error.showMessageDialog(null, "la base local est incorect", "Message d'erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                error.showMessageDialog(null, "la base local est incorect", "Message d'erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
        // permete de fermer la session gandalf
        if (source == deco1) {
            setVisible(true);
            connecc.removeNotify();
        }
        //permet de fermer la session local
        if (source == deco2) {
            setVisible(true);
            connecl.removeNotify();
        }
        // permet d'afficher la fenetre Rechercher
        if (source == exec1) {
            // declaration de la fenetre voir
            voir = new JFrame("Rechercher");
            voir.setResizable(false);
            voir.setSize(1000, 210);
            p4.setLayout(new GridLayout(6, 2));
            // ajout des objet dans le panel
            p4.add(Champ);
            p4.add(RequeteChamp);
            p4.add(Table);
            p4.add(RequeteTable);
            p4.add(Table2);
            p4.add(RequeteTable2);
            p4.add(C1);
            p4.add(RequeteC1);
            p4.add(C2);
            p4.add(RequeteC2);
            p4.add(C3);
            p4.add(RequeteC3);
            p4.add(JC1);
            p4.add(JointC1);
            p4.add(JC2);
            p4.add(JointC2);
            p4.add(MOY);
            p4.add(Moyenne);
            p4.add(COUNT);
            p4.add(Count);
            p4.add(SUM);
            p4.add(Somme);
            p4.add(GROUP);
            p4.add(Groupement);
            // ajout du panel et du bouton dans la fenetre
            voir.add("Center", p4);
            voir.add("South", rechercher);
            voir.setVisible(true);
            // effacer les résultats
            fenetreRes.removeAll();
        }
        // permet d'afficher la fenetre Modifier
        if (source == exec2) {
            // Declaration de la fenetre
            modif = new JFrame("Modifier");
            modif.setResizable(false);
            modif.setSize(500, 200);
            modif.setVisible(true);
            p5.setLayout(new GridLayout(6, 2));
            // ajout des objet dans le panel
            p5.add(MCT);
            p5.add(ModTable);
            p5.add(M1);
            p5.add(Mod1);
            p5.add(M2);
            p5.add(Mod2);
            p5.add(M3);
            p5.add(Mod3);
            p5.add(MC1);
            p5.add(ModC1);
            p5.add(MC2);
            p5.add(ModC2);
            // ajout du panel et boutons dans la fenetre
            modif.add("Center", p5);
            modif.add("South", modifier);
            // effacer les résultats
            fenetreRes.removeAll();
        }
        // permet d'afficher la fenetre Supprimer
        if (source == exec3) {
            suppr = new JFrame("Supprimer");
            suppr.setResizable(false);
            suppr.setSize(500, 140);
            suppr.setVisible(true);
            p6.setLayout(new GridLayout(3, 2));
            // ajout des objet dans le panel
            p6.add(DT);
            p6.add(DelT);
            p6.add(DC1);
            p6.add(DelC1);
            p6.add(DC2);
            p6.add(DelC2);
            // ajout du panel et boutons dans la fenetre
            suppr.add("Center", p6);
            suppr.add("South", supprimer);

            // effacer les résultats
            fenetreRes.removeAll();
        }
        // permet d'afficher la fenetre Ajouter
        if (source == exec4) {
            ajout = new JFrame("Ajouter");
            ajout.setResizable(false);
            ajout.setSize(500, 140);
            ajout.setVisible(true);
            p7.setLayout(new GridLayout(3, 2));
            // ajout des objet dans le panel
            p7.add(IT);
            p7.add(InTa);
            p7.add(IC);
            p7.add(InC);
            p7.add(IV);
            p7.add(InV);
            // ajout du panel et boutons dans la fenetre
            ajout.add("Center", p7);
            ajout.add("South", ajouter);

            // effacer les résultats
            fenetreRes.removeAll();
        }
        // permet d'executer la requete SQL Rechercher
        if (source == rechercher) {
            String requeteSelectionnee;
            requeteSelectionnee = rechercher();
            try {
                // afficher les résultats de la requete selectionnee
                if (afficherRes(requeteSelectionnee) != null) {
                    maconnexion.ajouterRequete(requeteSelectionnee);
                    listeDeRequetes.removeAll();
                    afficherRequetes();
                    voir.setVisible(false);
                }
            } catch (SQLException ex) {
                error.showMessageDialog(null, "Requete modifier invalide", "Message d'erreur", JOptionPane.ERROR_MESSAGE);
            }

        }
        // permet d'executer la requete SQL Modifier
        if (source == modifier) {
            String requeteSelectionnee;
            requeteSelectionnee = modifier();
            try {
                maconnexion.executeUpdate(requeteSelectionnee);
                modif.setVisible(false);
            } catch (SQLException ex) {
                Logger.getLogger(Fenetre2.class.getName()).log(Level.SEVERE, null, ex);
                error.showMessageDialog(null, "Requete modifier invalide", "Message d'erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
        // permet d'executer la requete SQL Supprimer
        if (source == supprimer) {
            String requeteSelectionnee;
            requeteSelectionnee = supprimer();
            try {
                maconnexion.executeUpdate(requeteSelectionnee);
                suppr.setVisible(false);
            } catch (SQLException ex) {
                Logger.getLogger(Fenetre2.class.getName()).log(Level.SEVERE, null, ex);
                error.showMessageDialog(null, "Requete supprimer invalide", "Message d'erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
        // permet d'executer la requete SQL Ajouter
        if (source == ajouter) {
            String requeteSelectionnee;
            requeteSelectionnee = ajouter();
            try {
                maconnexion.executeUpdate(requeteSelectionnee);
                ajout.setVisible(false);
            } catch (SQLException ex) {
                Logger.getLogger(Fenetre2.class.getName()).log(Level.SEVERE, null, ex);
                error.showMessageDialog(null, "Requete ajouter inavalide", "Message d'erreur", JOptionPane.ERROR_MESSAGE);
            }

        }
        if (source == SQL) {
            String requeteSelectionnee = requeteSQL.getText(); // récupérer le texte de la requête

            // effacer les résultats
            fenetreRes.removeAll();

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
    public void itemStateChanged(ItemEvent evt
    ) {
        // sélection d'une requete et afficher ses résultats
        if (evt.getSource() == listeDeRequetes) {
            // recuperer la liste des lignes de la requete selectionnee
            String requeteSelectionnee = listeDeRequetes.getSelectedItem();
            try {
                afficherRes(requeteSelectionnee);

            } catch (SQLException ex) {
                Logger.getLogger(Fenetre2.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        } else if (evt.getSource() == listeDeTables) {
            // afficher les lignes de la table sélectionnée
            String nomTable = listeDeTables.getSelectedItem();
            afficherLignes(nomTable);
        }
    }
}
