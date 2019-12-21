package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Member;
import model.MemberDAO;

public class MemberSearchService implements Service {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("id");
        PrintWriter pw = resp.getWriter();

        MemberDAO mDao = new MemberDAO();
        ArrayList<Member> members = mDao.search(name);

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(members);

        if(members == null) {
            pw.print("no return from uDao.test call");
        }
        else {
            resp.setContentType("application/x-json; charset=utf-8");
            pw.print(jsonString);
        }
    }
}