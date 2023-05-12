package Model;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class HostClientHandler implements ClientHandler
{
    public PrintWriter out;
    public Scanner in;

    @Override
    public void handleClient(InputStream inFromclient, OutputStream outToClient)
    {
        Scanner in = new Scanner(inFromclient);
        PrintWriter out = new PrintWriter(outToClient);
        String input = in.next();
        char question = input.charAt(0);
        input = input.substring(2);
       // String[] newClientInputParts = input.split(",");

        if (question == '1')
        {
            // needs to send to host word to place
            // needs to return the score for the words
        }

        if (question == '2')
        {
            // needs to send to host a challenge
            // needs to return if the word has accepted
        }

        if (question == '3')
        {
            // needs to send the host a string for challenge
            // needs to show the tile to the client
        }

        out.flush();
    }

    @Override
    public void close() {
        if (in != null)
            in.close();
        if (out != null)
            out.close();
    }
}
