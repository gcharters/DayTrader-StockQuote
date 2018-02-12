package application.rest.v1;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ibm.websphere.samples.daytrader.direct.TradeDirect;
import com.ibm.websphere.samples.daytrader.entities.QuoteDataBean;
import com.ibm.websphere.samples.daytrader.util.TradeConfig;
import com.ibm.websphere.samples.daytrader.web.TradeBuildDB;



@Path("v1/stockquote")
public class StockQuote {

	@Context
	ServletContext sc;
	
    @GET
    @Path("/{symbol}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getQuote(@PathParam("symbol") String symbol) {
    
    	
    		TradeDirect td = new TradeDirect();
    		
    		try {
			QuoteDataBean qdb = td.getQuote(symbol);
	    	
			if (qdb == null) {
				return Response.status(Response.Status.NOT_FOUND)
						       .entity("The requested stock symbol does not exist.")
						       .build();
			}
						
    			Quote quote = new Quote();
    			quote.setSymbol(symbol);
    			quote.setPrice(qdb.getPrice());
    			quote.setCompanyName(qdb.getCompanyName());
    			quote.setOpen1(qdb.getOpen());
    			quote.setLow(qdb.getLow());
    			quote.setHigh(qdb.getHigh());
    			
        		return Response.ok(quote).build();
        
    		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
    				       .entity("Something went wrong.")
    				       .build();	

    }
    
    @GET
    @Path("/db/buildDBTables")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buildDBTables() {
    	
    		try {
    			String ddlFile = "WEB-INF/dbscripts/derby/Table.ddl";
			new TradeBuildDB(new java.io.PrintWriter(System.out), 
						sc.getResourceAsStream(ddlFile));
	    		return Response.ok().build();

    		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
    		
    		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
				       .entity("Something went wrong.")
				       .build();	    	
    }
    
    
    @GET
    @Path("/db/buildDB")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buildDB() {
    	
        try {
			new TradeBuildDB(new java.io.PrintWriter(System.out), null);
	        System.out.println("DayTrader Database Built - " + TradeConfig.getMAX_USERS() + "users created");
			return Response.ok().build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
			       .entity("Something went wrong.")
			       .build();	       
    	
    }

}
