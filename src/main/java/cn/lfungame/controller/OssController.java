package cn.lfungame.controller;

import cn.lfungame.model.OssAK;
import cn.lfungame.service.OssService;
import cn.lfungame.util.ResponseMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: xuke
 * @Date: 2018/5/30 14:32
 * @Description:
 */
@RestController
@RequestMapping(value = "/oss")
public class OssController {
    @Autowired
    private OssService ossService;

    @RequestMapping(value = "/liaowan")
    Object liaowanBucket() throws Exception {
        ResponseMsg msg = new ResponseMsg<>();
        OssAK ak = ossService.getAK();
        msg.setData(ak);
        return msg;
    }

}
