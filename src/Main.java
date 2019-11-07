import java.time.Duration;
import java.time.LocalDateTime;

import chrysso.ChryssoPulcherrima;
import chrysso.nodes.QueueUrlPage;
import chrysso.nodes.QueueWebPage;
import chrysso.page.WebPage;

public class Main {
	
	public static void printTime(LocalDateTime time0, LocalDateTime time1) {
		Duration duration = Duration.between(time0, time1);
        System.out.println("Time:"+duration.getSeconds() + " seconds");
	}

	public static void main(String[] args) {
		
		System.out.println("Starting Chrysso Pulcherrima..."); // https://bugguide.net/node/view/598913/bgpage
		
		String[] urls = {
			"https://www.ecosia.org/search?q=%3Chello%3E"	
		};
		
		LocalDateTime time0, time1, time2, time3;
		
		ChryssoPulcherrima strt = new ChryssoPulcherrima(); //53630
		time0 = LocalDateTime.now();
		QueueWebPage webs = strt.scrape(urls);
		time1 = LocalDateTime.now();
		
		printTime(time0, time1);
		
		QueueUrlPage queueUrls = new QueueUrlPage();
		while(! webs.isEmpty()) {
			WebPage web = webs.dequeue();
			queueUrls = web.getLinks();
			System.out.println(web);
			System.out.println("Total Links founded:"+queueUrls.size());
		}
		
		time2 = LocalDateTime.now();
		QueueWebPage webs2 = strt.scrape(queueUrls);
		time3 = LocalDateTime.now();
		
		printTime(time2, time3);
		
		while(! webs2.isEmpty()) {
			System.out.println(webs2.dequeue().toString());
		}
		
		//Evaluator ev = new Evaluator();
		//webs = ev.evaluate( webs );
		
	}

}
