package com.joe.bookmark.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.joe.bookmark.convertor.BookmarkEntityToDataConvertor;
import com.joe.bookmark.data.BookmarkData;
import com.joe.bookmark.entity.Bookmark;
import com.joe.bookmark.form.BookmarkForm;
import com.joe.bookmark.repository.BookmarkRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/{userId}/bookmarks")
@Api(tags={"Bookmark Api"})
public class BookmarkRestController {

    @Autowired
    private BookmarkRepository bookmarkRepository;
    @Autowired
    private BookmarkEntityToDataConvertor convertor;
    
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value="List all bookmarks under {userId}")
    public List<BookmarkData> getBookmarks(@PathVariable String userId) {
    	List<BookmarkData> returnList = new ArrayList<>();
    	List<Bookmark> list = this.bookmarkRepository.findByUserId(userId);
    	if(!list.isEmpty()){
    		for (Bookmark bookmark : list) {
    			returnList.add(convertor.convert(bookmark));
			}
    	}
        return returnList;
    }

    @RequestMapping(value = "/{bookmarkId}", method = RequestMethod.GET)
    public BookmarkData getBookmark(@PathVariable String userId,
                         @PathVariable Long bookmarkId) {
        return convertor.convert(this.bookmarkRepository.getByUserIdAndId(userId, bookmarkId));
    }    
    
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create new Landlord", notes = "Creates new Landlord")
    @ApiResponses(value = {
			@ApiResponse(code = 400, message = "Fields are with validation errors"),
			@ApiResponse(code = 201, message = "") })
    
    public BookmarkData createBookmark(@ApiParam(name="userId",example="choelea", value="userId Description") @PathVariable  String userId,
    		@ApiParam(value="Bookmark form which is used to create ") @RequestBody  BookmarkForm bookmark) {

        Bookmark bookmarkInstance = new Bookmark(
                userId,
                bookmark.getHref(),
                bookmark.getDescription(),
                bookmark.getLabel());

        return convertor.convert(this.bookmarkRepository.save(bookmarkInstance));
    }
    
    @RequestMapping(value = "/{bookmarkId}", method = RequestMethod.DELETE)
    @ApiResponses(value = {
			@ApiResponse(code=400,message="Cannot find the bookmark by given bookmarkid under the login user"),
			@ApiResponse(code = 201, message = "") })
    public ResponseEntity<String> delete(@PathVariable String userId,
                         @PathVariable Long bookmarkId) {
    	Bookmark bookmark = bookmarkRepository.getByUserIdAndId(userId, bookmarkId);
    	if(bookmark==null){
    		return ResponseEntity.badRequest().body("Cannot find the bookmark");
    	}else{
    		bookmarkRepository.delete(bookmark);   	
    		return ResponseEntity.ok("Done success fully");
    	}
    }   

}
