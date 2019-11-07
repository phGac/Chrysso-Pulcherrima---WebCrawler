package chrysso.nodes;

import chrysso.page.UrlPage;

public class QueueUrlPage {
	private class NodeUrlPage {
		public UrlPage urlPage;
		public NodeUrlPage pointer;
		
		public NodeUrlPage(UrlPage urlPage) {
			this.urlPage = urlPage;
			this.pointer = null;
		}
	}
	
	private NodeUrlPage head;
	private NodeUrlPage last;
	private int size;

	public QueueUrlPage() {
		this.head = null;
		this.size = 0;
	}
	
	public void enqueue(UrlPage url) {
		NodeUrlPage newNode = new NodeUrlPage(url);
		if(this.head == null) {
			this.head = newNode;
		} else {
			this.last.pointer = newNode;
		}
		this.last = newNode;
		this.size++;
	}
	
	public UrlPage dequeue() {
		if(this.head != null) {
			UrlPage url = this.head.urlPage;
			NodeUrlPage aux = this.head;
			
			this.head = aux.pointer;
			aux.pointer = null;
			if(this.head == null)
				this.last = null;
			this.size--;
			return url;
		} else {
			return null;	
		}
	}
	
	public UrlPage get() {
		if(this.head == null) {
			return null;
		} else {
			return this.head.urlPage;
		}
	}
	
	public void remove() {
		if(this.head != null) {
			NodeUrlPage aux = this.head;
			this.head = aux.pointer;
			aux.pointer = null;
			if(this.head == null) {
				this.last = null;
			}
			this.size--;
		}
	}
	
	public int size() {
		return this.size;
	}
	
	public boolean isEmpty() {
		return (this.size == 0);
	}
}
