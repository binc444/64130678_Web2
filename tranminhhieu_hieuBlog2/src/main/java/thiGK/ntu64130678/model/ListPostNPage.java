package thiGK.ntu64130678.model;

import java.util.ArrayList;
import java.util.List;

public class ListPostNPage {
	public static List<Page> getPages() {
        List<Page> pages = new ArrayList<>();

        pages.add(new Page(1, "Trang chủ", "trang-chu", "Chào mừng bạn đến với hệ thống quản lý nội dung", 1));
        pages.add(new Page(2, "Giới thiệu môn học", "gioi-thieu-mon-hoc", "Môn Lập trình Web với Spring Boot - giúp bạn xây dựng ứng dụng web thực tế", 1));
        pages.add(new Page(3, "Tài liệu học tập", "tai-lieu", "Danh sách các tài liệu, bài giảng, slide về môn học", 1));
        pages.add(new Page(4, "Java Spring Boot", "java-spring-boot", "Tổng quan về Spring Boot, cấu trúc project, dependency...", 3));
        pages.add(new Page(5, "Liên hệ giảng viên", "lien-he", "Thông tin liên hệ với giảng viên phụ trách môn học", 1));

        return pages;
    }

    public static List<Post> getPosts() {
        List<Post> posts = new ArrayList<>();

        posts.add(new Post(1, "Giới thiệu Spring Boot", "Spring Boot giúp xây dựng ứng dụng web nhanh chóng với cấu hình tối thiểu.", 4));
        posts.add(new Post(2, "Hướng dẫn tạo project Spring Boot", "Sử dụng Spring Initializr để khởi tạo nhanh dự án.", 4));
        posts.add(new Post(3, "Cấu hình Thymeleaf trong Spring Boot", "Tích hợp Thymeleaf vào project để tạo giao diện HTML động.", 4));
        posts.add(new Post(4, "Hướng dẫn làm bài tập lớn", "Các bước triển khai bài tập lớn môn Lập trình Web.", 2));
        posts.add(new Post(5, "Danh sách tài liệu học", "Tài liệu PDF, slide và video bài giảng môn học.", 3));

        return posts;
    }
}
