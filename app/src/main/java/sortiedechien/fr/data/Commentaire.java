package sortiedechien.fr.data;

/**
 * Created by guillaume on 12/12/16.
 */

public class Commentaire {

    private int note;
    private String commentaire;

    public Commentaire(int note, String commentaire) {
        this.note = note;
        this.commentaire = commentaire;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }
}
