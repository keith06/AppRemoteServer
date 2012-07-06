/**
 *
 */
package edu.nyu.cess.remote.server;

import edu.nyu.cess.remote.common.app.State;

public interface ClientProxyObserver {

	public void newClientUpdate(String ipAddress);

	public void applicationStateUpdate(String ipAddress, State applicationState);

	public void networkStatusUpdate(String ipAddress, boolean isConnected);

}
