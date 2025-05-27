package ntu.tmhieu.dto;

public class ArticleDTO {
	private String title;
    private String thumbnailUrl;

    public ArticleDTO(String title, String imageUrl) {
        this.title = title;
        this.thumbnailUrl = "/images/articles/" + imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
}
