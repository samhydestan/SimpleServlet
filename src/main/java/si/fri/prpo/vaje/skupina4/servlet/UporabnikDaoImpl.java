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
  private Logger log=Logger.getLogger(UporabnikDaoImpl.class.getName());

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
    int id=rs.getInt("ID");
    String imepriimek=rs.getString("imepriimek");
    return new Uporabnik(id,imepriimek);
  }

  @Override
  public Entiteta vrni(int id){
    PreparedStatement ps=null;
    try{
      if(connection==null){
        connection=getConnection();
      }
      String sql = "SELECT * FROM uporabniki WHERE id=?";
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
        Statement s=null;
        try{
            if(connection==null){
                connection=getConnection();
            }
            String sql = "INSERT INTO uporabnik(";
            String vals = "";
            if(ent instanceof Uporabnik){
                sql += "ImePriimek";
                vals += "'" + ((Uporabnik) ent).getImePriimek() + "'";
            }
            sql += ") VALUES (";
            sql += vals + ")";
            s=connection.prepareStatement(sql);
            int rs=s.executeUpdate(sql);
            if(rs > 1 || rs == 0){
                //error
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
    }

    @Override
    public void odstrani(int id){
        Statement s=null;
        try{
            if(connection==null){
                connection=getConnection();
            }
            String sql = "DELETE FROM uporabniki WHERE id=" + id;
            s=connection.prepareStatement(sql);
            int rs=s.executeUpdate(sql);
            if(rs > 1 || rs == 0){
                //error
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
    }

    @Override
    public void posodobi(Entiteta ent){
        Statement s=null;
        try{
            if(connection==null){
                connection=getConnection();
            }
            String sql = "UPDATE uporabniki SET  '" +((Uporabnik) ent).getImePriimek() +"' WHERE id=" + ent.getID();
            s=connection.prepareStatement(sql);
            int rs=s.executeUpdate(sql);
            if(rs > 1 || rs == 0){
                //error
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
    }

  @Override
  public List<Entiteta> vrniVse(){
    Statement s=null;
    List<Entiteta> seznamUporabnikov=new ArrayList<>();
    try{
      if(connection==null){
        connection=getConnection();
      }
      s=connection.createStatement();
      String sql = "SELECT * FROM uporabniki";
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
