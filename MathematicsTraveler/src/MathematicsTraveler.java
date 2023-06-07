import extensions.CSVFile;

class MathematicsTraveler extends Program {

    // A implémenter
    // !!
    // !!
    // Lors d'un choix/création/supression de profil, proposez d'annuler l'action.

    void algorithm() {

        println("Menu principal :\nCréer un profil (1).\nCharger un profil (2).\nSupprimer un profil (3).\nQuitter (4).");

        String choixMenu = readString();
        while (!equals(choixMenu, "1") && !equals(choixMenu, "2") && !equals(choixMenu, "3") && !equals(choixMenu, "4")) {
            println("Choix invalide. Veuillez rentrer 1, 2, 3 ou 4.");
            choixMenu = readString();
        }

        while(!equals(choixMenu,"4")){

            if (equals(choixMenu, "1")) {
                Joueur j;
                j = creerProfil();

            } else if (equals(choixMenu, "2")){

                Joueur j = loadJoueur();

                String choixNiveau = choixNiveau(j.completionNiveaux);

                if (equals(choixNiveau,"1")) {

                    boolean resultat = modeFacile(j);
                    if (resultat) {
                        println("Bravo ! Vous avez réussi le mode facile !");
                        miseAJourProfils(j);
                    } else {
                        println("Dommage, vous avez perdu.");
                    }

                } else if(equals(choixNiveau,"2")) {
                    boolean resultat = modeMoyen(j);
                    if (resultat) {
                        println("Bravo ! Vous avez réussi le mode moyen !");
                        miseAJourProfils(j);
                    } else {
                        println("Dommage, vous avez perdu.");
                    }
                } else if(equals(choixNiveau,"3")) {
                    boolean resultat = modeDifficile(j);
                    if (resultat) {
                        println("Bravo ! Vous avez réussi le mode difficile !");
                        miseAJourProfils(j);
                    } else {
                        println("Dommage, vous avez perdu.");
                    }
                } else {
                    println("Vous allez revenir au menu.");
                }

            } else if (equals(choixMenu, "3")) {
                supprimerProfil();
            }

            println("Menu:\nCréer un profil (1).\nCharger un profil (2).\nSupprimer un profil (3).\nQuitter (4).");
            choixMenu = readString();
        }
    }

    /////////////////////////////////////////////////////
    // FONCTIONS EXECUTANT LES ATTAQUES INTERACTIVES
    /////////////////////////////////////////////////////

    void testEstEntier() {
        assertTrue(estEntier("09245"));
        assertTrue(estEntier("5"));
        assertFalse(estEntier(""));
        assertFalse(estEntier("abc"));
        assertFalse(estEntier("*"));
    }

    boolean estEntier(String chaine){
        int i = 0;
        boolean nombre = true;
        if(length(chaine)==0){
            return false;
        }
        while(i<length(chaine) && nombre){
            char c = charAt(chaine,i);
            if(c<'0'|| c>'9'){
                nombre=false;
            }
            i++;
        }
        return nombre;
    }

    boolean soustractionsDefensive(){
        for(int i=0;i<2;i++){
            int x=(int) (random()*800)+100;
            int y=(int) (random()*60)+20;
            println(x +" - " + y +" = ?");
            String reponse=readString();
            while(!estEntier(reponse) || length(reponse)>=4) {
                println("Veuillez rentrer un nombre à trois chiffres ou moins.");
                reponse=readString();
            }
            int rep = stringToInt(reponse);
            if(x-y != rep){
                println("\nDommage! C'est un échec.");
                return false;
            }
            if(i==0){
                println("\nPassons au calcul suivant.");
            }
            if(i==1){
                println("\nBravo, votre défense est une réussite!");
            }
        }
        return true;
    }

    boolean attaqueAdditionnelle(){
        for(int i=0;i<2;i++){
            int x=(int) (random()*20)+40;
            int y=(int) (random()*20)+10;
            println(x +" + " + y +" = ?");
            String reponse=readString();
            while(!estEntier(reponse) || length(reponse)>=3) {
                println("Veuillez rentrer un nombre à deux chiffres ou moins.");
                reponse=readString();
            }
            int rep = stringToInt(reponse);
            if(x+y != rep){
                println("\nDommage! C'est un échec.");
                return false;
            }
            if(i==0){
                println("\nPassons au calcul suivant.");
            }
            if(i==1){
                println("\nBravo, votre attaque est une réussite!");
            }
        }
        return true;
    }
    
