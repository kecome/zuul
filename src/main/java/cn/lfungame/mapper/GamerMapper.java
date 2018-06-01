package cn.lfungame.mapper;

import cn.lfungame.model.Gamer;

import java.util.List;

/**
 * @Auther: xuke
 * @Date: 2018/5/31 18:13
 * @Description:
 */
public interface GamerMapper {
    Gamer selectGamerById(Long id);
    void insertGamer(Gamer gamer);
    Gamer selectGamerByWxId(String wxId);
    List<Gamer> selectGamerByDeviceId(String deviceId);
    void updateGamer(Gamer gamer);
    Gamer selectGamerByPhoneNumber(String phoneNumber);
}
