package CouponOrganizer.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Coupon {
	private int id;
	private String store;
	private String deal;
	private String comment;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date expirationDate;
    private Date dateCreated;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dateDeleted;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public String getDeal() {
		return deal;
	}

	public void setDeal(String deal) {
		this.deal = deal;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

	public Date getDateDeleted() {
		return dateDeleted;
	}

	public void setDateDeleted(Date dateDeleted) {
		this.dateDeleted = dateDeleted;
	}
}
