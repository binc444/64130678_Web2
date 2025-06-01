package ntu.tmhieu.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ntu.tmhieu.Model.Reaction;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Integer> {

    // Đếm số lượng cảm xúc của một loại cụ thể cho một bài viết
    long countByArticleArticleIdAndReactionType(Integer articleId, String reactionType);
    List<Reaction> findByArticleArticleIdAndReactionType(Integer articleId, String reactionType);

}