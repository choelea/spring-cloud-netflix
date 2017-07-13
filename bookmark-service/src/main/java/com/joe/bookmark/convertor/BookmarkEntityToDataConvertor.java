package com.joe.bookmark.convertor;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.joe.bookmark.data.BookmarkData;
import com.joe.bookmark.entity.Bookmark;

@Component
public class BookmarkEntityToDataConvertor implements Converter<Bookmark, BookmarkData> {

	@Override
	public BookmarkData convert(Bookmark source) {
		return new BookmarkData(source.getUserId(), source.getHref(), source.getDescription(), source.getLabel());
	}

}
