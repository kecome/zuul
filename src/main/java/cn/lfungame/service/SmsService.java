package cn.lfungame.service;
import cn.lfungame.exception.BusinessException;
import cn.lfungame.exception.ErrorInfo;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: xuke
 * @Date: 2018/5/30 16:01
 * @Description:
 */
@Service
public class SmsService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${sms.product}")
    private String product;

    @Value("${sms.domain}")
    private String domain;

    @Value("${sms.accessKeyId}")
    private String accessKeyId;

    @Value("${sms.accessKeySecret}")
    private String accessKeySecret;

    @Value("${sms.signName}")
    private String signName;

    @Value("${sms.templateCode}")
    private String templateCode;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public int sendSms(String telephone) throws ClientException {
        logger.info("+++++++++++++++++++++获取手机号码为："+telephone+"+++++++++++++++");
        int code = (int)(Math.random()*9999)+1000;//随机生成3-5位数的验证码
        logger.info("=================验证码生成成功，验证码为："+code+"=================");
        // 可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        // 初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        // 组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        // 必填:待发送手机号
        request.setPhoneNumbers(telephone);
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName(signName);
        // 必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateCode);
        // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的用户,您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"code\":\"" + code + "\"}");
        // 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        // request.setSmsUpExtendCode("90997");
        // 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");
        // hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if(sendSmsResponse.getCode()!= null && sendSmsResponse.getCode().equals("OK")){
            stringRedisTemplate.opsForValue().set(telephone, code+"", 2, TimeUnit.MINUTES);
            return code;
        }
        throw new BusinessException(ErrorInfo.SMS_ERROR.code, ErrorInfo.SMS_ERROR.desc);
    }

    /**
     * 缓存取出手机验证码
     * @param phoneNumber
     * @return
     */
    public String getCode(String phoneNumber) {
        String code = stringRedisTemplate.opsForValue().get(phoneNumber);
        return code==null?"":code;
    }

}
