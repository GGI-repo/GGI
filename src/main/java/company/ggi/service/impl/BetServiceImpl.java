package company.ggi.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import company.ggi.dao.BetDao;
import company.ggi.dao.UserDao;
import company.ggi.exception.BetException;
import company.ggi.model.Bet;
import company.ggi.model.User;
import company.ggi.service.BetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Adam on 30/04/2017.
 */

@Service
public class BetServiceImpl implements BetService {

    @Resource
    private BetDao betRepository;

    @Resource
    private UserDao userDao;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    @Transactional(rollbackFor = BetException.class)
    public Bet create(Bet newBet) throws Exception {
        logger.info("Trying to create a new bet");
        Bet bet = newBet;

        if(bet == null)
            throw new BetException(BetException.BET_WITH_NULL_VALUE);

        if(bet.getId() != null) {
            if (betRepository.findOne(bet.getId()) != null)
                throw new BetException(BetException.BET_ALREADY_EXIST);
        }
       /*
        This exception is momentarily commented
        if(bet.getCredit() == 0)
            throw new BetException(BetException.BET_CREDIT_EQUAL_ZERO);
        */

        return betRepository.save(bet);
    }

    @Override
    @Transactional(rollbackFor = BetException.class)
    public Bet update(Bet newBet) throws Exception {
        logger.info("Trying to update bet ");
        Bet betToUpdate = betRepository.findOne(newBet.getId());

        if (betToUpdate == null) {
            logger.error("Bet not found " + mapper.writeValueAsString(newBet));
            throw new BetException(BetException.BET_NOT_FOUND);
        }

        /*
         This exception is momentarily commented
         if(betToUpdate.getCredit() == 0 )
             throw new BetException(BetException.BET_CREDIT_EQUAL_ZERO);
        */

        betToUpdate.setCredit(newBet.getCredit());
        betToUpdate.setUser(newBet.getUser());
        betToUpdate.setParty(newBet.getParty());

        betRepository.save(betToUpdate);

        return betToUpdate;
    }

    @Override
    @Transactional(rollbackFor = BetException.class)
    public Bet findById(int id) throws Exception {
        logger.info("Trying to find bet ");
        Bet bet = betRepository.findOne(id);

        if (bet == null) {
            logger.error("Bet with id " + id + " not found " );
            throw new BetException(BetException.BET_NOT_FOUND);
        }

        return bet;
    }

    @Override
    public List<Bet> findAll() throws Exception {
        logger.info("Trying to get all categories ");
        return betRepository.findAll();
    }

    @Override
    @Transactional(rollbackFor = BetException.class)
    public Bet delete(int id) throws Exception {
        logger.info("Trying to delete bet ");
        Bet betToDelete = betRepository.findOne(id);

        if (betToDelete == null) {
            logger.error("Bet with id " + id + " not found " );
            throw new BetException(BetException.BET_NOT_FOUND);
        }

        betRepository.delete(betToDelete);
        return betToDelete;
    }
}
