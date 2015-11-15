
package rmiImpl.system;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.system.SystemLogPO;
import dataservice.system.SystemLogDataService;

/**
 * @author 这菜咸了
 * 该类为远程对象的实现类,需要继承unicastRemoteObject
 */
public class SystemLogDataImpl extends UnicastRemoteObject implements SystemLogDataService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SystemLogDataImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArrayList<SystemLogPO> showAll(String time1,String time2)
			throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<SystemLogPO> systemLogs=new ArrayList<SystemLogPO>();
		systemLogs.add(new SystemLogPO(time1, "捡了一分钱", "王大锤"));
		return systemLogs;
	}

	public void add(SystemLogPO systemLog) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}