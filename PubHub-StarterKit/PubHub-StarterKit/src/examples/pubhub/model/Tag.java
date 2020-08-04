package examples.pubhub.model;

public class Tag {

	private String tagName;
	private String isbn13;
	
	// Constructor when a tag is specified
	public Tag(String tagname, String isbn) {
		this.tagName = tagname;
		this.isbn13 = isbn;
	}
	
	// Default constructor
	public Tag() {
		this.tagName = null;
		this.isbn13 = null;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getIsbn13() {
		return isbn13;
	}

	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}
	
	// We want the string representation of the complex hash number.
	@Override
	public String toString() {
		return String.format("Tag Name: " + tagName + "\nISBN: " + isbn13);
	}
	
}
