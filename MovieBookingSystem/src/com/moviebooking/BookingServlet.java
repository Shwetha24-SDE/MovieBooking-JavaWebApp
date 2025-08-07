package com.moviebooking;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class BookingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String movie = request.getParameter("movie");
        String time = request.getParameter("time");
        String snacks = request.getParameter("snacks");
        String seats = request.getParameter("seats");
        String tickets = request.getParameter("tickets");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h2 style='color:green'>Booked Successfully!</h2>");
        out.println("<p>Movie: " + movie + "</p>");
        out.println("<p>Time: " + time + "</p>");
        out.println("<p>Seats: " + seats + "</p>");
        out.println("<p>Tickets: " + tickets + "</p>");
        out.println("<p>Snacks: " + snacks + "</p>");
    }
}