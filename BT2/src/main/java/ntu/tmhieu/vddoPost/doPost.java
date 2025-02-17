package ntu.tmhieu.vddoPost;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class doPost
 */
@WebServlet("/doPost")
public class doPost extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public doPost() {
        super();
    }

    // Hiển thị form nhập tên
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h2>Nhập tên của bạn:</h2>");
        out.println("<form action='doPost' method='post'>");
        out.println("<label for='name'>Tên:</label>");
        out.println("<input type='text' id='name' name='name' required>");
        out.println("<button type='submit'>Gửi</button>");
        out.println("</form>");
        out.println("</body></html>");
    }

    // Hiển thị thông điệp sau khi gửi form
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h2>Xin chào, " + name + "!</h2>");
        out.println("</body></html>");
    }
}
