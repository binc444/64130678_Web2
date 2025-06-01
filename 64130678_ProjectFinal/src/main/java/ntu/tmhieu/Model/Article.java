package ntu.tmhieu.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime; //để sử dụng kiểu dữ liệu thời gian

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

    // Cờ boolean cho biết bài viết có phải là tin quốc tế hay không
    @Column(name = "is_international")
    private boolean isInternational;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "thumbnail_image_data", columnDefinition = "MEDIUMBLOB")
    private byte[] thumbnailImageData;

    // Kiểu MIME của ảnh thumbnail
    @Column(name = "thumbnail_mime_type")
    private String thumbnailMimeType;

    public Article() {
    }
    public Article(String title, String content, LocalDateTime publicationDate, boolean isInternational, Category category, byte[] thumbnailImageData, String thumbnailMimeType) {
        this.title = title;
        this.content = content;
        this.publicationDate = publicationDate;
        this.isInternational = isInternational;
        this.category = category;
        this.thumbnailImageData = thumbnailImageData;
        this.thumbnailMimeType = thumbnailMimeType;
    }

    // --- Getters và Setters cho tất cả các thuộc tính ---
    
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

    public byte[] getThumbnailImageData() {
        return thumbnailImageData;
    }

    public void setThumbnailImageData(byte[] thumbnailImageData) {
        this.thumbnailImageData = thumbnailImageData;
    }

    public String getThumbnailMimeType() {
        return thumbnailMimeType;
    }

    public void setThumbnailMimeType(String thumbnailMimeType) {
        this.thumbnailMimeType = thumbnailMimeType;
    }

    /**
     * Phương thức tiện ích để lấy một đoạn trích ngắn từ nội dung bài viết.
     * Loại bỏ các thẻ HTML để chỉ lấy văn bản thuần túy.
     * @param maxLength Chiều dài tối đa của đoạn trích.
     * @return Đoạn trích văn bản.
     */
    public String getShortContent(int maxLength) {
        if (content == null || content.isEmpty()) {
            return "";
        }
        // Loại bỏ tất cả các thẻ HTML để có văn bản thuần túy
        String plainTextContent = content.replaceAll("<[^>]*>", "");
        if (plainTextContent.length() <= maxLength) {
            return plainTextContent;
        }
        // Trả về đoạn trích và thêm dấu "..."
        return plainTextContent.substring(0, maxLength) + "...";
    }

    /**
     * Phương thức tiện ích để lấy đoạn trích ngắn với chiều dài mặc định là 150 ký tự.
     * @return Đoạn trích văn bản.
     */
    public String getShortContent() {
        return getShortContent(150); // Mặc định 150 ký tự
    }
}