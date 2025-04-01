package thiGK.ntu64130678.model;
import java.util.ArrayList;
import java.util.List;

public class DataStorage {
	 public static List<Page> pages = new ArrayList<>();
	    public static List<Post> posts = new ArrayList<>();

	    static {
	        // Hardcode danh sách Page
	        pages.add(new Page(1, "Trang Chủ", "home", "Đây là trang chủ", null));
	        pages.add(new Page(2, "Giới Thiệu", "about", "Thông tin về chúng tôi", null));
	        pages.add(new Page(3, "Dịch Vụ", "services", "Danh sách dịch vụ", null));
	        pages.add(new Page(4, "Liên Hệ", "contact", "Thông tin liên hệ", null));

	        // Hardcode danh sách Post
	        posts.add(new Post(1, "Bài Viết 1", "addad", 1));
	        posts.add(new Post(2, "Bài Viết 2", "jgfhgf", 2));
	        posts.add(new Post(3, "Bài Viết 3", "fghgf", 1));
	        posts.add(new Post(4, "Bài Viết 4", "fghgf", 3));
	    }

	    public static List<Page> getPages() {
	        return pages;
	    }

	    public static List<Post> getPosts() {
	        return posts;
	    }
}
