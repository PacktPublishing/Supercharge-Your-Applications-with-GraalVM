package org.abvijay.bozobooklibrary.booklibraryservice;

import javax.persistence.Entity;
import javax.persistence.Id;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import java.io.Serializable;


@Entity
public class BookLibrary extends PanacheEntityBase implements Serializable{
    @Id
	String bookID;
	
	@Id
    String userID;

	public String getBookID() {
		return this.bookID;
	}

	public void setBookID(String bookID) {
		this.bookID = bookID;
	}

	public String getUserID() {
		return this.userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

    
}
