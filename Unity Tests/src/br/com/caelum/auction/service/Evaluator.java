package br.com.caelum.auction.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.caelum.auction.domain.Auction;
import br.com.caelum.auction.domain.Bid;

public class Evaluator {

	private double greatestOfAll = Double.NEGATIVE_INFINITY;
	private double smallestOfAll = Double.POSITIVE_INFINITY;
	private List<Bid> biggestValues;

	public void evaluate(Auction auction) {
		for (Bid bid : auction.getBids()) {
			if (bid.getValue() > greatestOfAll) {
				greatestOfAll = bid.getValue();
			}
			if (bid.getValue() < smallestOfAll) {
				smallestOfAll = bid.getValue();
			}
		}
		biggestValues = new ArrayList<Bid>(auction.getBids());

		Collections.sort(biggestValues, new Comparator<Bid>() {
			public int compare(Bid o1, Bid o2) {
				if (o1.getValue() < o2.getValue())
					return 1;
				if (o1.getValue() > o2.getValue())
					return -1;
				return 0;
			}
		});
		
		biggestValues = biggestValues.subList(0, biggestValues.size() > 3 ? 3 : biggestValues.size());
	}

	public double getBiggestBid() {
		return greatestOfAll;
	}

	public double getSmallestBid() {
		return smallestOfAll;
	}

	public double getAverageBids() {
		return (greatestOfAll + smallestOfAll) / 2;
	}
	
	public List<Bid> getBiggestValues() {
		return biggestValues;
	}
}
