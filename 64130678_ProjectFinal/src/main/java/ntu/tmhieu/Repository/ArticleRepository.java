package ntu.tmhieu.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ntu.tmhieu.Model.Article;


public interface ArticleRepository extends JpaRepository<Article, Integer> {
}