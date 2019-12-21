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

public class LoginService implements Service {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MemberDAO mDao = new MemberDAO();
        PrintWriter pw = resp.getWriter();

        String id = req.getParameter("id");
        String password = req.getParameter("password");
        ArrayList<Member> members = mDao.search(id);
        String result = "{ result: ";

        if(!members.isEmpty()) {
            Member _member = members.get(0);

            if(_member.getId().equals(id) && _member.getPassword().equals(password)) {
                // login sucess
                HttpSession session = req.getSession();
                session.setAttribute("id", _member.getId());
                result += "true }";

                pw.write(result);
                pw.close();
            }
            else {
                // login fail
                result += "false }";
            }
        }
    }
}