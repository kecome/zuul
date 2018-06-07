package cn.lfungame.controller;

import cn.lfungame.exception.BusinessException;
import cn.lfungame.exception.ErrorInfo;
import cn.lfungame.interceptor.LoginIgnore;
import cn.lfungame.model.Gamer;
import cn.lfungame.model.Token;
import cn.lfungame.service.GamerService;
import cn.lfungame.service.SmsService;
import cn.lfungame.service.TokenService;
import cn.lfungame.util.ResponseMsg;
import cn.lfungame.util.SnowflakeIdWorker;
import cn.lfungame.util.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: xuke
 * @Date: 2018/5/15 18:23
 * @Description: 登录fdf
 */
@RestController
@RequestMapping("/liaowan")
public class LoginController {

    @Autowired
    private GamerService gamerService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private SmsService smsService;

    /**
     * 撩玩平台三种方式登录入口，登录成功获取后端接口访问凭证token
     * @param param
     * @return
     * @throws Exception
     */
    @LoginIgnore
    @PostMapping(value = "/login")
    Object login(@RequestBody Gamer param) throws Exception {
        if(StringUtils.isEmpty(param.getWxId()) && StringUtils.isEmpty(param.getPhoneNumber()) && StringUtils.isEmpty(param.getDeviceId())) {
            throw new BusinessException(ErrorInfo.ARGUMENT_NULL_ALL.code, ErrorInfo.ARGUMENT_NULL_ALL.desc);
        }
        ResponseMsg msg = new ResponseMsg();
        //微信号登录
        if(!StringUtils.isEmpty(param.getWxId())) {
            Gamer gamer = gamerService.selectGamerByWxId(param.getWxId());
            if(gamer==null) {         //通过微信id没有找到玩家
                List<Gamer> list = gamerService.selectGamerByDeviceId(param.getDeviceId());
                for(Gamer g : list) {
                    if(g.getWxId() != null) {  //绑定当前的设备id和微信id
                        g.setWxId(param.getWxId());
                        gamerService.updateGamer(g);
                        Token token = tokenService.genToken(g.getId(), 1, TimeUnit.DAYS);
                        msg.setData(token);
                        return msg;
                    }
                }
                //设备id和微信id都没找到玩家,生成新帐号返回
                gamer = new Gamer();
                Long id = SnowflakeIdWorker.getInstance().nextId();
                gamer.setId(id);
                gamer.setWxId(param.getWxId());
                gamer.setDeviceId(param.getDeviceId());
                gamer.setHead(param.getHead());
                Date date = new Date();
                gamer.setUpdated(date);
                gamer.setCreated(date);
                gamerService.insertGamer(gamer);
                Token token = tokenService.genToken(id, 1, TimeUnit.DAYS);
                msg.setData(token);
                return msg;
            }
            //通过微信id找到玩家
            Token token = tokenService.genToken(gamer.getId(), 1, TimeUnit.DAYS);
            msg.setData(token);
            return msg;
        }
        //手机号登录
        if(!StringUtils.isEmpty(param.getPhoneNumber())) {
            if(!ValidatorUtil.isMobile(param.getPhoneNumber())) {
                throw new BusinessException(ErrorInfo.PHONENUMBER_IS_ERROR.code, ErrorInfo.PHONENUMBER_IS_ERROR.desc);
            }
            String code = smsService.getCode(param.getPhoneNumber());
            if(!code.equals(param.getCode())) {
                throw new BusinessException(ErrorInfo.PHONECODE_IS_ERROR.code, ErrorInfo.PHONECODE_IS_ERROR.desc);
            }
            Gamer gamer = gamerService.selectGamerByPhoneNumber(param.getPhoneNumber());
            if(gamer == null) {  //通过手机号没找到玩家
                gamer = new Gamer();
                Long id = SnowflakeIdWorker.getInstance().nextId();
                gamer.setId(id);
                gamer.setPhoneNumber(param.getPhoneNumber());
                gamer.setDeviceId(param.getDeviceId());
                Date date = new Date();
                gamer.setUpdated(date);
                gamer.setCreated(date);
                gamerService.insertGamer(gamer);
                Token token = tokenService.genToken(id, 1, TimeUnit.DAYS);
                msg.setData(token);
                return msg;
            }
            // 通过手机号找到玩家
            Token token = tokenService.genToken(gamer.getId(), 1, TimeUnit.DAYS);
            msg.setData(token);
            return msg;
        }
        //游客登录
        List<Gamer> list = gamerService.selectGamerByDeviceId(param.getDeviceId());
        for(Gamer g : list) {  //找出游客帐号
            if(StringUtils.isEmpty(g.getPhoneNumber()) && StringUtils.isEmpty(g.getWxId())) {
                Token token = tokenService.genToken(g.getId(), 1, TimeUnit.DAYS);
                msg.setData(token);
                return msg;
            }
        }
        //新生成游客帐号
        Gamer gamer = new Gamer();
        Long id = SnowflakeIdWorker.getInstance().nextId();
        gamer.setId(id);
        gamer.setDeviceId(param.getDeviceId());
        Date date = new Date();
        gamer.setUpdated(date);
        gamer.setCreated(date);
        gamerService.insertGamer(gamer);
        Token token = tokenService.genToken(id, 1, TimeUnit.DAYS);
        msg.setData(token);
        return msg;
    }

    /**
     * 检查设备是否用手机号登录过，并返回手机号码
     * @param param
     * @return
     * @throws Exception
     */
    @LoginIgnore
    @PostMapping(value = "/checkPhone")
    Object checkPhone(@RequestBody Gamer param) throws Exception {
        if(StringUtils.isEmpty(param.getDeviceId())) {
            throw new BusinessException(ErrorInfo.DEVICE_ID_NULL.code, ErrorInfo.DEVICE_ID_NULL.desc);
        }
        ResponseMsg msg = new ResponseMsg();
        List<Gamer> list = gamerService.selectGamerByDeviceId(param.getDeviceId());
        for(Gamer g : list) {  //找出手机帐号
            if(!StringUtils.isEmpty(g.getPhoneNumber())) {
                Map<String, Object> map = new HashMap<>();
                map.put("phoneNumber", g.getPhoneNumber());
                map.put("deviceId", g.getDeviceId());
                msg.setData(map);
                break;
            }
        }
        return msg;
    }
}
