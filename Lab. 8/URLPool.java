import java.io.IOException;
import java.util.LinkedList;

public class URLPool {
    private LinkedList<URLDepthPair> nopeList; //необработанные
    private LinkedList<URLDepthPair> yesList; //обработанные
    private int finalDepth;
    private int waitThreads = 0;
    public static void main(String[] args) throws IOException {
        //String url = args[0];
        //int finishDepth = Integer.parseInt(args[1]);
        //int CountThre = Integer.parseInt(args[2]);
        String url = "http://www.google.com";
        int finishDepth = 3;
        int CountThre = 4;
        URLPool pool = new URLPool(new URLDepthPair(url,0),finishDepth);
        LinkedList<Thread> threadList = new LinkedList<>();

        for (int i=0;i<CountThre;i++){
            CrawlerTask crawlerTask = new CrawlerTask(pool);
            threadList.add(new Thread(crawlerTask));
            threadList.getLast().start();
        }

        while (pool.getWaitThreads()!=CountThre){}

        LinkedList<URLDepthPair> site=pool.getSite();

        for(URLDepthPair iurl: site){
            System.out.println(iurl);
        }

        for(Thread thread: threadList){
            thread.stop();
        }
    }

    URLPool(URLDepthPair url,int depth){
        finalDepth=depth;
        nopeList = new LinkedList<URLDepthPair>();
        yesList = new LinkedList<URLDepthPair>();
        nopeList.add(url);
    }

    public synchronized int getWaitThreads() {
        return waitThreads;
    }

    public synchronized URLDepthPair getURL(){
        if (nopeList.size() == 0) {
            try {
                waitThreads++;
                this.wait();
            }
            catch (InterruptedException e) {
                System.err.println("MalformedURLException: " + e.getMessage());
                return null;
            }
        }
        yesList.add(nopeList.getFirst());
        return nopeList.removeFirst();
    }

    public synchronized void addListURL(LinkedList<URLDepthPair> URLs){

        if (URLs.size()!=0){
            if (URLs.getFirst().getcDepth()>=finalDepth){
                yesList.addAll(URLs);
            }
            else{
                nopeList.addAll(URLs);
                for (int countSite=URLs.size(); countSite!=0 && waitThreads!=0;countSite-- , waitThreads--){
                    this.notify();
                }
            }
        }
    }

    public LinkedList<URLDepthPair> getSite(){
        return yesList;
    }
}
