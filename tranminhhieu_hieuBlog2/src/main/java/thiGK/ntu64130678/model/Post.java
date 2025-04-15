package thiGK.ntu64130678.model;

public class Post {
	private int id;
    private String title;
    private String content;
    private int categoryId;

    public Post(int id, String title, String content, int categoryId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.categoryId = categoryId;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getCategoryId() {
        return categoryId;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
