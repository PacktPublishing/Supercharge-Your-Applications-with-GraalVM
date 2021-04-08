package org.abvijay.bozobooklibrary.bookinfoservice.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookInfoSearchResponse {
    String kind;
    int totalItems;

    List<BookItem> items;

	public String getKind() {
		return this.kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public int getTotalItems() {
		return this.totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

	public List<BookItem> getItems() {
		return this.items;
	}

	public void setItems(List<BookItem> items) {
		this.items = items;
	}
}
