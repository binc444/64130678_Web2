package ntu.tmhieu.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ntu.tmhieu.Model.Image;

public interface ImageRepository extends JpaRepository<Image, Integer> {
}