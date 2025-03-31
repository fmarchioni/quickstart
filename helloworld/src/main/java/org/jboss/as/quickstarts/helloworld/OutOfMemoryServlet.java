package org.jboss.as.quickstarts.helloworld;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/killer")
public class OutOfMemoryServlet extends HttpServlet {

    static String PAGE_HEADER = "<html><head><title>killer</title></head><body>";
    static String PAGE_FOOTER = "</body></html>";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println(PAGE_HEADER);
        writer.println("<h1>Servlet Killer started!</h1>");
        writer.println(PAGE_FOOTER);
        writer.close();

        // Consuma memoria fino a causare un OutOfMemoryError
        List<byte[]> memoryHog = new ArrayList<>();
        while (true) {
            memoryHog.add(new byte[10 * 1024 * 1024]); // Allocazione di blocchi da 10MB
        }
    }
}
