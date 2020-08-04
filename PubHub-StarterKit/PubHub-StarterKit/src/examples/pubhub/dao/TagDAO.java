package examples.pubhub.dao;

import java.util.List;

import examples.pubhub.model.Tag;

public interface TagDAO {

	public List<Tag> getAllTags();
	public Tag getTagsByName(String tagName);
	public List<Tag> getTagsByISBN(String isbn);
	
	public boolean addTag(Tag tag);
	public boolean updateTag(Tag tag);
	public boolean deleteTagByISBN(String isbn);
}
