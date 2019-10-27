package si.fri.prpo.vaje.skupina4.servlet;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;
import java.util.logging.Logger;

public class UporabnikDaoImpl implements BaseDao{
  private static UporabnikDaoImpl instance;

  public static UporabnikDaoImpl getInstance(){
    if(instance==null){
      instance=new UporabnikDaoImpl();
    }
    return instance;
  }

  private Connection connection;
  private Logger log=Logger.getLogger("UporabnikDaoImpl");

  @Override
  public Connection getConnection(){
    try{
      InitialContext initctx=new InitialContext();
      DataSource ds=(DataSource)initctx.lookup("jdbc/SimpleJdbcDS");
      return ds.getConnection();
    } catch(Exception e){
      log.severe("Can't get connection: "+e.toString());
    }
    return null;
  }

  private Uporabnik getUporabnikFromRS(ResultSet rs) throws SQLException{
    String imepriimek=rs.getString("imepriimek");
    return new Uporabnik(imepriimek);
  }

  @Override
  public Entiteta vrni(int id){
    PreparedStatement ps=null;
    try{
      if(connection==null){
        connection=getConnection();
      }
      String sql = "SELECT * FROM uporabnik WHERE id=?";
      ps=connection.prepareStatement(sql);
      ps.setInt(1,id);
      ResultSet rs=ps.executeQuery();
      if (rs.next()){
        return getUporabnikFromRS(rs);
      } else{
        log.info("Uporabnik ne obstaja");
      }
    } catch(SQLException e){
      log.severe(e.toString());
    } finally{
      if(ps!=null){
        try{
          ps.close();
        } catch(SQLException e){
          log.severe(e.toString());
        }
      }
    }
    return null;
  }

  @Override
  public void vstavi(Entiteta ent){
    //TODO
  }

  @Override
  public void odstrani(int id){
    //TODO
  }

  @Override
  public void posodobi(Entiteta ent){
    //TODO
  }

  @Override
  public List<Entiteta> vrniVse(){
    Statement s=null;
    List<Entiteta> seznamUporabnikov=new ArrayList<Entiteta>();
    try{
      if(connection==null){
        connection=getConnection();
      }
      String sql = "SELECT * FROM uporabnik";
      s=connection.prepareStatement(sql);
      ResultSet rs=s.executeQuery(sql);
      while(rs.next()){
        seznamUporabnikov.add(getUporabnikFromRS(rs));
      }
      if(seznamUporabnikov.size()==0){
        log.info("Uporabniki ne obstajajo");
      }
    } catch(SQLException e){
      log.severe(e.toString());
    } finally{
      if(s!=null){
        try{
          s.close();
        } catch(SQLException e){
          log.severe(e.toString());
        }
      }
    }
    return seznamUporabnikov;
  }
}
