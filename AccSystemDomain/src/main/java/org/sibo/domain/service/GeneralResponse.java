package org.sibo.domain.service;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Objects;

@Component
public class GeneralResponse <E> implements Serializable {
    private final boolean succesful;
    private final transient  E payload;

    public GeneralResponse(boolean succesful, E payload){
        this.succesful = succesful;
        this.payload = payload;
    }
    public boolean isSuccesful(){
        return succesful;
    }

    public E getPayload() {
        return payload;
    }
    @Override
    public boolean equals(Object O){
        if(this == O) return true;
        if(O == null || getClass()!= O.getClass()) return false;
        return succesful == this.succesful && Objects.equals(payload, this.payload);
    }
    @Override
    public int hashCode(){return Objects.hash(succesful,payload);}

    @Override
    public String toString(){
        return "GetResponse{" +
                "succesful =" + succesful +
                "payload =" + payload+
                "}";
    }
}
