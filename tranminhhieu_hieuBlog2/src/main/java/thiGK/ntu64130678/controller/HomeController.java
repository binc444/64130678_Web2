package thiGK.ntu64130678.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import thiGK.ntu64130678.model.ListPostNPage;
import thiGK.ntu64130678.model.Page;
import thiGK.ntu64130678.model.Post;

@Controller
public class HomeController {

	@GetMapping("/")
	public String index() {
        return "index";
    }
	
	//Xử lý cho trang page-list
	@GetMapping("/page/all")
    public String getAllPages(Model model) {
        List<Page> pages = ListPostNPage.getPages();
        model.addAttribute("pages", pages);
        model.addAttribute("pageTitle", "Danh sách Page");
        return "page-list";
    }
	
	//Xử lý khi muốn thêm mới một page
	@GetMapping("/page/new")
	public String showCreatePageForm(Model model) {
	    model.addAttribute("page", new Page(0, "", "", "", 0)); // ID tạm là 0
	    model.addAttribute("pageTitle", "Thêm Page mới");
	    return "page-form";
	}
	
	// lưu page vào lớp Page
	@PostMapping("/page/save")
	public String savePage(@ModelAttribute Page page) {
	    int newId = ListPostNPage.getPages().size() + 1;
	    page.setId(newId);
	    ListPostNPage.addPage(page); // <-- lưu trong danh sách tĩnh
	    return "redirect:/page/all";
	}
	
	
	// Xử lý khi nhấn view
	@GetMapping("/page/view/{id}")
	public String viewPage(@PathVariable("id") int id, Model model) {
	    Page pageToView = null;
	    for (Page p : ListPostNPage.getPages()) {
	    	//Nếu tìm thấy Page có id đúng với id người dùng yêu cầu, gán nó vào biến pageToView
	        if (p.getId() == id) {
	            pageToView = p;
	            break;
	        }
	    }
	    if (pageToView != null) {
	        model.addAttribute("page", pageToView); //Gửi pageToView vào Model để đưa sang Thymeleaf
	        model.addAttribute("pageTitle", "Chi tiết Page");
	        return "page-view"; // tên file HTML
	    } else {
	        return "redirect:/page/all"; // nếu không tìm thấy thì quay lại danh sách
	    }
	}
	
	//xử lý delete page
	@GetMapping("/page/delete/{id}")
	public String deletePage(@PathVariable("id") int id) {
	    List<Page> pages = ListPostNPage.getPages();
	    Page pageToDelete = null;

	    for (Page p : pages) {
	        if (p.getId() == id) {
	            pageToDelete = p;
	            break;
	        }
	    }

	    if (pageToDelete != null) {
	        pages.remove(pageToDelete); //xóa khỏi list
	    }

	    return "redirect:/page/all";
	}
	
	/*
	 * 
	 * 
	 * 
	 * 
	 * */
	//Xử lý phần post
	// Xử lý cho trang post-list
	@GetMapping("/post/all")
	public String getAllPosts(Model model) {
	    List<Post> posts = ListPostNPage.getPosts();
	    model.addAttribute("posts", posts);
	    model.addAttribute("pageTitle", "Danh sách Post");
	    return "post-list"; //
	}
	
	// Hiển thị form thêm mới Post
	@GetMapping("/post/new")
	public String showCreatePostForm(Model model) {
	    model.addAttribute("post", new Post(0, "", "", 0)); 
	    model.addAttribute("pageTitle", "Thêm Post mới");
	    return "post-form"; 
	}

	// Lưu Post vào danh sách tĩnh
	@PostMapping("/post/save")
	public String savePost(@ModelAttribute Post post) {
	    int newId = ListPostNPage.getPosts().size() + 1;
	    post.setId(newId);
	    ListPostNPage.addPost(post); 
	    return "redirect:/post/all";
	}
	
	//View post theo id
	@GetMapping("/post/view/{id}")
	public String viewPost(@PathVariable("id") int id, Model model) {
	    Post postToView = null;
	    for (Post p : ListPostNPage.getPosts()) {
	        if (p.getId() == id) {
	            postToView = p;
	            break;
	        }
	    }

	    if (postToView != null) {
	        model.addAttribute("post", postToView); // Gửi post vào model
	        model.addAttribute("pageTitle", "Chi tiết Bài viết");
	        return "post-view"; 
	    } else {
	        return "redirect:/post/all"; // Nếu không tìm thấy thì quay lại danh sách
	    }
	}
	
	//Delete Post theo ID
	@GetMapping("/post/delete/{id}")
	public String deletePost(@PathVariable("id") int id) {
	    List<Post> posts = ListPostNPage.getPosts();
	    Post postToDelete = null;

	    for (Post p : posts) {
	        if (p.getId() == id) {
	            postToDelete = p;
	            break;
	        }
	    }

	    if (postToDelete != null) {
	        posts.remove(postToDelete); // Xóa post khỏi danh sách
	    }

	    return "redirect:/post/all";
	}

}
