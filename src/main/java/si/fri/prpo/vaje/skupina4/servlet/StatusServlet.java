package si.fri.prpo.vaje.skupina4.servlet;

import com.kumuluz.ee.configuration.utils.ConfigurationUtil;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/servlet")
public class StatusServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        ConfigurationUtil conf=ConfigurationUtil.getInstance();
        PrintWriter pw=resp.getWriter();
        Logger log=Logger.getLogger(StatusServlet.class.getName());
        BaseDao udao=UporabnikDaoImpl.getInstance();
        List<Entiteta> vsi=udao.vrniVse();
        pw.printf("I've been all around the grlobe. Globe.\n-Sam Hyde\n\n\n");
        pw.printf("Name: "+conf.get("kumuluzee.name").get()+"\nVersion: "+conf.get("kumuluzee.version").get()+"\nRun env. name: "+conf.get("kumuluzee.env.name").get());
        pw.printf("\n\n\n");

        /*test vrni
        Uporabnik u=(Uporabnik)(udao.vrni(1));
        pw.printf("Uporabnik 1: "+u.toString()+"\n");

        //test vstavi
        Uporabnik u1=new Uporabnik("Samir al-Hayid","lilsammy");
        udao.vstavi(u1);

        vsi=udao.vrniVse();
        pw.printf("Vsi:\n");
        vsi.stream().forEach(user -> pw.printf(user.toString()+"\n"));

        //test odstrani
        udao.odstrani(2);

        vsi=udao.vrniVse();
        pw.printf("Vsi:\n");
        vsi.stream().forEach(user -> pw.printf(user.toString()+"\n"));

        //test posodobi
        Uporabnik u2=(Uporabnik)(udao.vrni(1));
        u.setImePriimek("Denis Benis");
        udao.posodobi(u);

        vsi=udao.vrniVse();
        pw.printf("Vsi:\n");
        vsi.stream().forEach(user -> pw.printf(user.toString()+"\n"));

        //test vrniVse*/
        vsi=udao.vrniVse();
        pw.printf("Vsi uporabniki:\n");
        vsi.stream().forEach(user -> pw.printf(user.toString()+"\n"));

        log.info("Vsi uporabniki:");
        vsi.stream().forEach(user -> log.info(user.toString()));

        pw.close();
    }
}