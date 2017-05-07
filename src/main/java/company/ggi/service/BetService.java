package company.ggi.service;

import company.ggi.model.Bet;

import java.util.List;

/**
 * Created by Adam on 30/04/2017.
 */
public interface BetService  {

    public Bet create(Bet newBet) throws Exception;

    public Bet update(Bet newBet) throws Exception;

    public Bet findById(int id) throws  Exception;

    public List<Bet> findAll() throws Exception;

    public Bet delete(int id) throws Exception;
}
