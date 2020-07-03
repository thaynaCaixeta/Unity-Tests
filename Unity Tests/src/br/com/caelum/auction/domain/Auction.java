package br.com.caelum.auction.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Auction {

	private String description;
	private List<Bid> bids;

	public Auction(String description) {
		this.description = description;
		this.bids = new ArrayList<Bid>();
	}

	public void proposes(Bid bid) {
		if (bids.isEmpty() || bidIsValid(bid.getUser())) {
			bids.add(bid);
		}
	}

	public void doubleLastPropose(User user) {
		double maxUserBid = getMaxUserBid(user);
		proposes(new Bid(user, maxUserBid * 2));
	}
	
	private boolean bidIsValid(User user) {
		return !isLastBidUser(user) && numberOfUserBids(user) < 5;
	}

	private int numberOfUserBids(User user) {
		int total = 0;
		for (Bid b : bids) {
			if (b.getUser().equals(user)) {
				total++;
			}
		}
		return total;
	}

	private boolean isLastBidUser(User user) {
		return bids.get(bids.size() - 1).getUser().equals(user);
	}

	private double getMaxUserBid(User user) {
		double maxValue = Double.NEGATIVE_INFINITY;
		for (Bid b : bids) {
			if(b.getUser().equals(user) && b.getValue() > maxValue) {
				maxValue = b.getValue();
			}
		}
		return maxValue;
	}

	public String getDescription() {
		return description;
	}

	public List<Bid> getBids() {
		return Collections.unmodifiableList(bids);
	}

}
