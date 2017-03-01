
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class MultiByteClient implements Runnable{
	String hostname;
	int port_number;
	String filename;
	InetAddress host_address;
	Socket client_socket;
	DataOutputStream output;
	DataInputStream input;
	
	public MultiByteClient (String hostname, int port, String filename) throws
	IOException{
		port_number = port;
		host_address = InetAddress.getByName(hostname);
		this.filename = filename;
		client_socket = new Socket(host_address, port);
		input = new DataInputStream(client_socket.getInputStream());
		output = new DataOutputStream(client_socket.getOutputStream());
	}
	
	public void run()
	{
		try 
		{
			System.out.println("Attempting to retrieve '"+ filename + "'from "+ 
								client_socket.getInetAddress().getHostAddress() );
			output.writeUTF(filename);
			long file_size = input.readLong();
			if(file_size==0)
			{
				System.out.println("File '"+filename+"' is not avaliable.");
				return;
			}
			System.out.println("Receiving file '"+ filename +"'");
			FileOutputStream file_out = new FileOutputStream(filename);
			int number_read;
			byte[] buffer = new byte[1500];
			while ((number_read = input.read(buffer)) != -1)
			{
				file_out.write(buffer,0,number_read);				
			}
			file_out.close();
			input.close();
			output.close();
			System.out.println(" file received");
			
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
	}
}
