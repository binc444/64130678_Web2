package ntu.tmhieu.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ntu.tmhieu.Model.Image;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
    Optional<Image> findByArticleArticleIdAndIsThumbnailTrue(Integer articleId);
}