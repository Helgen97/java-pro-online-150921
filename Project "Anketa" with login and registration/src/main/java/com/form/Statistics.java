package com.form;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


@WebServlet(value = "/stats")
public class Statistics extends HttpServlet {
    private final Map<String, AtomicInteger> questionStatistic = new HashMap<>();

    @Override
    public void init() {
        questionStatistic.put("everyDay", new AtomicInteger(0));
        questionStatistic.put("fewInWeek", new AtomicInteger(0));
        questionStatistic.put("oneInWeek", new AtomicInteger(0));
        questionStatistic.put("never", new AtomicInteger(0));

        questionStatistic.put("staff1", new AtomicInteger(0));
        questionStatistic.put("staff2", new AtomicInteger(0));
        questionStatistic.put("staff3", new AtomicInteger(0));
        questionStatistic.put("staff4", new AtomicInteger(0));
        questionStatistic.put("staff5", new AtomicInteger(0));

        questionStatistic.put("cafe1", new AtomicInteger(0));
        questionStatistic.put("cafe2", new AtomicInteger(0));
        questionStatistic.put("cafe3", new AtomicInteger(0));
        questionStatistic.put("cafe4", new AtomicInteger(0));
        questionStatistic.put("cafe5", new AtomicInteger(0));

        questionStatistic.put("yes", new AtomicInteger(0));
        questionStatistic.put("no", new AtomicInteger(0));

        questionStatistic.put("male", new AtomicInteger(0));
        questionStatistic.put("female", new AtomicInteger(0));

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String visitsOften = request.getParameter("visitsOften");
        String staffRate = request.getParameter("staffRate");
        String cafeRate = request.getParameter("cafeRate");
        String cafeRecom = request.getParameter("cafeRecom");
        String gender = request.getParameter("gender");

        questionStatistic.get(visitsOften).getAndIncrement();
        questionStatistic.get(staffRate).getAndIncrement();
        questionStatistic.get(cafeRate).getAndIncrement();
        questionStatistic.get(cafeRecom).getAndIncrement();
        questionStatistic.get(gender).getAndIncrement();

        try {
            setAttributes(request);
            request.getRequestDispatcher("statistic.jsp").forward(request, response);

        } catch (ServletException ex) {
            response.sendRedirect("index.jsp");
        }
    }

    private void setAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("userName", request.getParameter("userName"));
        session.setAttribute("userSurname", request.getParameter("userSurname"));
        session.setAttribute("userAge", request.getParameter("userAge"));

        session.setAttribute("visitsOftenEvery", questionStatistic.get("everyDay").get());
        session.setAttribute("visitsOftenFew", questionStatistic.get("fewInWeek").get());
        session.setAttribute("visitsOftenOnce", questionStatistic.get("oneInWeek").get());
        session.setAttribute("visitsOftenNever", questionStatistic.get("never").get());

        session.setAttribute("staff1", questionStatistic.get("staff1").get());
        session.setAttribute("staff2", questionStatistic.get("staff2").get());
        session.setAttribute("staff3", questionStatistic.get("staff3").get());
        session.setAttribute("staff4", questionStatistic.get("staff4").get());
        session.setAttribute("staff5", questionStatistic.get("staff5").get());

        session.setAttribute("cafe1", questionStatistic.get("cafe1").get());
        session.setAttribute("cafe2", questionStatistic.get("cafe2").get());
        session.setAttribute("cafe3", questionStatistic.get("cafe3").get());
        session.setAttribute("cafe4", questionStatistic.get("cafe4").get());
        session.setAttribute("cafe5", questionStatistic.get("cafe5").get());

        session.setAttribute("cafeRecomYes", questionStatistic.get("yes").get());
        session.setAttribute("cafeRecomNo", questionStatistic.get("no").get());

        session.setAttribute("genderMale", questionStatistic.get("male").get());
        session.setAttribute("genderFemale", questionStatistic.get("female").get());
    }

}