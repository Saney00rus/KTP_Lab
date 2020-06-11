import java.net.MalformedURLException;
import java.net.URL;

public class URLDepthPair {
   private String SURL;
   private int cDepth;
    public String toString()
    {
        String strDe = Integer.toString(cDepth);
        return strDe + "  " +SURL;
    }
    public boolean isthisURL() throws MalformedURLException
    {
        try{
            URL url = new URL(SURL);
            return true;
        }catch (Exception e)
        {
            System.err.println("MalformedURLException: "+e.getMessage());
            return false;
        }
    }
    public URLDepthPair(String URL, int depth)
    {
        this.SURL=URL;
        this.cDepth=depth;
    }

    public int getcDepth() {
        return cDepth;
    }

    public String getSURL()
    {
        try {
            URL curl = new URL(SURL);
            return curl.getHost();
        } catch (MalformedURLException e)
        {
            System.err.println("MalformedURLException: "+e.getMessage());
            return null;
        }
    }
}
