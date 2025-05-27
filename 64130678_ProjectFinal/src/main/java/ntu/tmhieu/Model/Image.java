package ntu.tmhieu.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer imageId;

    private String path; 

    @Column(name = "is_thumbnail")
    private boolean isThumbnail;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

}

