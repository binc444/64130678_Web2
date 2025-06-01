package ntu.tmhieu.Model;

import jakarta.persistence.*;
import java.util.HashSet; // Sử dụng HashSet để khởi tạo tập hợp các bài viết
import java.util.Set;    // Kiểu dữ liệu cho tập hợp các bài viết

/**
 * Lớp đại diện cho một Thể loại (Category) của bài viết.
 * Ánh xạ tới bảng "categories" trong cơ sở dữ liệu.
 */
@Entity
@Table(name = "categories")
public class Category {

    // Khóa chính (Primary Key) tự động tăng của bảng categories
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    // Tên của thể loại
    private String name;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Article> articles = new HashSet<>(); // Khởi tạo HashSet rỗng

    /**
     * Constructor mặc định (cần thiết cho JPA).
     */
    public Category() {
    }

    /**
     * Constructor để tạo một đối tượng Category mới với tên.
     * @param name Tên của thể loại.
     */
    public Category(String name) {
        this.name = name;
    }

    // --- Getters và Setters cho tất cả các thuộc tính ---

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    public void addArticle(Article article) {
        this.articles.add(article);
        article.setCategory(this); // Thiết lập mối quan hệ ngược lại
    }

    public void removeArticle(Article article) {
        this.articles.remove(article);
        article.setCategory(null); // Gỡ bỏ mối quan hệ ngược lại
    }
}