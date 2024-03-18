package com.pro.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.batch.dao.BatchDao;
import com.pro.model.Member;

public class TodoServletGet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BatchDao batchDao = new BatchDao();
        List<Member> tasks = batchDao.getAllTasks();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Start HTML content
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("    <meta charset=\"UTF-8\">");
        out.println("    <title>Task List</title>");
        out.println("    <style>");
        out.println("        body {");
        out.println("            background-image: url('image1.avif');");
        out.println("            background-size: 900px 900px;");
        out.println("            background-repeat: no-repeat;");
        out.println("            background-position: 400px -120px;");
        out.println("        }");
        out.println("    </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("    <h1>Task List</h1>");
        out.println("    <ul>");
        

        // Dynamically generate list items for each task
        for (Member task : tasks) {
            out.println("    <li>" + task.getName() + "</li>");
        }

        out.println("    </ul>");
        out.println("    <a href=\"index.html\">Add more tasks</a>");
        out.println("</body>");
        out.println("</html>");
    }
}