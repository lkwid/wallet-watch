package lkwid.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Transaction {
	@Id
	@GeneratedValue
	private long id;
	private BigDecimal transfer;
	private LocalDate date;
	private boolean profit;
	@ManyToOne
	private Account account;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public BigDecimal getTransfer() {
		return transfer;
	}
	public void setTransfer(BigDecimal transfer) {
		this.transfer = transfer;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public boolean isProfit() {
		return profit;
	}
	public void setProfit(boolean profit) {
		this.profit = profit;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}

}