    int multiplicationMultiple () {

        int nbAttaque = 0;

        for(int i=0;i<3;i=i+1) {
            int x = (int) (random()*8) +2;
            int y = (int)(random()*8) +2;

            println(x + " * " + y +" = ?");
            String reponse = readString();
            while (!estEntier(reponse) || length(reponse)>=3) {
                println("Veuillez rentrer un nombre à deux chiffres ou moins.");
                reponse = readString();
            }
            int rep = stringToInt(reponse);
            if (x*y == rep) {
                nbAttaque=nbAttaque+1;
            }

        }

        return nbAttaque;
    }

    boolean divisions(){

        for(int i=0;i<2;i++){

            int x=(int) (random()*88)+8;
            int y=(int) (random()*20)+3;
            while (x%y!=0) {
                x=(int) (random()*88)+8;
                y=(int) (random()*20)+3;
            }
            println(x + " / " + y + " = ?");

            String reponse=readString();
            while (!estEntier(reponse) || length(reponse)>=3) {
                println("Veuillez rentrer un nombre à deux chiffres ou moins.");
                reponse=readString();
            }
            int rep = stringToInt(reponse);

            if(x/y != rep){
                println("\nDommage! C'est un échec.");
                return false;
            }
            if(i==0){
                println("\nPassons au calcul suivant.");
            }
            if(i==1){
                println("\nBravo, votre attaque est une réussite!");
            }
        }
        return true;
    }

    boolean allIn(){
        for(int i=0;i<4;i++){
            if(i == 0){
                int x=(int) (random()*800)+100;
                int y=(int) (random()*60)+20;
                println(x + " - " + y + " = ?");
                String reponse = readString();
                while(!estEntier(reponse) || length(reponse)>=4) {
                    println("Veuillez rentrer un nombre à trois chiffres ou moins.");
                    reponse = readString();
                }
                int rep = stringToInt(reponse);
                if (x-y != rep) {
                    return false;
                }else{
                    println("\nBravo, votre calcul est une réussite!");
                    println("\nPassons au calcul suivant.");
                }
            }
            if(i == 1){
                int x=(int) (random()*20)+40;
                int y=(int) (random()*20)+10;
                println(x + " + " + y + " = ?");
                String reponse = readString();
                while(!estEntier(reponse) || length(reponse)>=3) {
                    println("Veuillez rentrer un nombre à deux chiffres ou moins.");
                    reponse=readString();
                }
                int rep = stringToInt(reponse);
                if (x+y != rep) {
                    return false;
                }else{
                    println("\nBravo, votre calcul est une réussite!");
                    println("\nPassons au calcul suivant.");
                }
            }
            if(i == 2){
                int x = (int) (random()*8) +2;
                int y = (int)(random()*8) +2;

                println(x + " * " + y +" = ?");
                String reponse = readString();
                while (!estEntier(reponse) || length(reponse)>=3) {
                    println("Veuillez rentrer un nombre à deux chiffres ou moins.");
                    reponse = readString();
                }
                int rep = stringToInt(reponse);
                if (x*y != rep) {
                    return false;
                }else{
                    println("\nBravo, votre calcul est une réussite!");
                    println("\nPassons au calcul suivant.");
                }
            }
            if(i == 3){
                int x=(int) (random()*88)+8;
                int y=(int) (random()*20)+3;
                while (x%y!=0) {
                    x=(int) (random()*88)+8;
                    y=(int) (random()*20)+3;
                }
                println(x + " / " + y + " = ?");

                String reponse=readString();
                while (!estEntier(reponse) || length(reponse)>=3) {
                    println("Veuillez rentrer un nombre à deux chiffres ou moins.");
                    reponse=readString();
                }
                int rep = stringToInt(reponse);
                if (x/y != rep) {
                    return false;
                }else{
                    println("\nBravo, votre attaque est une réussite!");
                }
            }
        }
        return true;
    }

    //////////////////////////////////////////////////////////
    // FONCTIONS EXECUTANT LES MECANIQUES DES COMBATS
    //////////////////////////////////////////////////////////

