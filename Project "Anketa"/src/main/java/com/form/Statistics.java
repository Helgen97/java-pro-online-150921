package com.form;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
            HttpServletRequest newRequest = setAttributes(request);
            request.getRequestDispatcher("statistic.jsp").forward(newRequest, response);

        } catch (ServletException ex) {
            response.sendRedirect("index.jsp");
        }
    }

    private HttpServletRequest setAttributes(HttpServletRequest request) {
        request.setAttribute("userName", request.getParameter("userName"));
        request.setAttribute("userSurname", request.getParameter("userSurname"));
        request.setAttribute("userAge", request.getParameter("userAge"));

        request.setAttribute("visitsOftenEvery", questionStatistic.get("everyDay").get());
        request.setAttribute("visitsOftenFew", questionStatistic.get("fewInWeek").get());
        request.setAttribute("visitsOftenOnce", questionStatistic.get("oneInWeek").get());
        request.setAttribute("visitsOftenNever", questionStatistic.get("never").get());

        request.setAttribute("staff1", questionStatistic.get("staff1").get());
        request.setAttribute("staff2", questionStatistic.get("staff2").get());
        request.setAttribute("staff3", questionStatistic.get("staff3").get());
        request.setAttribute("staff4", questionStatistic.get("staff4").get());
        request.setAttribute("staff5", questionStatistic.get("staff5").get());

        request.setAttribute("cafe1", questionStatistic.get("cafe1").get());
        request.setAttribute("cafe2", questionStatistic.get("cafe2").get());
        request.setAttribute("cafe3", questionStatistic.get("cafe3").get());
        request.setAttribute("cafe4", questionStatistic.get("cafe4").get());
        request.setAttribute("cafe5", questionStatistic.get("cafe5").get());

        request.setAttribute("cafeRecomYes", questionStatistic.get("yes").get());
        request.setAttribute("cafeRecomNo", questionStatistic.get("no").get());

        request.setAttribute("genderMale", questionStatistic.get("male").get());
        request.setAttribute("genderFemale", questionStatistic.get("female").get());

        return request;
    }

}