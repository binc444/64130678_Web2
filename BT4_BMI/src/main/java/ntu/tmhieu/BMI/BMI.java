package ntu.tmhieu.BMI;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class BMI
 */
@WebServlet("/BMI")
public class BMI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BMI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // form nhập chiều cao và cân nặng
        out.println("<html><body>");
        out.println("<h2>Tính chỉ số BMI</h2>");
        out.println("<form action='BMI' method='POST'>");
        out.println("Chiều cao (m): <input type='text' name='height' /><br/>");
        out.println("Cân nặng (kg): <input type='text' name='weight' /><br/>");
        out.println("<input type='submit' value='Tính BMI' />");
        out.println("</form>");
        out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Lấy dữ liệu từ form
        String strH = request.getParameter("height");
        String strW = request.getParameter("weight");
        
        // tính BMI
        try {
            double height = Double.parseDouble(strH);
            double weight = Double.parseDouble(strW);
            double bmi = weight / (height * height);

            // Sử dụng String.format để in BMI với 2 chữ số sau dấu phẩy
            String nBMI = String.format("%.2f", bmi);

            out.println("<html><body>");
            out.println("<h2>Kết quả tính BMI</h2>");
            out.println("<p>BMI của bạn là: " + nBMI + "</p>");
            
            // Xác định tình trạng sức khỏe dựa trên BMI
            String tinhTrang = "";
            if (bmi < 18.5) {
                tinhTrang = "Gầy";
            } else if (bmi >= 18.5 && bmi < 25) {
                tinhTrang = "Bình thường";
            } else if (bmi >= 25 && bmi < 30) {
                tinhTrang = "Thừa cân";
            } else {
                tinhTrang = "Béo phì";
            }
            out.println("<p>Tình trạng sức khỏe: " + tinhTrang + "</p>");
            out.println("</body></html>");
        } catch (NumberFormatException e) {
            out.println("<html><body>");
            out.println("<p>Vui lòng nhập chiều cao và cân nặng hợp lệ.</p>");
            out.println("</body></html>");
        }
	}

}