    void afficherInterface(int pvJ, int pvE, int tour, Ennemi e, Competence[] competences) {

        println("Tour : " + tour);
        print("PV Joueur : ");
        for (int i=0; i<pvJ; i++) {
            print("* ");
        }
        print("              ");
        print("PV "+ e.nom + " : ");
        for (int i=0; i<pvE; i++) {
            print("* ");
        }
        println();
        println();
        println();

        if(e.frequenceAttaqueForte != 0) {
            if(tour%e.frequenceAttaqueForte == 0) {
                println("L'ennemi se prépare à faire une attaque dévastatrice !");
                println();
            }
        }

        for (int i=0; i<length(competences); i++) {
            if (competences[i].tpsAvantUtilisation != 0) {
                print("( " + (i+1) + " - " + competences[i].nom + " ) Temps avant utilisation : " + competences[i].tpsAvantUtilisation + " tour(s).\n");
            } else  {
                print((i+1) + " - " + competences[i].nom + "\n");
            }
            
        }
        println();
    }

    // Execute l'attaque demandée, renvoie un positif correpondant aux dégats du joueur, ou -1 pour une attaque ennemie, ou 0 pour une défense parfaite.
    int lancerAttaque(int choix){
        if (choix==1) {
            boolean resultat=soustractionsDefensive();
            if (resultat) {
                return 0;
            } else {
                return -1;
            }
        } else if (choix==2){
            boolean resultat=attaqueAdditionnelle();
            if (resultat) {
                return 1;
            } else {
                return -1;
            }
        }else if(choix==3){
            int resultat =multiplicationMultiple();
            if(resultat==0){
                return -1;
            }else{
                return resultat;
            }
        }else if(choix==4){
            boolean resultat = divisions();
            if (resultat) {
                return 2;
            } else {
                return -1;
            }
        }else{
            boolean resultat = allIn();
            if (resultat) {
                return 5;
            } else {
                return -1;
            }
        }
    }

    void miseAJourPv(int[] pvs, int attaque, Ennemi e, int tour) {
        if (attaque>0) {
            pvs[1] = pvs[1] - attaque;
            if(e.frequenceAttaqueForte != 0) {
                if(tour%e.frequenceAttaqueForte == 0) {
                    pvs[0] = pvs[0] - (abs(attaque)*e.force);
                }
            }
        } else if (attaque<0) {
            if(e.frequenceAttaqueForte != 0) {
                if(tour%e.frequenceAttaqueForte == 0) {
                    pvs[0] = pvs[0] - (abs(attaque)*e.force*2);
                } else {
                    pvs[0] = pvs[0] - (abs(attaque)*e.force);
                }
            } else {
                pvs[0] = pvs[0] - (abs(attaque)*e.force);
            }
        }
    }

    void miseAJourProfils(Joueur j){
        Joueur[] listeJoueurs = loadListeJoueurs("../ressources/joueurs.csv", 0);
        String[] profils = new String[length(listeJoueurs)];
        for (int i=0; i<length(listeJoueurs); i++) {
            profils[i] = listeJoueurs[i].nom;
        }
        int m=numeroProfil(j.nom,profils)+1;
        CSVFile joueurs = loadCSV("../ressources/joueurs.csv");
        String[][] newCSV = new String[length(listeJoueurs)+1][10];

        for (int k=0; k<rowCount(joueurs); k++) {
            for (int l=0; l<columnCount(joueurs); l++) {
                newCSV[k][l] = getCell(joueurs, k, l);
            }
        }
        if(nbCompetencesDebloquees(j.competenceDebloquee)==3){
            j.pvMax=5;
            j.completionNiveaux[0]=true;
            j.competenceDebloquee[3]=true;
            newCSV[m][1] = "5";
            newCSV[m][2] = "true";
            newCSV[m][8] = "true";
            println("Vous disposez d'une nouvelle compétence et venez de débloquer une nouvelle difficulté");
        }else if(nbCompetencesDebloquees(j.competenceDebloquee)==4){
            j.pvMax=7;
            j.completionNiveaux[1]=true;
            j.competenceDebloquee[4]=true;
            newCSV[m][1] = "7";
            newCSV[m][3] = "true";
            newCSV[m][9] = "true";
            println("Vous disposez d'une nouvelle compétence et venez de débloquer une nouvelle difficulté");
        }else{
            j.pvMax=9;
            j.completionNiveaux[2]=true;
            newCSV[m][1] = "9";
            newCSV[m][4] = "true";
            
            println("Vous disposez de toutes les compétences disponibles.");
        }
        saveCSV(newCSV, "../ressources/joueurs.csv");
    }

