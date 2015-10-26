/**
 * 2015年10月23日
 *author:
 *description:收款单PO
 */
package po;

import java.io.Serializable;

/**
 * @author 谭期友
 *
 */
public class MoneyOutListPO extends ListPO implements Serializable{
	/**
	 * 日期
	 */
	private String date;
	
	/**
	 * 金额
	 */
	private double money;
	
	/**
	 * 付款人
	 */
	private String payer;
	
	/**
	 * 银行账号
	 */
	private String accountNum;
	
	/**
	 * 付款方式
	 */
	private String paymentMethod;
	
	/**
	 * 构造方法
	 * @param date
	 * @param money
	 * @param payer
	 * @param accountNum
	 * @param paymentMethod
	 */
	public MoneyOutListPO(String date,double money,String payer,String accountNum,
			String paymentMethod){
		this.date = date;
		this.money = money;
		this.payer = payer;
		this.accountNum = accountNum;
		this.paymentMethod = paymentMethod;
	}

	public String getDate() {
		return date;
	}

	public double getMoney() {
		return money;
	}

	public String getPayer() {
		return payer;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}
}