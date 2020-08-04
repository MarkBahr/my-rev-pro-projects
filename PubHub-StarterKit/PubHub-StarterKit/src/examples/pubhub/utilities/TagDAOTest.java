package examples.pubhub.utilities;

import examples.pubhub.model.*;
import examples.pubhub.dao.TagDAO;
import examples.pubhub.dao.TagDAOImpl;
import java.util.List;
import java.time.LocalDate;

public class TagDAOTest {

	public static void main(String[] args) {
		
		TagDAO dao = new TagDAOImpl();
		List<Tag> list = dao.getTagsByISBN("1111111111111");
		
		for (int i = 0; i< list.size(); i++) {
			Tag t = list.get(i);
			System.out.println("--- Tags by Book ISBN ---");
			System.out.println(t);
		}
		
		List<Tag> list2 = dao.getAllTags();
		
		System.out.println("\n--- A List of All Tags ---");
		System.out.println(list2);
		
		Tag byTagName = dao.getTagsByName("Heroic Fantasy");
		
		System.out.println("\n--- Specific Tag By name");
		System.out.println(byTagName);
		
		Tag tag1 = new Tag("Historical Fiction", "1111111111112");
		
		Boolean didAdd = false;
		Boolean didUpdate = false;
		
		didAdd = dao.addTag(tag1);
		System.out.println(didAdd);
		
		
		Tag tag2 = new Tag("U.S. Historical Fiction", "1111111111112");
		didUpdate = dao.updateTag(tag2);
		System.out.println(didUpdate);
		
		/* Used the following two lines to successfully delete the above added tag, but then re-added it later. 
		didDelete = dao.deleteTagByISBN("1111111111112");
		System.out.println(didDelete);
		*/
		
		
	}

}