    boolean lancerCombat(Joueur j, Ennemi e) {

        int pvJ = j.pvMax;
        int pvE = e.pvMax;
        int[] pvs = new int[]{pvJ,pvE};

        Competence[] competences = initialiserCompetences(j.competenceDebloquee);

        int tour = 1;
        while(pvs[0] > 0 && pvs[1] > 0) {
            afficherInterface(pvs[0], pvs[1], tour, e, competences);
            String choix = readString();
            while (length(choix)!=1 || !estEntier(choix) || stringToInt(choix)<0 || stringToInt(choix)>nbCompetencesDebloquees(j.competenceDebloquee) || competences[stringToInt(choix)-1].tpsAvantUtilisation !=0) {
                println("Veuillez rentrer un chiffre correct.");
                choix = readString();
            }
            int choixChiffre = stringToInt(choix);
            competences[choixChiffre-1].tpsAvantUtilisation = competences[choixChiffre-1].cooldown+1;
            int attaque = lancerAttaque(choixChiffre);

            miseAJourPv(pvs, attaque, e, tour);
            tour++;

            for (int i=0; i<length(competences); i++) {
                if (competences[i].tpsAvantUtilisation>0) {
                    competences[i].tpsAvantUtilisation = competences[i].tpsAvantUtilisation-1;
                }
            }
            
            print("\033[H\033[2J");
        }
        if(pvs[1] <= 0 && pvs[0] > 0){
            return true;
        }
        return false;
    }

    int nbCompetencesDebloquees(boolean[] competenceDebloquee){
        int nbcomp=0;
        for (int i=0; i<length(competenceDebloquee); i++) {
            if (competenceDebloquee[i]) {
                nbcomp++;
            }
        }
        return nbcomp;
    }

    Competence[] initialiserCompetences (boolean[] competenceDebloquee) {
        Competence[] competences = new Competence[nbCompetencesDebloquees(competenceDebloquee)];

        CSVFile compCSV = loadCSV("../ressources/competences.csv");

        for (int i=0; i<length(competences); i++) {
            String nom = getCell(compCSV, i+1, 0);
            int puissance = stringToInt(getCell(compCSV, i+1, 1));
            int cooldown = stringToInt(getCell(compCSV, i+1, 2));
            int tpsAvantUtilisation = stringToInt(getCell(compCSV, i+1, 3));
            Competence currentCompetence = newCompetence(nom, puissance, cooldown, tpsAvantUtilisation);
            competences[i] = currentCompetence;
        }

        return competences;
    }

    String choixNiveau(boolean[] completionNiveaux) {
        println("À quel niveau souhaitez vous jouer ?");

        println("Facile (1)");

        if (completionNiveaux[0]) {
            println("Moyen (2)");
        } else {
            println("( Moyen (2) ) Bloqué.");
        }

        if (completionNiveaux[1]) {
            println("Difficile (3)");
        } else {
            println("( Difficile (3) ) Bloqué.");
        }

        println("Pour revenir au menu, entrez 4.");

        String choixNiveau = readString();
        while (!equals(choixNiveau, "1") && !equals(choixNiveau, "2") && !equals(choixNiveau, "3") && !equals(choixNiveau, "4") ||((!completionNiveaux[0] && equals(choixNiveau, "2")) || (!completionNiveaux[1] && equals(choixNiveau, "3")))) {
            if ((!completionNiveaux[0] && equals(choixNiveau, "2")) || (!completionNiveaux[1] && equals(choixNiveau, "3"))) {
                println("Ce niveau est bloqué, veuillez réussir le niveau précédent d'abord.");
            } else {
                println("Choix invalide, veuillez rentrer 1, 2 ou 3.");
            }
            choixNiveau = readString();
        }

        return choixNiveau;
    }

