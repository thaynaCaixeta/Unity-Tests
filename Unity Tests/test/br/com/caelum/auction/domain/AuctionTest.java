package br.com.caelum.auction.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class AuctionTest {

	@Test
	public void mustReceiveOneBid() {
		Auction auction = new Auction("Macbook Pro 15\"");
		assertEquals(0, auction.getBids().size());

		auction.proposes(new Bid(new User("Steve Jobs"), 2000));

		// We must ensure that the size and the values of the list are correct
		assertEquals(2000, auction.getBids().get(0).getValue(), 0.00001);
	}

	@Test
	public void mustReceiveManyBids() {
		Auction auction = new Auction("Macbook Pro 15\"");
		auction.proposes(new Bid(new User("Steve Jobs"), 2000));
		auction.proposes(new Bid(new User("Steve Wozniak"), 3000));

		assertEquals(2, auction.getBids().size());
		assertEquals(2000, auction.getBids().get(0).getValue());
		assertEquals(3000, auction.getBids().get(1).getValue());
	}

	@Test
	public void twoBidsFollowedByTheSameUserAreProhibited() {
		/*
		 * First implementation: Auction class ignore the second bid (baby steps
		 * behavior)
		 */

		Auction auction = new Auction("Macbook Pro 15\"");
		User steveJobs = new User("Steve Jobs");

		auction.proposes(new Bid(steveJobs, 2000.0));
		auction.proposes(new Bid(steveJobs, 3000.0));

		assertEquals(1, auction.getBids().size());
		assertEquals(2000.0, auction.getBids().get(0).getValue());
	}

	@Test
	public void allowOnlyFiveBidsPerUser() {
		Auction auction = new Auction("Macbook Pro 15\"");
		User steveJobs = new User("Steve Jobs");
		User billGates = new User("Bill Gates");

		auction.proposes(new Bid(steveJobs, 2000.0));
		auction.proposes(new Bid(billGates, 3000.0));

		auction.proposes(new Bid(steveJobs, 4000.0));
		auction.proposes(new Bid(billGates, 5000.0));

		auction.proposes(new Bid(steveJobs, 6000.0));
		auction.proposes(new Bid(billGates, 7000.0));

		auction.proposes(new Bid(steveJobs, 8000.0));
		auction.proposes(new Bid(billGates, 9000.0));

		auction.proposes(new Bid(steveJobs, 10000.0));
		auction.proposes(new Bid(billGates, 11000.0));

		// Must be ignored
		auction.proposes(new Bid(steveJobs, 12000.0));

		int auctionSize = auction.getBids().size();
		assertEquals(10, auctionSize);
		assertEquals(11000, auction.getBids().get(auctionSize - 1).getValue(), 0.00001);
	}
	
	@Test
	public void mustDoubleTheLastBid() {
		Auction auction = new Auction("Macbook Pro 15\"");
		User steveJobs = new User("Steve Jobs");
		User billGates = new User("Bill Gates");
		
		auction.proposes(new Bid(steveJobs, 2000.0));
		auction.proposes(new Bid(billGates, 3000.0));
		
		auction.doubleLastPropose(steveJobs);
		
		int bidSize = auction.getBids().size();
		assertEquals(3, bidSize);
		assertEquals(4000.0, auction.getBids().get(bidSize - 1).getValue());
		
	}
}
