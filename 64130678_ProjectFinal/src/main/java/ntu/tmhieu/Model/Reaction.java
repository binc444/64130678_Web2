package ntu.tmhieu.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "reactions")
public class Reaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reactionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    @Column(name = "reaction_type", nullable = false, length = 20)
    private String reactionType;

    public Reaction() {
    }

    public Reaction(Article article, String reactionType) {
        this.article = article;
        this.reactionType = reactionType;
    }

    public Integer getReactionId() {
        return reactionId;
    }

    public void setReactionId(Integer reactionId) {
        this.reactionId = reactionId;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getReactionType() {
        return reactionType;
    }

    public void setReactionType(String reactionType) {
        this.reactionType = reactionType;
    }
}