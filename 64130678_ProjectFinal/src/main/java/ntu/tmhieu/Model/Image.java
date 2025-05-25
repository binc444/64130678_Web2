package ntu.tmhieu.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Integer imageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    @Column(name = "image_url", nullable = false, length = 255)
    private String imageUrl;

    @Column(name = "is_thumbnail", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean isThumbnail;

    // Getters and Setters
    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isThumbnail() {
        return isThumbnail;
    }

    public void setThumbnail(boolean thumbnail) {
        isThumbnail = thumbnail;
    }
}