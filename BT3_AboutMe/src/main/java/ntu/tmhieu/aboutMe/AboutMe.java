package ntu.tmhieu.aboutMe;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class AboutMe
 */
@WebServlet("/AboutMe")
public class AboutMe extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AboutMe() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Giao diện với nền màu đơn giản và căn giữa nội dung
        out.println("<html>");
        out.println("<head>");
        out.println("<title>About Me</title>");
        out.println("<style>");
        out.println("body { font-family: 'Arial', sans-serif; background-color: #f4f4f4; margin: 0; padding: 0; display: flex; justify-content: center; align-items: center; }");
        //căn giữa
        out.println(".card { width: 350px; background: white; padding: 20px; border-radius: 8px; box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.2); text-align: center; }");
        out.println("h1 { color: #333; font-size: 22px; }");
        out.println("p { font-size: 16px; color: #555; margin: 6px 0; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");

        out.println("<div class='card'>");
        out.println("<h1>Thông Tin Cá Nhân</h1>");
        out.println("<p><strong>👤 Họ và Tên:</strong> Trần Minh Hiếu</p>");
        out.println("<p><strong>📅 Ngày Sinh:</strong> 03/03/2004</p>");
        out.println("<p><strong>🎓 Ngành Học:</strong> Công Nghệ Thông Tin</p>");
        out.println("<p><strong>📩 Email:</strong> hieu.tmi.64cntt@ntu.edu.vn</p>");
        out.println("</div>");

        out.println("</body>");
        out.println("</html>");
    }
}
