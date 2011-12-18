package com.android.metier;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;

import com.android.reseau.client.Client;

public class DataConnexion {
	private HashMap<String, Object> map;
	
	public DataConnexion(String pseudo, String ip, String port, String avatar)
	{
		map = new HashMap<String, Object>();
		
		map.put("pseudo", pseudo);
		map.put("ip", ip);
		map.put("port", port);
		map.put("avatar", avatar);
	}
	
	public String get(Object key) {
		return "" + map.get(key);
	}	
	
	public void createClient() throws NumberFormatException, UnknownHostException, IOException
	{
		if (!map.containsKey("client"))
			map.put("client", new Client(get("pseudo"), get("ip"), get("port"), get("avatar")));
	}
	
	public void createInfo(String info)
	{
		map.put("info", info);
	}
	
	public String getPseudo()
	{
		return get("pseudo");
	}
	
	public String getIp()
	{
		return get("ip");
	}
	
	public String getPort()
	{
		return get("port");
	}
	
	public String getInfo()
	{
		return get("info");
	}
	
	public String getAvatar()
	{
		return get("avatar");
	}
	
	public Client getClient()
	{
		if (map.containsKey("client"))
			return (Client) map.get("client");
		return null;
	}
}