    boolean modeFacile (Joueur j){
        println("Le premier combat va commencer !");
        Ennemi e = loadEnnemi("Slime");
        boolean resultat1 = lancerCombat(j, e);
        if (resultat1) {
            println("Le second combat va commencer !");
            e = loadEnnemi("Gobelin");
            boolean resultat2 = lancerCombat(j, e);
            if (resultat2) {
                println("Le dernier combat va commencer !");
                e = loadEnnemi("Concombrageur");
                boolean resultat3 = lancerCombat(j, e);
                if(resultat3){
                    return true;
                }
            }
        }                   
        return false;
    }

    boolean modeMoyen (Joueur j){
        println("Le premier combat va commencer !");
        Ennemi e = loadEnnemi("Concombrageur");
        boolean resultat1 = lancerCombat(j, e);
        if (resultat1) {
            println("Le second combat va commencer !");
            e = loadEnnemi("Lièvrecorne");
            boolean resultat2 = lancerCombat(j, e);
            if (resultat2) {
                println("Le dernier combat va commencer !");
                e = loadEnnemi("Chimère");
                boolean resultat3 = lancerCombat(j, e);
                if(resultat3){
                    return true;
                }
            }
        }                   
        return false;
    }

    boolean modeDifficile (Joueur j){
        println("Le premier combat va commencer !");
        Ennemi e = loadEnnemi("Chimère");
        boolean resultat1 = lancerCombat(j, e);
        if (resultat1) {
            println("Le second combat va commencer !");
            e = loadEnnemi("Golem");
            boolean resultat2 = lancerCombat(j, e);
            if (resultat2) {
                println("Le dernier combat va commencer !");
                e = loadEnnemi("Dragon noir");
                boolean resultat3 = lancerCombat(j, e);
                if(resultat3){
                    return true;
                }
            }
        }                   
        return false;
    }
    
    ////////////////////////////////////////////////////////////////////////
    // INITIALISATION DE VARIABLES POUR LES TYPES QUE NOUS AVONS CREE
    ////////////////////////////////////////////////////////////////////////
    
    Joueur newJoueur(String nom, int pvMax, boolean[] completionNiveaux, boolean[] competenceDebloquee) {
        Joueur j = new Joueur();
        j.nom = nom;
        j.pvMax = pvMax;
        j.completionNiveaux = completionNiveaux;
        j.competenceDebloquee = competenceDebloquee;
        return j;
    }

    Ennemi newEnnemi(String nom, int pvMax, int force, int frequenceAttaqueForte) {
        Ennemi e = new Ennemi();
        e.nom = nom;
        e.pvMax = pvMax;
        e.force = force;
        e.frequenceAttaqueForte = frequenceAttaqueForte;
        return e;
    }

