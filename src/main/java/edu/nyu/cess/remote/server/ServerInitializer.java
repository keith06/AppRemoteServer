/**
 *
 */
package edu.nyu.cess.remote.server;

import edu.nyu.cess.remote.server.Server;
/**
 * @author akira
 */
public class ServerInitializer {

	public static void main(String[] args) {
		Server server = new Server();
		server.init();
	}

}
