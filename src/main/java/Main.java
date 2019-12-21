import java.util.ArrayList;

import model.Member;
import model.MemberDAO;

public class Main {
    public static void main(String[] args) {
        MemberDAO mDao = new MemberDAO();

        mDao.addMember("Jes", "password", "teeeeee");
        mDao.addMember("top", "password", "ppo");
        mDao.addMember("suoup", "password", "Stone soup");

        ArrayList<Member> members = new ArrayList<Member>();

        members = mDao.test();

        System.out.println("Show all members");
        for(Member u : members) {
            String out = u.getId() + " " + u.getName();
            System.out.println(out);
        }

        members = mDao.search("min");

        System.out.println("Searching...");
        for(Member u : members) {
            String out = u.getId() + " " + u.getName();
            System.out.println(out);
        }
    }
}