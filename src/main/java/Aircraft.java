import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class Aircraft {
	public class App implements Serializable
	{
	    public String customername, customerid;
	    public Vector result;
	    public void setCustomerid(String customerid)
	    {
	        if(customerid != null)
	            this.customerid = customerid;
	        
	    }
	    
	    public String getCustomerid()
	    {
	        return(this.customerid);
	    }
	    
	    public Vector getResult()
	    {
	        Vector v = new Vector();
	        try
	        {
	            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	            Connection con = DriverManager.getConnection("jdbc:odbc:CustomerDataSource","","");
	            PreparedStatement stat = con.prepareStatement("SELECT * from customer where cust_id= ? ");
	            stat.setString(1,customerid);
	            ResultSet rs = stat.executeQuery();
	            if(rs.next())
	            {
	                v.addElement(rs.getString("cust_name"));
	                v.addElement(rs.getString("address"));
	                v.addElement(rs.getString("city"));
	                
	            }
	            
	        }
	        catch(Exception ex)
	        {
	            ex.printStackTrace();
	        }
	        
	        this.result = v;
	        return v;
	      }
	    
	    }


}
