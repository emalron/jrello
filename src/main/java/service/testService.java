package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Member;
import model.MemberDAO;

public class testService implements Service {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MemberDAO mDao = new MemberDAO();
        ArrayList<Member> members = new ArrayList<Member>();
        PrintWriter pw = resp.getWriter();
        ObjectMapper mapper = new ObjectMapper();

        members = mDao.test();
        String output = mapper.writeValueAsString(members);

        if(!members.isEmpty()) {
            resp.setContentType("application/x-json; charset=utf-8");
            pw.write(output);
        }
        else {
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            pw.print("no return from uDao.test call");
        }
    }
}