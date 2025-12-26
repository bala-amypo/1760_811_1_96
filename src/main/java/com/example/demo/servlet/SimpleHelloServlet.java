package com.example.demo.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SimpleHelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Set status code
        resp.setStatus(HttpServletResponse.SC_OK);

        // Set content type
        resp.setContentType("text/plain");

        // Write response
        resp.getWriter().write("Hello from servlet");
    }
}
