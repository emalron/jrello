package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Member;
import model.MemberDAO;

public class LoginCheckService implements Service {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        PrintWriter pw = resp.getWriter();
        String msg = "";

        if(session == null) {
            // login is needed
            msg = "Login is needed...";
        }
        else {
            MemberDAO mDao = new MemberDAO();
            String id = session.getAttribute("id").toString();

            ArrayList<Member> members = mDao.search(id);
            if(members.isEmpty()) {
                // no such id .... 
                msg = "no such id.";
            }
            else {
                String name = members.get(0).getName();
                msg = "Welcome back, " + name;
            }
        }

        pw.write(msg);
    }
}