package test;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;


public class BookScrabbleHandler implements ClientHandler
{
    PrintWriter out;
    Scanner in;

    @Override
    public void handleClient(InputStream inFromclient, OutputStream outToClient)
    {
        Scanner in = new Scanner(inFromclient);
        PrintWriter out = new PrintWriter(outToClient);
        String input = in.next();
        char question = input.charAt(0);
        input = input.substring(2);
        String[] newClientInputParts = input.split(",");

        if (question == 'C')
        {
            if (DictionaryManager.get().challenge(newClientInputParts))
                out.println("true\n");
            else
                out.println("false\n");

        }
        else if(question == 'Q')
        {
            if (DictionaryManager.get().query(newClientInputParts))
                out.println("true\n");
            else
                out.println("false\n");
        }
        out.flush();
    }

    @Override
    public void close()
    {
        if (in != null)
             in.close();
        if (out != null)
            out.close();
    }
}
