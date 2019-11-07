package chrysso.evaluator;

import chrysso.page.WebPage;

public class Evaluator {
	
	public WebPage[] evaluate(WebPage[] webs) {
		int value = 0;
		for( WebPage web : webs ) {
			value += head(web);
			value += body(web);
			value += linksPointer(web);
			
			web.setValue(value);
			value = 0;
		}
		return webs;
	}
	
	private int head(WebPage web) {
		int value = 0;
		
		if(! web.getUseJquery())
			value++;
		if(web.isSecure())
			value++;
		if(web.getCharset() == "UTF-8")
			value++;
		if( web.isMobile() )
			value++;
		
		return value;
	}
	
	private int body(WebPage web) {
		return 0;
	}
	
	private int linksPointer(WebPage web) {
		return 0;
	}

}
