package com.oc.dandfriends.exceptions;

public class EntityNotFoundException extends RuntimeException {

        public EntityNotFoundException(String message){
            super(message);
        }
}
