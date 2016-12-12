package apiBasNiveau;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

class ConnectHandler implements Runnable
{
	private Socket cs;

	public ConnectHandler(Socket csocket)
	{
		cs = csocket;
	}

	public void run()
	{
		try
		{
			PrintWriter out = 
				new PrintWriter(cs.getOutputStream(), true);
			BufferedReader in =
				new BufferedReader(
					new InputStreamReader(cs.getInputStream()));
			String inputLine = in.readLine();
			while(inputLine != null)
			{
				System.out.println("[from " 
							+ cs.getInetAddress() 
							+ "]: " 
							+ inputLine);
				inputLine = in.readLine();
			}
			System.out.println(cs.getInetAddress() + "disconnected");
			cs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}


public class SimpleServer 
{
	public static void main(String args[]) 
	{
		int portNumber = 1234;
		if(args.length == 1)
		{
			portNumber = Integer.parseInt(args[0]);
		}
		try 
		{
			ServerSocket ssocket = new ServerSocket(portNumber);
			while(true)
			{
				Socket csocket = ssocket.accept();
				System.out.println("accept connect from " 
						+ csocket.getInetAddress()
						);
				new Thread(new ConnectHandler(csocket)).start();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.exit(0);
	}
}

