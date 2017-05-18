package com.example.georgi.shop.Helpers;

import com.example.georgi.shop.Models.CommandEnum;

import java.io.Serializable;

/**
 * Created by Georgi on 18-May-17.
 */

public class Command implements Serializable {

    private CommandEnum command;
    private Object object;

    public Command(CommandEnum command){
        this.command = command;
    }

    public Command(CommandEnum command, Object object){
        this.command = command;
        this.object = object;
    }

    public Object getObject(){
        return object;
    }

    public CommandEnum getCommand(){
        return command;
    };
}
