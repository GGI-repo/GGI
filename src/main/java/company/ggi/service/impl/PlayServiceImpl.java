package company.ggi.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import company.ggi.dao.PartyDao;
import company.ggi.dao.PlayDao;
import company.ggi.dao.UserGroupDao;
import company.ggi.model.Party;
import company.ggi.model.Play;
import company.ggi.model.UserGroup;
import company.ggi.service.PlayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

public class PlayServiceImpl implements PlayService {

    @Resource
    PlayDao playDao;

    @Autowired
    PartyDao partyDao;
    @Autowired
    UserGroupDao userGroupDao;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public Play create(Play newPlay) throws Exception {
        if(null == newPlay){
            logger.debug("Can't create play for null object", mapper.writeValueAsString(newPlay));
            throw new Exception("Null object");
        }

        if(newPlay.getParty().equals(new Party()) || newPlay.getUserGroup().equals(new UserGroup())){
            logger.debug("Can't create play for empty userGroup or party", mapper.writeValueAsString(newPlay));
            throw new Exception("Empty userGroup or party");
        }

        if(partyDao.findByName(newPlay.getParty().getName())== null || userGroupDao.findByName(newPlay.getUserGroup().getName())==null){
            logger.debug("Can't create play for none existing userGroup or party", mapper.writeValueAsString(newPlay));
            throw new Exception("None existing userGroup or party");
        }

        if(null != playDao.findByUserGroupAndParty(newPlay.getUserGroup(), newPlay.getParty())){
            logger.debug("Can't create existing play", mapper.writeValueAsString(newPlay));
            throw new Exception("Existing play");
        }

        newPlay = playDao.save(newPlay);

        return newPlay;
    }

    @Override
    public Play update(Play play) throws Exception {
        if(null == play){
            logger.debug("Can't update play for null object", mapper.writeValueAsString(play));
            throw new Exception("Null object");
        }
        if(play.getParty().equals(new Party()) || play.getUserGroup().equals(new UserGroup())){
            logger.debug("Can't create play for empty userGroup or party", mapper.writeValueAsString(play));
            throw new Exception("Empty userGroup or party");
        }

        if(partyDao.findByName(play.getParty().getName())== null || userGroupDao.findByName(play.getUserGroup().getName())==null){
            logger.debug("Can't update play for none existing userGroup or party", mapper.writeValueAsString(play));
            throw new Exception("None existing userGroup or party");
        }

        if(playDao.findOne(play.getId()) == null){
            logger.debug("Can't update none existing play", mapper.writeValueAsString(play));
            throw new Exception("None existing play");
        }

        play = playDao.save(play);

        return play;
    }

    @Override
    public List<Play> findAll() throws Exception {
        List<Play> plays = playDao.findAll();
        if(plays.isEmpty()){
            logger.debug("No play found");
            throw new Exception("No play found");
        }
        return plays;
    }

    @Override
    public Play findByUserGroupAndParty(UserGroup userGroup, Party party) throws Exception {
        if(userGroup.getId() == null){
            logger.debug("Can't find null UserGroup", mapper.writeValueAsString(userGroup));
            throw new Exception("Null UserGroup");
        }

        if(party.getId() == null){
            logger.debug("Can't find null party", mapper.writeValueAsString(party));
            throw new Exception("Null party");
        }

        if(partyDao.findOne(party.getId()) == null){
            logger.debug("Party not found", mapper.writeValueAsString(party));
            throw new Exception("Not found party");
        }

        if (userGroupDao.findOne(userGroup.getId()) == null){
            logger.debug("UserGroup not found", mapper.writeValueAsString(userGroup));
            throw new Exception("Not found UserGroup");
        }

        Play play = playDao.findByUserGroupAndParty(userGroup, party);
        if(play == null){
            logger.debug("No play found", mapper.writeValueAsString(play));
            throw new Exception("No play found");
        }

        return play;
    }

    @Override
    public Play delete(Play play) throws Exception {
        if(play == null || play.getId() == null){
            logger.debug("Can't delete null object", mapper.writeValueAsString(play));
            throw new Exception("Can't delete null object");
        }

        if(playDao.findOne(play.getId()) == null) {
            logger.debug("Play not found", mapper.writeValueAsString(play));
            throw new Exception("Not found play");
        }
        playDao.delete(play.getId());
        return play;
    }
}
