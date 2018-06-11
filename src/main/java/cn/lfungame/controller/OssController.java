package cn.lfungame.controller;

import cn.lfungame.model.OssAK;
import cn.lfungame.service.OssService;
import cn.lfungame.util.ResponseMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: xuke
 * @Date: 2018/5/30 14:32
 * @Description:
 */
@Api(tags = "阿里oss相关api")
@RestController
@RequestMapping(value = "/oss")
public class OssController {
    @Autowired
    private OssService ossService;


    @GetMapping(value = "/liaowan")
    @ApiOperation(value="liaowan Bucket", notes="获取liaowan Bucket的操作权限")
    ResponseMsg<OssAK> liaowanBucket() throws Exception {
        ResponseMsg msg = new ResponseMsg<>();
        OssAK ak = ossService.getAK();
        msg.setData(ak);
        return msg;
    }

}
