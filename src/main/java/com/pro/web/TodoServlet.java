package com.pro.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.batch.dao.BatchDao;
import com.pro.model.Member;



public class TodoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");

        // Create a new Member object with the input name
        Member member = new Member(name);
        BatchDao batchDao = new BatchDao();

        try {
            // Insert the member into the database
            int result = batchDao.post(member);

            if (result > 0) {
                // If insertion is successful, redirect to the homepage
                response.sendRedirect("index.html");
            } else {
                // Handle insertion failure
                response.getWriter().println("Failed to insert task.");
            }
        } catch (Exception e) {
            // Log or print the stack trace of the exception
            e.printStackTrace();
            response.getWriter().println("An error occurred while inserting the task.");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BatchDao batchDao = new BatchDao();

        try {
            // Retrieve tasks from the database
            List<Member> tasks = batchDao.getAllTasks();

            // Set tasks as a request attribute
            request.setAttribute("tasks", tasks);

            // Forward the request to the index.html page for rendering
            request.getRequestDispatcher("index.html").forward(request, response);
        } catch (Exception e) {
            // Log or print the stack trace of the exception
            e.printStackTrace();
            response.getWriter().println("An error occurred while retrieving tasks.");
        }
    }    
}