package ntu.tmhieu.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer imageId;

    // Quan hệ ManyToOne với Article: nhiều ảnh thuộc về một bài viết
    @ManyToOne(fetch = FetchType.LAZY) // Lazy loading để không tải ảnh không cần thiết
    @JoinColumn(name = "article_id") 
    private Article article;

    // Ánh xạ cột image_url kiểu BLOB sang byte[] trong Java
    @Lob
    @Column(name = "image_url", columnDefinition = "MEDIUMBLOB")
    private byte[] imageUrl;

    @Column(name = "is_thumbnail")
    private boolean isThumbnail; 
    
    @Column(name = "mime_type", nullable = false)
    private String mimeType;
    
    public Image() {
    }

    public Image(Article article, byte[] imageUrl, boolean isThumbnail) {
        this.article = article;
        this.imageUrl = imageUrl;
        this.isThumbnail = isThumbnail;
    }

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

    public byte[] getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(byte[] imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isThumbnail() {
        return isThumbnail;
    }

    public void setThumbnail(boolean thumbnail) {
        isThumbnail = thumbnail;
    }
    
    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
}
