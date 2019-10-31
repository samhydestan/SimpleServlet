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
        Uporabnik u=(Uporabnik)udao.vrni(1);
        List<Entiteta> us=udao.vrniVse();
        pw.printf("I've been all around the grlobe. Globe.\n-Sam Hyde\n\n\n");
        pw.printf("Uporabnik z IDjem %d: %s\n",u.getID(),u.getImePriimek());
        pw.printf("Name: "+conf.get("kumuluzee.name").get()+"\nVersion: "+conf.get("kumuluzee.version").get()+"\nRun env. name: "+conf.get("kumuluzee.env.name").get());
        pw.close();
    }
}