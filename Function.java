package MyResourceGroup.BrowserSniffer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.*;



import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

/**
 * Azure Functions with HTTP Trigger.
 */
public class Function {
    /**
     * This function listens at endpoint "/api/HttpTrigger-Java". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/HttpTrigger-Java&code={your function key}
     * 2. curl "{your host}/api/HttpTrigger-Java?name=HTTP%20Query&code={your function key}"
     * Function Key is not needed when running locally, it is used to invoke function deployed to Azure.
     * More details: https://aka.ms/functions_authorization_keys
     * @throws IOException 
     */
    @SuppressWarnings("static-access")
	@FunctionName("HttpTrigger-Java")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req", methods = {HttpMethod.GET, HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) throws IOException {
        context.getLogger().info("Java HTTP trigger processed a request.");
       
        
        // Parse query parameter
        String browser = null;
        String platform = null;
        String os = null;
        String ip = null;
        for(Map.Entry<String,String> entry : request.getHeaders().entrySet())
        {
        	if(entry.getKey().equals("user-agent"))
        	{
        		browser = entry.getValue();
        		int i = 0;
        		int e = 0;
        		i = browser.indexOf('(');
        		e = browser.indexOf(')');
        		platform = browser.substring(0,i);
        		os = browser.substring(i+1,e);
        		browser = browser.substring(e+1, browser.length()-1);
        		
        		
        	}
        	if(entry.getKey().equals("x-forwarded-for"))
        	{
        		ip = entry.getValue();
        		int i = ip.indexOf(':');
        		ip = ip.substring(0, i);
        	}
        }
		String country ="";
		String state = "";
		String city = "";
		int i = -1;
		int e = -1;
		
        try {
			URL url = new URL("https://iplocation.com/?ip="+ip);
			HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
			InputStream result = urlConnection.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(result));
			String line = null;
				while((line = reader.readLine())!=null)
				{
					if(line.contains("country_name"))
					{
						i=line.indexOf("\">");
						e=line.indexOf("</span");
						country = line.substring(i+2, e);
					}
					if(line.contains("region_name"))
					{
						i=line.indexOf("\">");
						e=line.indexOf("</span");
						state = line.substring(i+2, e);
					}
					if(line.contains("class=\"city"))
					{
						i=line.indexOf("\">");
						e=line.indexOf("</");
						city = line.substring(i+2, e);		
					}
				
				}
			result.close();
			reader.close();
			urlConnection.disconnect();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
            return request.createResponseBuilder(HttpStatus.OK).body("Browser: "+ browser+"\nOperating System: "+os+"\nPlatform: "+platform+"\n"+"IP address: "+ip+"\n"+
		"Location: "+city+", "+state+", "+country+"\n\n"+ "Location services provided by: https://iplocation.com/").build();
        
    }
}
