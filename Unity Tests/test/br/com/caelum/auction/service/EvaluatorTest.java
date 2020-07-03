package br.com.caelum.auction.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.auction.domain.Auction;
import br.com.caelum.auction.domain.Bid;
import br.com.caelum.auction.domain.User;

public class EvaluatorTest {

	/*
	 * Equivalence classes considered: 
	 * --- Bids
	 * - Bids with increasing values 
	 * - Bids with non increasing values 
	 * - Bids with unclassified values 
	 * - Bids with only one value in the list
	 * - Find the top three bids
	 * 
	 */
	
	private Evaluator auctioneer;
	private User joao;
	private User jose;
	private User maria;
	
	
	/* Build the test scene */
	@Before
	public void createEvaluator() {
		this.auctioneer = new Evaluator();
		
		this.joao = new User("João");
		this.jose = new User("José");
		this.maria = new User("Maria");
	}

	@Test
	public void bidsWithIncreasingValues() {
		
		Auction auction = new Auction("Playstation 5 - New");

		// Execute an action (method)
		auction.proposes(new Bid(joao, 250.0));
		auction.proposes(new Bid(jose, 300.0));
		auction.proposes(new Bid(maria, 400.0));

		auctioneer.evaluate(auction);

		// Validate the result
		double biggestOfAll = 400.0;
		double smallestOfAll = 250.0;
		double averageBids = 325.0;

		assertEquals(biggestOfAll, auctioneer.getBiggestBid(), 0.00001);
		assertEquals(smallestOfAll, auctioneer.getSmallestBid(), 0.00001);
		assertEquals(averageBids, auctioneer.getAverageBids(), 0.00001);
	}

	@Test
	public void bidsWithNonIncreasingValues() {

		Auction auction = new Auction("Playstation 5 - New");

		// Execute an action (method)
		auction.proposes(new Bid(joao, 400.0));
		auction.proposes(new Bid(jose, 300.0));
		auction.proposes(new Bid(maria, 250.0));

		auctioneer.evaluate(auction);

		// Validate the result
		double biggestOfAll = 400.0;
		double smallestOfAll = 250.0;
		double averageBids = 325.0;

		assertEquals(biggestOfAll, auctioneer.getBiggestBid(), 0.00001);
		assertEquals(smallestOfAll, auctioneer.getSmallestBid(), 0.00001);
		assertEquals(averageBids, auctioneer.getAverageBids(), 0.00001);
	}
	
	@Test
	public void bidsWithUnclassifiedValues() {

		Auction auction = new Auction("Playstation 5 - New");

		// Execute an action (method)
		auction.proposes(new Bid(joao, 300.0));
		auction.proposes(new Bid(jose, 250.0));
		auction.proposes(new Bid(maria, 400.0));

		auctioneer.evaluate(auction);

		// Validate the result
		double biggestOfAll = 400.0;
		double smallestOfAll = 250.0;
		double averageBids = 325.0;

		assertEquals(biggestOfAll, auctioneer.getBiggestBid(), 0.00001);
		assertEquals(smallestOfAll, auctioneer.getSmallestBid(), 0.00001);
		assertEquals(averageBids, auctioneer.getAverageBids(), 0.00001);
	}
	
	@Test
	public void bidsWithOnlyOneValue() {

		Auction auction = new Auction("Playstation 5 - New");

		// Execute an action (method)
		auction.proposes(new Bid(joao, 300.0));

		auctioneer.evaluate(auction);

		// Validate the result
		double biggestOfAll = 300.0;
		double smallestOfAll = 300.0;
		double averageBids = 300.0;

		assertEquals(biggestOfAll, auctioneer.getBiggestBid(), 0.00001);
		assertEquals(smallestOfAll, auctioneer.getSmallestBid(), 0.00001);
		assertEquals(averageBids, auctioneer.getAverageBids(), 0.00001);
	}
	
	@Test
	public void findTopThreeBids() {

		Auction auction = new Auction("Playstation 5 - New");

		// Execute an action (method)
		auction.proposes(new Bid(joao, 100.0));
		auction.proposes(new Bid(maria, 200.0));
		auction.proposes(new Bid(joao, 300.0));
		auction.proposes(new Bid(maria, 400.0));

		auctioneer.evaluate(auction);

		// Validate the result
		List<Bid> threeBiggestValues = auctioneer.getBiggestValues();
		
		assertEquals(3, threeBiggestValues.size());
		assertEquals(400.0, threeBiggestValues.get(0).getValue(), 0.00001);
		assertEquals(300.0, threeBiggestValues.get(1).getValue(), 0.00001);
		assertEquals(200.0, threeBiggestValues.get(2).getValue(), 0.00001);
	}
}
