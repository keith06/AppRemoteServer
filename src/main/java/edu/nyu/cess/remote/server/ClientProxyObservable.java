/**
 *
 */
package edu.nyu.cess.remote.server;

import edu.nyu.cess.remote.app.State;

/**
 * @author akira
 */
public interface ClientProxyObservable {

	public boolean addObserver(ClientProxyObserver clientProxyObserver);

	public boolean deleteObserver(ClientProxyObserver clientProxyObserver);

	public void notifyApplicationStateReceived(State applicationState, String ipAddress);

	public void notifyNetworkClientAdded(String ipAddress);

	public void notifyNetworkStatusChange(String ipAddress, boolean isConnected);

}