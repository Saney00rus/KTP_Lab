import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.*;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Crawler {
    LinkedList<URLDepthPair> curList;
    PrintWriter printWriter;
    BufferedReader bufferedReader;
    Pattern regHTTP;
    Socket socket;
    public static void main(String[] args) throws IOException{
        LinkedList<URLDepthPair> curSite=new LinkedList<>();
        LinkedList<URLDepthPair> newSites=new LinkedList<>();
        int depth = 2;
        newSites.add(new URLDepthPair("http://www.google.com",0));
        //newSites.add(curSite.getFirst());
        while (newSites.size()!=0){
            Crawler crawler = new Crawler(newSites.getFirst());
            if (newSites.getFirst().getcDepth()!=depth) {
                newSites.addAll(crawler.getUrl());
            }
            else
            {
                curSite.addAll(crawler.getUrl());
            }
            curSite.add(newSites.removeFirst());
        }

        for(URLDepthPair url: curSite)
        {
            System.out.println(url);
        }
    }

    Crawler (URLDepthPair pair) throws IOException{
        try {
            socket = new Socket(pair.getSURL(), 80);
           bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
           printWriter = new PrintWriter(socket.getOutputStream(), true);
           curList = new LinkedList<URLDepthPair>();
           regHTTP = Pattern.compile("(http:\\/\\/[\\w\\-\\.!~?&=+\\*'(),\\/\\#\\:]+)((?!\\<\\/\\w\\>))*?");//true url or not
        }
        catch (Exception exc){
            System.out.println(exc);
            return;
        }
        printWriter.println("GET / HTTP/1.1");
        printWriter.println("Host: "+pair.getSURL()+":80");
        printWriter.println("Connection: Close");
        printWriter.println();

        try{
            String line;
            while ((line=bufferedReader.readLine())!=null) {
                while(line.contains("<a")){
                    while (line.indexOf(">", line.indexOf("<a"))==-1) line+=bufferedReader.readLine();

                    String http = line.substring(line.indexOf("<a"),line.indexOf(">", line.indexOf("<a")));
                    if (http.contains("http://")){
                        Matcher matcher = regHTTP.matcher(http);
                        matcher.find();
                        String url = matcher.group();
                        curList.add(new URLDepthPair(url,pair.getcDepth()+1));
                    }
                    line=line.replace(http,"");
                }
            }
        }
        catch (IOException except){
            System.out.println(except);
        }
        socket.close();
    }
    LinkedList<URLDepthPair> getUrl()
    {
        return curList;
    }


}
