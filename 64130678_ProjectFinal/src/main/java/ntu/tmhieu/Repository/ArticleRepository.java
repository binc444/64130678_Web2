package ntu.tmhieu.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ntu.tmhieu.Model.Article;


public interface ArticleRepository extends JpaRepository<Article, Integer> {
	// Sắp xếp bài viết theo publicationDate giảm dần và giới hạn số lượng
    List<Article> findTop5ByOrderByPublicationDateDesc();
    
    // Tìm bài viết theo Category ID và Pageable 
    Page<Article> findByCategoryCategoryId(Integer categoryId, Pageable pageable);

}