    Competence newCompetence(String nom, int puissance, int cooldown, int tpsAvantUtilisation) {
        Competence e = new Competence();
        e.nom = nom;
        e.puissance = puissance;
        e.cooldown = cooldown;
        e.tpsAvantUtilisation = tpsAvantUtilisation;
        return e;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    // FONCTIONS SERVANT A CHARGER DES PROFILS OU DES ENNEMIS DEPUIS DES FICHIERS CSV
    //////////////////////////////////////////////////////////////////////////////////////

    void displayCSV(String nomFichier) {
        CSVFile csv = loadCSV(nomFichier);
        for (int i=0; i<rowCount(csv); i++) {
            for (int j=0; j<columnCount(csv); j++) {
                print(getCell(csv, i, j) + " ; ");
            }
            println();
        }
    }

    // Charge la liste des profils de joueurs présent dans le fichier CSV demandé. Permet également de rajouter une case au besoin pour créer un nouveau profil.
    Joueur[] loadListeJoueurs(String nomFichier, int casesSuppl) {
        CSVFile joueurs = loadCSV(nomFichier);
        Joueur[] joueurList = new Joueur[rowCount(joueurs) + casesSuppl - 1];
        for (int i=1; i<rowCount(joueurs); i++) {
            String nom = getCell(joueurs, i, 0);
            int pvMax = stringToInt(getCell(joueurs, i, 1));
            boolean[] completionNiveaux = new boolean[3];
            for (int j=0; j<length(completionNiveaux); j++) {
                //Boolean.parseBoolean extrait la valeur booléenne d'un String en fonction de s'il vaut "true" ou non (ignore la casse).
                completionNiveaux[j] = Boolean.parseBoolean(getCell(joueurs, i, 2+j));
            }
            boolean[] competenceDebloquee = new boolean[5];
            for (int j=0; j<length(competenceDebloquee); j++) {
                competenceDebloquee[j] = Boolean.parseBoolean(getCell(joueurs, i, 5+j));
            }
            Joueur currentJoueur = newJoueur(nom, pvMax, completionNiveaux, competenceDebloquee);
            joueurList[i-1] = currentJoueur;
        }
        return joueurList;
    }

    // Charge une variable de type Joueur en cherchant par le nom passé en paramètre dans la liste également passée en paramètre.
    Joueur loadJoueur() {

        Joueur[] listeJoueurs = loadListeJoueurs("../ressources/joueurs.csv", 0);

        println("Liste des profils existants :");
        String[] profils = new String[length(listeJoueurs)];
        for (int i=0; i<length(listeJoueurs); i++) {
            profils[i] = listeJoueurs[i].nom;
            println(listeJoueurs[i].nom);
        }

        println("Veuillez rentrer votre nom de profil.");
        String nom = readString();
        while (numeroProfil(nom, profils) == -1) {
            println("Ce nom n'est pas présent dans les profils existants. Veuillez réessayer.");
            nom = readString();
        }
        return listeJoueurs[numeroProfil(nom, profils)];
    }

    // Trouve l'indice de profils correpondant à nom, s'il n'est pas trouvé renvoie -1.
    int numeroProfil(String nom, String[] profils) {
        for (int i=0; i<length(profils); i++) {
            if (equals(nom, profils[i])) {
                return i;
            }
        }
        return -1;
    }   

    // Fonction ajoutant un profil à la base de données des joueurs. Remplace le CSV précédent par un nouveau.
    Joueur creerProfil() {
        
        Joueur[] listeJoueurs = loadListeJoueurs("../ressources/joueurs.csv", 1);

        println("Liste des profils déjà existants :");
        String[] profils = new String[length(listeJoueurs)];
        for (int i=0; i<length(listeJoueurs)-1; i++) {
            profils[i] = listeJoueurs[i].nom;
            println(listeJoueurs[i].nom);
        }

        println("Veuillez rentrer votre nom de profil.");
        String nom = readString();
        while (numeroProfil(nom, profils) != -1) {
            println("Ce nom est déjà présent dans les profils existants. Veuillez entrer un autre nom.");
            nom = readString();
        }

        boolean[] completionNiveaux = new boolean[]{false, false, false};
        boolean[] competenceDebloquee = new boolean[]{true, true, true, false, false};
        Joueur nouveauProfil = newJoueur(nom, 3, completionNiveaux, competenceDebloquee);

        listeJoueurs[length(listeJoueurs)-1] = nouveauProfil;

        CSVFile joueurs = loadCSV("../ressources/joueurs.csv");
        String[][] newCSV = new String[length(listeJoueurs)+1][10];

        for (int i=0; i<rowCount(joueurs); i++) {
            for (int j=0; j<columnCount(joueurs); j++) {
                newCSV[i][j] = getCell(joueurs, i, j);
            }
        }
        newCSV[length(listeJoueurs)][0] = nom;
        newCSV[length(listeJoueurs)][1] = "3";
        newCSV[length(listeJoueurs)][2] = "false";
        newCSV[length(listeJoueurs)][3] = "false";
        newCSV[length(listeJoueurs)][4] = "false";
        newCSV[length(listeJoueurs)][5] = "true";
        newCSV[length(listeJoueurs)][6] = "true";
        newCSV[length(listeJoueurs)][7] = "true";
        newCSV[length(listeJoueurs)][8] = "false";
        newCSV[length(listeJoueurs)][9] = "false";
        
        saveCSV(newCSV, "../ressources/joueurs.csv");
        return nouveauProfil;
    }

    void supprimerProfil(){
        Joueur[] listeJoueurs = loadListeJoueurs("../ressources/joueurs.csv", 1);
        println("Liste des profils déjà existants :");
        String[] profils = new String[length(listeJoueurs)];
        for (int i=0; i<length(listeJoueurs)-1; i++) {
            profils[i] = listeJoueurs[i].nom;
            println(listeJoueurs[i].nom);
        }

        println("Veuillez rentrer le nom du profil à supprimer.");
        String nom = readString();
        while (numeroProfil(nom, profils) == -1) {
            println("Ce nom n'est pas présent dans les profils existants. Veuillez entrer un nom présent dans la liste.");
            nom = readString();
        }
        int nbnom=numeroProfil(nom,profils);
        CSVFile joueurs = loadCSV("../ressources/joueurs.csv");
        String[][] transiCSV = new String[length(listeJoueurs)][10];
        for (int i=0; i<rowCount(joueurs); i++) {
            for (int j=0; j<columnCount(joueurs); j++) {
                transiCSV[i][j] = getCell(joueurs, i, j);
            }
        }
        for (int i=nbnom+1; i<rowCount(joueurs)-1; i++) {
            for (int j=0; j<columnCount(joueurs); j++) {
                transiCSV[i][j] = transiCSV[i+1][j];
            }
        }
        String[][] newCSV = new String[length(listeJoueurs)-1][10];
        for (int i=0; i<length(listeJoueurs)-1; i++) {
            for (int j=0; j<columnCount(joueurs); j++) {
                newCSV[i][j] = transiCSV[i][j];
            }
        }

        saveCSV(newCSV, "../ressources/joueurs.csv");
    }

    // Charge la liste des ennemis présent dans le fichier CSV demandé.
    Ennemi[] loadListeEnnemi(String nomFichier) {
        CSVFile ennemis = loadCSV(nomFichier);
        Ennemi[] ennemiList = new Ennemi[rowCount(ennemis) - 1];
        for (int i=1; i<rowCount(ennemis); i++) {
            String nom = getCell(ennemis, i, 0);
            int pvMax = stringToInt(getCell(ennemis, i, 1));
            int force = stringToInt(getCell(ennemis, i, 2));
            int frequenceAttaqueForte = stringToInt(getCell(ennemis, i, 3));
            Ennemi currentEnnemi = newEnnemi(nom, pvMax, force, frequenceAttaqueForte);
            ennemiList[i-1] = currentEnnemi;
        }
        return ennemiList;
    }

    // Charge une variable de type Ennemi en cherchant par le nom passé en paramètre dans la liste également passée en paramètre.
    Ennemi loadEnnemi(String nom) {

        Ennemi[] listeEnnemi = loadListeEnnemi("../ressources/ennemis.csv");

        // Crée un tableau de String contenant les noms des ennemis existants.
        String[] bestiaire = new String[length(listeEnnemi)];
        for (int i=0; i<length(listeEnnemi); i++) {
            bestiaire[i] = listeEnnemi[i].nom;
        }

        // Boucle infinie si l'algo essaie de charger un ennemi n'existant pas, on règle alors le problème.
        while (numeroBestiaire(nom, bestiaire) == -1) {
            println("Ce nom n'est pas présent dans le bestiaire. Veuillez réessayer.");
        }
        return listeEnnemi[numeroProfil(nom, bestiaire)];
    }

    // Trouve l'indice du bestiaire correpondant à nom, s'il n'est pas trouvé renvoie -1.
    int numeroBestiaire(String nom, String[] bestiaire) {
        for (int i=0; i<length(bestiaire); i++) {
            if (equals(nom, bestiaire[i])) {
                return i;
            }
        }
        return -1;
    }    

}

// !!
// !!
// !!
// save à implementer en fin de niveau, mettre à jour completionNiveaux et competenceDebloquee

 
 
    /////// TRACE D'ÉXECUTION ///////
    
    /*



    nouveau joueur OU charger profil
    if (nouveau joueur) {
        String nom = readString();
        creerProfil(nom);
    } else {
        afficherProfilsExistants();
        String nom = readString();
        chargerProfil(nom);
    }


    while(choix!=0) {

    afficherMenu();
    choisirDifficultéOuQuitter(); 
        
        if(combat) {

        }
        lancerPartie(difficulté, nomJoueur);
        int numeroCombat = 1;
        while(numeroCombat<=4) {
            lancerCombat(numeroCombat);
            int tour = 1;
            initialiserCombat();
                    while(pvJoueur > 0 && pvEnnemi > 0) {
                            afficherInterface();
                            afficherPv();
                            if(tour%ennemi.frequenceAttaqueForte == 0) {
                                afficherMessageAlerte();
                            }
                            choixCompetence();
                            lancerAttaque();
                            miseAJourPv();
                            tour++;
                    }
            numeroCombat++;
            if(numeroCombat<=3)
            continuerOuSauvegarder?();
        }

    }
        


    */