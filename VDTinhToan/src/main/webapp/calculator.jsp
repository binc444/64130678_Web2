<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
try {
    // Lấy giá trị tham số từ URL
    String strA = request.getParameter("a");
    String strB = request.getParameter("b");
    String operator = request.getParameter("operator");
    
    int valueA = Integer.parseInt(strA);
    int valueB = Integer.parseInt(strB);
    double result = 0;
    String operation = "";
    
    // Xử lý phép toán
    switch (operator) {
        case "+":
            result = valueA + valueB;
            operation = "+";
            break;
        case "-":
            result = valueA - valueB;
            operation = "-";
            break;
        case "*":
            result = valueA * valueB;
            operation = "*";
            break;
        case "/":
            if (valueB != 0) {
                result = (double) valueA / valueB;
                operation = "/";
            } else {
                out.print("Lỗi: Không thể chia cho 0");
                return;
            }
            break;
        default:
            out.print("Lỗi: Toán tử không hợp lệ");
            return;
    }
    
    // In kết quả
    out.print(valueA + " " + operation + " " + valueB + " = " + result);
} catch (Exception e) {
    out.print("Lỗi: Dữ liệu không hợp lệ");
}
%>
</body>
</html>