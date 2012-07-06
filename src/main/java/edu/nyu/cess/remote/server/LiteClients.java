/**
 *
 */
package edu.nyu.cess.remote.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import edu.nyu.cess.remote.common.app.State;

/**
 * @author akira
 */
public class LiteClients implements LiteClientsObservable {

	HashMap<String, LiteClient> liteClients = new HashMap<String, LiteClient>();

	HashMap<String, String> hostNameToIPAddress = new HashMap<String, String>();
	HashMap<String, String> ipAddressToHostName = new HashMap<String, String>();

	ArrayList<LiteClientsObserver> observers = new ArrayList<LiteClientsObserver>();

	String sortedHostNames[];

	private final HashMap<String, String> hostNames = new HashMap<String, String>();

	public LiteClients() {
		hostNames.put("192.168.171.33", "Lab-01");
		hostNames.put("192.168.171.28", "Lab-02");
		hostNames.put("192.168.171.29", "Lab-03");
		hostNames.put("192.168.171.32", "Lab-04");
		hostNames.put("192.168.171.8", "Lab-05");
		hostNames.put("192.168.171.10", "Lab-06");
		hostNames.put("192.168.171.37", "Lab-07");
		hostNames.put("192.168.171.40", "Lab-08");
		hostNames.put("192.168.171.39", "Lab-09");
		hostNames.put("192.168.171.38", "Lab-10");
		hostNames.put("192.168.171.42", "Lab-11");
		hostNames.put("192.168.171.43", "Lab-12");
		hostNames.put("192.168.171.45", "Lab-13");
		hostNames.put("192.168.171.46", "Lab-14");
		hostNames.put("192.168.171.47", "Lab-15");
		hostNames.put("192.168.171.48", "Lab-16");
		hostNames.put("192.168.171.49", "Lab-17");
		hostNames.put("192.168.171.51", "Lab-18");
		hostNames.put("192.168.171.52", "Lab-19");
		hostNames.put("192.168.171.53", "Lab-20");
		hostNames.put("192.168.171.54", "Lab-21");
		hostNames.put("192.168.171.55", "Lab-22");
		hostNames.put("192.168.171.56", "Lab-23");
		hostNames.put("192.168.171.57", "Lab-24");

		sortedHostNames = new String[0];
	}

	public LiteClient put(String ipAddress, LiteClient liteClient) {

		String hostName = hostNames.get(ipAddress);
		if (hostName == null) {
			hostName = ipAddress;
		}

		liteClient.setHostName(hostName);

		LiteClient tempLiteClient = liteClients.put(ipAddress, liteClient);

		hostNameToIPAddress.put(hostName, ipAddress);
		ipAddressToHostName.put(ipAddress, hostName);

		sortHostNames();

		notifyClientAdded(ipAddress);

		return tempLiteClient;
	}

	public void updateState(State applicationState, String ipAddress) {
		LiteClient liteClient = liteClients.get(ipAddress);
		liteClient.setApplicationState(applicationState);
		notifyClientStateChanged(ipAddress);
	}

	public LiteClient remove(String ipAddress) {
		LiteClient liteClient = liteClients.get(ipAddress);

		hostNameToIPAddress.put(liteClient.getHostName(), liteClient.getIPAddress());
		ipAddressToHostName.put(liteClient.getIPAddress(), liteClient.getHostName());

		liteClients.remove(ipAddress);

		sortHostNames();

		notifyClientRemoved(ipAddress);

		return liteClient;
	}

	public String getIPAddressFromHostName(String hostName) {
		return hostNameToIPAddress.get(hostName);
	}

	public String getHostNameFromIPAddress(String ipAddress) {
		return ipAddressToHostName.get(ipAddress);
	}

	public LiteClient get(String ipAddress) {
		return liteClients.get(ipAddress);
	}

	/**
	 * Generates an array of sorted host strings from the hostNameToIPAddress
	 * ArrayList.
	 *
	 * @return sorted array of host names
	 */
	private void sortHostNames() {
		List<String> hostNameKeys = new ArrayList<String>(hostNameToIPAddress.keySet());
		TreeSet<String> sortedHostNameKeys = new TreeSet<String>(hostNameKeys);

		Object[] hstNames = sortedHostNameKeys.toArray();

		int sortedSetSize = hstNames.length;
		sortedHostNames = new String[sortedSetSize];

		for (int i = 0; i < sortedSetSize; i++) {
			sortedHostNames[i] = (String) hstNames[i];
		}

		Arrays.sort(sortedHostNames);
	}

	public String[] getSortedHostNames() {
		return sortedHostNames;
	}

	public void notifyClientAdded(String ipAddress) {
		for (LiteClientsObserver observer : observers) {
			observer.updateLiteClientAdded(ipAddress);
		}
	}

	public void notifyClientRemoved(String ipAddress) {
		for (LiteClientsObserver observer : observers) {
			observer.updateLiteClientRemoved(ipAddress);
		}
	}

	public void notifyClientStateChanged(String ipAddress) {
		for (LiteClientsObserver observer : observers) {
			observer.updateLiteClientStateChanged(liteClients.get(ipAddress));
		}
	}

	public int size() {
		return liteClients.size();
	}

	public void addObserver(LiteClientsObserver observer) {
		observers.add(observer);
	}

	public void removeObserver(LiteClientsObserver observer) {
		observers.remove(observer);
	}

}
