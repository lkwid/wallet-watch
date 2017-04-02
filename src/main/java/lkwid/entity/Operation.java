package lkwid.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Operation {
	@Id
	@GeneratedValue
	private long id;
	private LocalDate date;
	private BigDecimal transfer;
	private boolean profit;
	private String description;
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="account_id")	
	private Account account;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public BigDecimal getTransfer() {
		return transfer;
	}
	public void setTransfer(BigDecimal transfer) {
		this.transfer = transfer;
	}
	public boolean isProfit() {
		return profit;
	}
	public void setProfit(boolean profit) {
		this.profit = profit;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}

}
