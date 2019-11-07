package chrysso.page;

public class UrlPage {
	
	private String url;
	private String context;

	public UrlPage(String url, String context) {
		this.url = url;
		this.context = context;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

}
