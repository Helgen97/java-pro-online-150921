package ua.kiev.prog.ShortLinkServlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "shortServlet", value = "/shorten")
public class UrlServlet extends HttpServlet {

    private final UrlDatabase urlDatabase = UrlDatabase.INSTANCE;
    private final Gson gson = new GsonBuilder().create();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            byte[] buf = requestBodyToArray(request); // InputUrl
            String json = new String(buf, StandardCharsets.UTF_8);

            InputUrl input = gson.fromJson(json, InputUrl.class);
            String shorten = getServerURL(request) + urlDatabase.save(input.getUrl());

            OutputUrl output = new OutputUrl();
            output.setShortUrl(shorten);
            output.setUrl(input.getUrl());
            output.setComment("short URL");

            json = gson.toJson(output);

            response.setContentType("application/json");
            response.getWriter().print(json);
        } catch (IOException ex) {
            response.setStatus(400); // bad request
        }
    }

    private String getServerURL(HttpServletRequest req) {
        return req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/my/";
    }

    private byte[] requestBodyToArray(HttpServletRequest req) throws IOException {
        InputStream is = req.getInputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[10240];
        int r;

        do {
            r = is.read(buf);
            if (r > 0) bos.write(buf, 0, r);
        } while (r != -1);

        return bos.toByteArray();
    }
}