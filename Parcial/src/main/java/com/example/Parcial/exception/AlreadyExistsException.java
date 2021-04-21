package com.example.Parcial.exception;

public class AlreadyExistsException extends RuntimeException{
    public AlreadyExistsException(String value){super("Value already exists");}
}