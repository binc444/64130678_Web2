package ntu.tmhieu.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import ntu.tmhieu.Model.Article;


public interface ArticleRepository extends JpaRepository<Article, Integer> {
}