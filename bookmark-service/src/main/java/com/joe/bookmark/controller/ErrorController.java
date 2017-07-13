package com.joe.bookmark.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.joe.bookmark.entity.Bookmark;

/**
 * Just for testing
 * @author Administrator
 *
 */
@RestController
class ErrorController{
	@RequestMapping(value = "/500", method = RequestMethod.GET)
    @ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
    Bookmark internalError() {
        return new Bookmark("000","wrong","Internal Error Happened","No lable");
    }
}