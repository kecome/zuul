package cn.lfungame.mapper;

import cn.lfungame.model.Gamer;

/**
 * @Auther: xuke
 * @Date: 2018/5/31 18:13
 * @Description:
 */
public interface GamerMapper {
    Gamer selectGamerById(Long id);
    void insertGamer(Gamer gamer);
    Gamer selectGamerByWxId(Long wxId);
}
