package examples.pubhub.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import examples.pubhub.model.Tag;
import examples.pubhub.utilities.DAOUtilities;

// Implementation for the TagDAO, so that it queries the database for Tag objects.
public class TagDAOImpl implements TagDAO {

	Connection connection = null;	// Connection to the database
	PreparedStatement stmt = null;	// This protects against SQL injection
	
	// --------------------------------------------------------------------------------------------------
	
	@Override
	public List<Tag> getAllTags() {
		List<Tag> tags = new ArrayList<>();
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM book_tags";	// My SQL query
			stmt = connection.prepareStatement(sql);	// Created prepared statement from the query
			
			ResultSet rs = stmt.executeQuery();
			
			// As long as the ResultSet actually contains results (while loop)...
			while (rs.next()) {
				// We'll populate a Tag object with information for each tag from the query result
				Tag tag = new Tag();
				
				// We want the columns mapped.
				tag.setTagName(rs.getString("tag_name"));
				tag.setIsbn13(rs.getString("isbn_13"));
				
				tags.add(tag);
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		// Return the list of Tag objects populated by the database.
		return tags;
	}
	
	// ---------------------------------------------------------------------------------------------------

	@Override
	public Tag getTagsByName(String tagName) {
		
		Tag tag = null;
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM book_tags WHERE tag_name LIKE ?";  // ? is string matching user-input
			stmt = connection.prepareStatement(sql);
			
			// Populate the 1st '?' with the tag_name and wild cards, between ' ' 
			stmt.setString(1, "%" + tagName + "%");
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				// We'll populate a Tag object with information for each tag from the query result
				tag = new Tag();
				
				// Each variable in our Tag object maps to a column in a row from the results.
				tag.setTagName(rs.getString("tag_name"));
				tag.setIsbn13(rs.getString("isbn_13"));
				
			}
			
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return tag;
	}

	// ---------------------------------------------------------------------------------------------------
	
	@Override
	public List<Tag> getTagsByISBN(String isbn) {
		
		List<Tag> tags = new ArrayList<>();
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM book_tags WHERE isbn_13 LIKE ?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, "%" + isbn + "%");
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Tag tag = new Tag();
				
				tag.setTagName(rs.getString("tag_name"));
				tag.setIsbn13(rs.getString("isbn_13"));
				
				tags.add(tag);
			}
			
			
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return tags;
	}
	
	// ---------------------------------------------------------------------------------------------------

	@Override
	public boolean addTag(Tag tag) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO book_tags VALUES (?, ?)"; 	// only 2 columns
			stmt = connection.prepareStatement(sql);
			
			// Set columns
			stmt.setString(1, tag.getTagName());
			stmt.setString(2, tag.getIsbn13());
			
			// If it added our book to the DB, it returns true.
			// This if statement executes the query and looks at the return value
			if (stmt.executeUpdate () != 0)
				return true;
			else
				return false;
		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}

	// ---------------------------------------------------------------------------------------------------
	
	@Override
	public boolean updateTag(Tag tag) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "UPDATE book_tags SET tag_name=? WHERE isbn_13=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, tag.getTagName());
			stmt.setString(2, tag.getIsbn13());
			
			System.out.println(stmt);
			
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}
	
	// ---------------------------------------------------------------------------------------------------

	@Override
	public boolean deleteTagByISBN(String isbn) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "DELETE FROM book_tags WHERE isbn_13=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, isbn);

			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}
	
	// ---------------------------------------------------------------------------------------------------
	
	private void closeResources() {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			System.out.println("Could not close statement!");
			e.printStackTrace();
		}
		
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}
	}

}
