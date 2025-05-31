package ntu.tmhieu.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer articleId;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "publication_date")
    private LocalDateTime publicationDate;

    @Column(name = "is_international")
    private boolean isInternational;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    // Quan hệ OneToMany với Image: một bài viết có nhiều ảnh
    // mappedBy trỏ đến tên thuộc tính "article" trong class Image
    // cascade = CascadeType.ALL để các thao tác với Article cũng áp dụng cho Images liên quan
    // orphanRemoval = true để xóa ảnh khi không còn liên kết với bài viết
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Image> images;

    public Article() {
    }

    public Article(String title, String content, LocalDateTime publicationDate, boolean isInternational, Category category) {
        this.title = title;
        this.content = content;
        this.publicationDate = publicationDate;
        this.isInternational = isInternational;
        this.category = category;
    }

    // Getters, Setters
    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public boolean isInternational() {
        return isInternational;
    }

    public void setInternational(boolean international) {
        isInternational = international;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    // Phương thức tiện ích để lấy ảnh thumbnail
    public Image getThumbnailImage() {
        if (images == null || images.isEmpty()) {
            return null;
        }
        return images.stream()
                     .filter(Image::isThumbnail) // Sử dụng method reference
                     .findFirst()
                     .orElse(null);
    }
}