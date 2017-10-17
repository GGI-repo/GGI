package company.ggi.service;

import company.ggi.model.Party;
import company.ggi.model.Play;
import company.ggi.model.UserGroup;

import java.util.List;

public interface PlayService {
    Play create(Play newPlay) throws Exception;

    Play update(Play play) throws Exception;

    List<Play> findAll() throws Exception;

    Play findByUserGroupAndParty(UserGroup userGroup, Party party) throws Exception;

    Play delete(Play play) throws Exception;
}
