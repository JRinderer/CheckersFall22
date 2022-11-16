package edu.lewisu;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ClientProcessing implements Runnable{
    private Socket client;
    private String client_message;
    private String client_message_type;
    private DataInputStream data_in;
    private DataOutputStream data_out;
    private static Game game = new Game();
    private String color;

    public ClientProcessing(Socket client_socket, Game game){
        this.game = game;
        try{
            this.client = client_socket;
            data_in = new DataInputStream(new BufferedInputStream(client.getInputStream()));
            data_out = new DataOutputStream(client.getOutputStream());
        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    private String parseClientMessage(String key){
        Map<String, String> map = new HashMap<>();
        String[] pair = this.client_message.split(",");
        for(int i = 0; i<pair.length;i++){
            String pair1 = pair[i];
            String[] keyVal = pair1.split(":");
            map.put(keyVal[0],keyVal[1]);
        }
        return map.get(key);
    }

    public void run(){
        String send_back ="";
        String piece_name="";
        String x_cord = "";
        String y_cord="";

        try{
            //type:get_board
            this.client_message = this.data_in.readUTF();
            this.client_message_type = parseClientMessage("type");

            if (client_message_type.equals("get_board")){
                send_back = game.seralizeBoard();
                this.data_out.writeUTF(send_back);
            }else if(client_message_type.equals("move")){
                this.color = parseClientMessage("color");
                piece_name = parseClientMessage("piece_name");
                //System.out.println(piece_name);
                x_cord = parseClientMessage("x_cord");
                y_cord = parseClientMessage("y_cord");
                send_back = game.processPlayerInput(piece_name,x_cord,y_cord,this.color,"");
                this.data_out.writeUTF(send_back);
            }else if(client_message_type.equals("color_set")){
                if(game.isColorAvail(parseClientMessage("color"))){
                    this.color = parseClientMessage("color");
                    send_back = "color:" + this.color;
                    send_back = game.seralizeBoard() + "," + send_back + "";
                    //System.out.println(send_back);
                    this.data_out.writeUTF(send_back);
                }else{
                    send_back = "error:" + "the color you selected " + parseClientMessage("color") + " is unavailable";
                    this.data_out.writeUTF(send_back);
                }
            }else if(client_message_type.equals("get_turn")){
                send_back = game.seralizeBoard();
                send_back = send_back + "," + game.processPlayerInput(piece_name,x_cord,y_cord, this.color,"get_turn");
                System.out.println("We are sending back " + send_back);
                this.data_out.writeUTF(send_back);
            }
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
}
