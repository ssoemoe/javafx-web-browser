package model;

public class History {
	private String urlString;
	private String timeStamp;
	
	History(String urlString, String timeStamp) {
		this.urlString = urlString;
		this.timeStamp = timeStamp;
	}

	/**
	 * @return the urlString
	 */
	public String getUrlString() {
		return urlString;
	}

	/**
	 * @return the timeStamp
	 */
	public String getTimeStamp() {
		return timeStamp;
	}
	
	public String oString() {
		return timeStamp + " --- " + urlString;
	}
	
}
