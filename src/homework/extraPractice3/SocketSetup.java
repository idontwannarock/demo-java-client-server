package homework.extraPractice3;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public interface SocketSetup {
	
	abstract Socket setupSocket() throws UnknownHostException, IOException;
	
	abstract void closeSocket() throws IOException;
}
