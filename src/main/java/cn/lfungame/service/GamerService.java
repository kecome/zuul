package cn.lfungame.service;

import cn.lfungame.mapper.GamerMapper;
import cn.lfungame.model.Gamer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: xuke
 * @Date: 2018/5/31 18:25
 * @Description:
 */
@Service
public class GamerService {
    @Autowired
    private GamerMapper gamerMapper;

    public Gamer selectGamerById(Long id) {
       return gamerMapper.selectGamerById(id);
    }
    public void insertGamer(Gamer gamer) {
        gamerMapper.insertGamer(gamer);
    }
    public Gamer selectGamerByWxId(Long wxId) {
        return gamerMapper.selectGamerByWxId(wxId);
    }
}
