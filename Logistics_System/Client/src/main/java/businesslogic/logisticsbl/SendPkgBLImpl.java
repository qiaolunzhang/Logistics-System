package businesslogic.logisticsbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.constantinfo.DistanceChartPO;
import po.list.OrderListPO;
import dataservice.constantinfo.ConstantDataService;
import dataservice.list.OrderListDataService;
import utility.ResultMessage;
import vo.OrderListVO;
import businesslogic.rmi.RMIHelper;
import businesslogicservice.logisticsblservice.SendPkgBLService;

public class SendPkgBLImpl implements SendPkgBLService {
	OrderListDataService service1=null;
	ConstantDataService service2=null;
	public SendPkgBLImpl(){
		this.service1=(OrderListDataService)RMIHelper.find("OrderListDataService");
		this.service2=(ConstantDataService)RMIHelper.find("ConstantDataService");
	}
	public OrderListVO createMoneyAndDate(OrderListVO orderListVO) {
		try {
			ArrayList<OrderListPO> list=service1.showAll("2015-9-1", "2015-12-31");
			int days=0;
			int count=0;
			for(OrderListPO orderListPO:list){
				if(orderListPO.getSenderAddress()==orderListVO.getSenderAddress()||
						orderListPO.getReceiverAddress()==orderListVO.getReceiverAddress()||
						orderListPO.getCategory()==orderListVO.getCategory()){
					count++;
					String[] temp1=orderListPO.getDepartTime().split("-");
					String[] temp2=orderListPO.getArriveTime().split("-");
					days+=((Integer.parseInt(temp2[0])-Integer.parseInt(temp1[0]))*360
							+(Integer.parseInt(temp2[1])-Integer.parseInt(temp1[1]))*30
							+Integer.parseInt(temp2[2])-Integer.parseInt(temp1[2]));
					//估计值，按照一年360天，一月30天计算，有时误差可能较大
				}
			}
			if(count!=0){
				days=days/count;
			}
			String[] temp=orderListVO.getDepartTime().split("-");
			int year=Integer.parseInt(temp[0]);
			int month=Integer.parseInt(temp[1]);
			int day=Integer.parseInt(temp[2]);
			year+=days/360;
			month+=(days%360)/30;
			days+=((days%360)%30);
			orderListVO.setArriveTime(year+"-"+month+"-"+day);
			//估计时间完成
			
			
			String cityName1=orderListVO.getSenderAddress();
			String cityName2=orderListVO.getReceiverAddress();
			for(int i=0;i<cityName1.length();i++){
				if(cityName1.charAt(i)=='市'){
					cityName1=cityName1.substring(0,i+1);
					break;
				}
			}
			for(int i=0;i<cityName2.length();i++){
				if(cityName2.charAt(i)=='市'){
					cityName2=cityName2.substring(0,i+1);
					break;
				}
			}
			DistanceChartPO distanceChart=service2.showDistanceChart();
			String[][] chart = distanceChart.getDistanceChart();
			int distance=0;
			for(int i=0;i<chart.length;i++){
				if(chart[0][i]==cityName1){
					for(int j=0;j<chart.length;j++){
						if(chart[j][0]==cityName2){
							distance=Integer.parseInt(chart[i][j]);
							break;
						}
					}
					break;
				}
			}
			
			double price_kiloGram=0.0;
			switch(orderListVO.getCategory()){
				case STANDARD:price_kiloGram=service2.showPriceChart().getStandard();break;
				case ECONOMIC:price_kiloGram=service2.showPriceChart().getEconomic();break; 
				case EXPRESS:price_kiloGram=service2.showPriceChart().getExpress();break;
				default:break;
			}
			
			double price=price_kiloGram*distance*orderListVO.getWeight()/1000;
			switch(orderListVO.getPkgType()){
				case WOODEN:price+=10;break;
				case PAPER:price+=5;break;
				case PLASTIC:price+=1;break;
				default: break;
			}
			orderListVO.setPackPrice(price);
			//计算价格完成
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return orderListVO;
	}

	public ResultMessage createOrderList(OrderListVO orderListVO) {
		// TODO Auto-generated method stub
		OrderListPO result=new OrderListPO(orderListVO.getSenderName(), orderListVO.getSenderAddress(), orderListVO.getSenderTeleNumber(),
				orderListVO.getReceiverName(), orderListVO.getReceiverAddress(), orderListVO.getReceiverTeleNumber(),
				orderListVO.getNumber(),orderListVO.getWeight(),orderListVO.getVolume(),orderListVO.getName(),orderListVO.getCategory()
				,orderListVO.getPkgState(),orderListVO.getPackPrice(),orderListVO.getBarCode(),orderListVO.getPkgType(),orderListVO.getDepartTime(),orderListVO.getArriveTime());
		try{
			service1.add(result);
		}catch (RemoteException e) {
			e.printStackTrace();
		}
		return new ResultMessage(true, "成功保存订单！");
	}

}