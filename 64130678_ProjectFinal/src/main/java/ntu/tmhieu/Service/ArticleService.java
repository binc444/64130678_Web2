package ntu.tmhieu.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ntu.tmhieu.Model.Article;
import ntu.tmhieu.Repository.ArticleRepository;

@Service
public class ArticleService {
	@Autowired
    private ArticleRepository articleRepository;

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    // Phương thức mới cho phân trang và sắp xếp
    public Page<Article> getArticlesPaginated(Pageable pageable) {
        return articleRepository.findAll(pageable);
    }

    public void deleteArticle(Integer id) {
        articleRepository.deleteById(id);
    }
}
