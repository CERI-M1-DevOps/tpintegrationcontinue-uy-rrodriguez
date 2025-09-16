package liste;

/**
 * Représente une structure dynamique qui peut stocker et manipuler une collection ordonnée d'entiers de type `long`.
 */
public class ListeSimple {
    /**
     * La taille de la liste.
     */
    private long size;
    /**
     * Le noeud en tête de liste.
     */
    Noeud tete;

    /**
     * Retourne la taille actuelle de la liste.
     * @return le nombre d'éléments dans la liste.
     */
    public long getSize() {
        return size;
    }

    /**
     * Ajoute un entier à la tête de la liste.
     * @param element L'entier à ajouter.
     */
    public void ajout(int element) {
        tete = new Noeud(element, tete);
        size++;
    }

    /**
     * Modifie la première occurrence d'un élément spécifié dans la liste avec une nouvelle valeur. Si l'élément n'existe pas dans la liste, aucune action n'est prise.
     * @param element L'élément à modifier.
     * @param nouvelleValeur La nouvelle valeur pour l'élément.
     */
    public void modifiePremier(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null && courant.getElement() != element)
            courant = courant.getSuivant();
        if (courant != null)
            courant.setElement(nouvelleValeur);
    }

    /**
     * Modifie toutes les occurrences d'un élément spécifié dans la liste avec une nouvelle valeur. Si l'élément n'existe pas dans la liste, aucune action n'est prise.
     * @param element L'élément à modifier.
     * @param nouvelleValeur La nouvelle valeur pour l'élément.
     */
    public void modifieTous(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null) {
            if (courant.getElement() == element)
                courant.setElement(nouvelleValeur);
            courant = courant.getSuivant();
        }
    }

    /**
     * Transforme la liste en une représentation de chaîne de caractères.
     * @return Une représentation de chaîne de caractères de la liste.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("ListeSimple(");
        Noeud n = tete;
        while (n != null) {
            sb.append(n);
            n = n.getSuivant();
            if (n != null)
                sb.append(", ");
        }
        sb.append(")");
        return sb.toString();
    }

    /**
     * Supprime le premier élément spécifié dans la liste. Si l'élément n'existe pas dans la liste, aucune action n'est prise.
     * @param element L'élément à supprimer.
     */
    public void supprimePremier(Object element) {
        if (tete != null) {
            if (tete.getElement() == element) {
                tete = tete.getSuivant();
                size--;
                return;
            }
            Noeud precedent = tete;
            Noeud courant = tete.getSuivant();
            while (courant != null && courant.getElement() != element) {
                precedent = precedent.getSuivant();
                courant = courant.getSuivant();
            }
            if (courant != null) {
                precedent.setSuivant(courant.getSuivant());
                size--;
            }
        }
    }

    /**
     * Supprime toutes les occurrences d'un élément spécifié dans la liste. Si l'élément n'existe pas dans la liste, aucune action n'est prise.
     * @param element L'élément à supprimer.
     */
    public void supprimeTous(int element) {
       tete = supprimeTousRecurs(element, tete);
    }

    /**
     * Méthode auxiliaire et récursive pour supprimer toutes les occurrences d'un élément spécifié dans la liste.
     * @param element L'élément à supprimer.
     * @param tete La tête de la liste.
     * @return La nouvelle tête de la liste après la suppression des éléments spécifiés.
     */
    public Noeud supprimeTousRecurs(Object element, Noeud tete) {
        if (tete != null) {
            Noeud suiteListe = supprimeTousRecurs(element, tete.getSuivant());
            if (tete.getElement() == element) {
                size--;
                return suiteListe;
            } else {
                tete.setSuivant(suiteListe);
                return tete;
            }
        } else return null;
    }

    /**
     * Retourne le noeud avant-dernier de la liste (si la liste n'est pas vide).
     * @return Le noeud avant-dernier de la liste ou null si la liste est vide.
     */
    public Noeud getAvantDernier() {
        if (tete == null || tete.getSuivant() == null)
            return null;
        else {
            Noeud courant = tete;
            Noeud suivant = courant.getSuivant();
            while (suivant.getSuivant() != null) {
                courant = suivant;
                suivant = suivant.getSuivant();
            }
            return courant;
        }
    }

    /**
     * Réalise une inversion dans l'ordre des éléments dans la liste. La liste est modifiée sur place.
     */
    public void inverser() {
        Noeud precedent = null;
        Noeud courant = tete;
        while (courant != null) {
            Noeud next = courant.getSuivant();
            courant.setSuivant(precedent);
            precedent = courant;
            courant = next;
        }
        tete = precedent;
    }

    /**
     * Retourne le noeud précédent d'un noeud spécifié dans la liste. On assume que la liste est non-vide et que le noeud donné appartient à la liste.
     * @param r Le noeud dont on veut trouver le précédent.
     * @return Le noeud précédant de `r` ou null si `r` est à la tête de la liste.
     */
    public Noeud getPrecedent(Noeud r) {
    // la liste n'est pas vide puisqu'on transmet un Node de la liste et le Node existe obligatoirement
        Noeud precedent = tete;
        Noeud courant = precedent.getSuivant();
        while (courant != r) {
            precedent = courant;
            courant = courant.getSuivant();
        }
        return precedent;
    }

    /**
     * Échange deux noeuds spécifiés dans la liste. Si les deux noeuds sont le même objet, aucune action n'est prise.
     * @param r1 Le premier noeud à échanger.
     * @param r2 Le deuxième noeud à échanger.
     */
    public void echanger(Noeud r1, Noeud r2) {
        if (r1 == r2)
            return;
        Noeud precedentR1;
        Noeud precedentR2;
        if (r1 != tete && r2 != tete) {
            precedentR1 = getPrecedent(r1);
            precedentR2 = getPrecedent(r2);
            precedentR1.setSuivant(r2);
            precedentR2.setSuivant(r1);
        } else if (r1 == tete) {
            precedentR2 = getPrecedent(r2);
            precedentR2.setSuivant(tete);
            tete = r2;
        }
        else {  // r2 == tete
            precedentR1 = getPrecedent(r1);
            precedentR1.setSuivant(tete);
            tete = r1;
        }
        Noeud temp = r2.getSuivant();
        r2.setSuivant(r1.getSuivant());
        r1.setSuivant(temp);
    }

}
