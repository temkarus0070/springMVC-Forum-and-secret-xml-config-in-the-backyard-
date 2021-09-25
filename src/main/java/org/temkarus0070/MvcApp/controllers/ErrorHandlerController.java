package org.temkarus0070.MvcApp.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.temkarus0070.MvcApp.Exceptions.PostNotFoundException;
import org.temkarus0070.MvcApp.Exceptions.SectionNotFoundException;

@RestControllerAdvice
public class ErrorHandlerController {
    @ExceptionHandler(value = PostNotFoundException.class)
    public ResponseEntity postNotFound(){
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.set("error","Post not found");
        return new ResponseEntity(httpHeaders, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = SectionNotFoundException.class)
    public ResponseEntity sectionNotFound(){
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.set("error","section not found");
        return new ResponseEntity(httpHeaders,HttpStatus.NOT_FOUND);
    }
}
