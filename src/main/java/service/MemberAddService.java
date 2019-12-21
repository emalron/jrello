package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemberDAO;

public class MemberAddService implements Service {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MemberDAO uDao = new MemberDAO();

        String _id = req.getParameter("id");
        
        // bscrypt로 암호화된 암호가 저장될 것
        String _password = req.getParameter("password");
        String _name = req.getParameter("name");
        

        uDao.addMember(_id, _password, _name);
    }
}