package dataImpl.agency;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.ResultSet;

import po.agency.StaffPO;
import po.system.UserPO;
import utility.UserType;
import data.DataJDBCConnection;
import dataservice.agency.StaffDataService;

public class StaffDataImpl extends UnicastRemoteObject implements StaffDataService,Serializable {

	public StaffDataImpl() throws RemoteException {
		super();
	}

	public void add(StaffPO staff) throws RemoteException {
		String sql="insert into staff values("+staff.getId()+",'"+staff.getName()+"','"+staff.getSex()+"','"+staff.getPostion()
				+"','"+staff.getIDNum()+"','"+staff.getWorkingstarttime()+"','"+staff.getPhoneNum()+"','"+staff.getWage()+"',"+staff.getAgencyName()+")";
		DataJDBCConnection.update(sql);
		
	}

	public void delete(String id) throws RemoteException {
		String sql="delete from staff where id="+id;
		DataJDBCConnection.update(sql);
		
	}

	public void update(StaffPO staff) throws RemoteException {
		this.delete(staff.getId());
		this.add(staff);
		
	}

	public StaffPO find(String id) throws RemoteException {
		String sql="select from staff where id="+id;
		ResultSet rs2=(ResultSet) DataJDBCConnection.find(sql);
		StaffPO staff=null;
		try {
			rs2.next();
			staff=new StaffPO(rs2.getString("name"), rs2.getString("sex"), rs2.getString("postion"), rs2.getString("idNumber"), rs2.getString("workingstarttime"), rs2.getString("phoneNum"), rs2.getString("wage"), rs2.getString("agencyName"), rs2.getString("id"));
			
		} catch (SQLException e) {
			return null;
		}
		return staff;
	}


}