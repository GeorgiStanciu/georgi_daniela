package com.example.georgi.shop.Services;

import com.example.georgi.shop.Helpers.Command;
import com.example.georgi.shop.Helpers.CommandResponse;
import com.example.georgi.shop.Helpers.DeserializateResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Georgi on 18-May-17.
 */

public class Client {

    private static final int BUFFER_SIZE = 2048;
    private Socket client;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    private String host;
    private int port;


    public Client(){
        host = "10.5.255.168";
        port = 9982;
    }


    public void connectToServer(){
        try {
            if(client == null){
                InetAddress serverAdr = InetAddress.getByName(host);
                client = new Socket(serverAdr, port);
                input = new ObjectInputStream(client.getInputStream());
                output = new ObjectOutputStream(client.getOutputStream());
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void disconnetFromServer() {
        if (client != null) {
            if (client.isConnected()) {
                try {
                    input.close();
                    output.close();
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public CommandResponse receiveDataFromServer(Command command){

        try{

            String gson = new Gson().toJson(command);
            output.writeObject(gson);
            output.flush();
            String json = (String) input.readObject();
            DeserializateResponse response = new DeserializateResponse(json, command.getCommand());

            CommandResponse commandResponse =  response.getResponse();
            return commandResponse;
        }catch (IOException e){

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
