package hu.igal.sample;

public class Quote {
	private String quoteText;

	public String getQuoteText() {
		return quoteText;
	}

	public void setQuoteText(String quoteText) {
		this.quoteText = quoteText;
	}
	
	@Override
	public String toString() {
		return "The quote text is: " + quoteText;
	}
}
