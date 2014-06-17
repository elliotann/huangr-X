<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="net.sf.jasperreports.engine.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*" %>
<%@ page import="java.sql.*" %>
<%
    String ctxpath = request.getContextPath();
    Class.forName("com.mysql.jdbc.Driver");
    String url="jdbc:mysql://localhost:3306/jeecg";
    String user="root";
    String password="1234567";
    Connection conn= DriverManager.getConnection(url,user,password);
//ireport生成的.jasper文件的存放位置，这里为了方便放置在根目录下面  
    File reportFile = new
            File(this.getServletConfig().getServletContext().getRealPath("/core/admin/report/report1.jasper"));
    Map parameters = new HashMap();
    System.setProperty("java.awt.headless", "true");
    try {
//执行报表程序  
        JasperRunManager.runReportToHtmlFile(reportFile.getPath(),parameters, conn);
        response.sendRedirect(ctxpath+"/report1.html");
    }
    catch (Exception e) {
        System.out.println( e.getMessage() );
    }
    finally {
        try {
            conn.close();
        }
        catch (Exception ex) {
            System.out.println( ex.getMessage() );
        }

    }
%>
