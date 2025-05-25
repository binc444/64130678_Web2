package ntu.tmhieu.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ntu.tmhieu.Model.Article;


@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

}
