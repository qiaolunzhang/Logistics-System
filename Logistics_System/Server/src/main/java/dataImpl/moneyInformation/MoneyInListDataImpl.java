package dataImpl.moneyInformation;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.moneyInfomation.MoneyInListPO;
import dataservice.moneyInformation.MoneyInListDataService;

public class MoneyInListDataImpl extends UnicastRemoteObject implements MoneyInListDataService,Serializable {

	public MoneyInListDataImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public void add(MoneyInListPO moneyInList) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	public void delete(String moneyInListID) throws RemoteException {
		// TODO Auto-generated method stub
	
	}

	public void update(MoneyInListPO moneyInList) throws RemoteException {
		
	}

	public MoneyInListPO findOnID(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public MoneyInListPO findOnTime(String time) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<MoneyInListPO> showAll(String start_day, String end_day)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}



	

}
