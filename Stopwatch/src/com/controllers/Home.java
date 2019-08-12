package com.controllers;

import com.models.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.joda.time.DateTime;
/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @SuppressWarnings({ "unchecked", "unused" })
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("submit");
        HttpSession session = request.getSession();

      
        // Get current timing information, if available
        Timer curtimer = (Timer)session.getAttribute("curtimer");
        ArrayList<Timer> timers = (ArrayList<Timer>)session.getAttribute("timers");
        DateTime startTime = (DateTime)session.getAttribute("startTime");

        // If a button was clicked (this isn't initial page load)
        if (action.equals("Start")) {
            if (curtimer == null)
                curtimer = new Timer();
            curtimer.startTimer();

            if (timers == null) {
                timers = new ArrayList<>();
                session.setAttribute("timers", timers);
            }
            timers.add(curtimer);
            session.setAttribute("curtimer", curtimer);
        }
        else if (action.equals("Stop") && curtimer != null) {
            curtimer.stopTimer();
            session.removeAttribute("curtimer");
        }
        else if (action.equals("Reset")) {
            session.removeAttribute("curtimer");
            session.removeAttribute("timers");
        }

        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
        view.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
        view.forward(request, response);
    }
}