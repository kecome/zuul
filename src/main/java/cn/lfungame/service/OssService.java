package cn.lfungame.service;

import cn.lfungame.model.OssAK;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.auth.sts.AssumeRoleRequest;
import com.aliyuncs.auth.sts.AssumeRoleResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Auther: xuke
 * @Date: 2018/5/30 14:33
 * @Description:
 */
@Service
public class OssService {
    @Value("${oss.endpoint}")
    private String endpoint;
    @Value("${oss.accessKeyId}")
    private String accessKeyId;
    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;
    // String accessKeySecret = "tyvw5tNmsfRVn0E7NKEcbztSELA2C5";
    @Value("${oss.roleArn}")
    private String roleArn;
    @Value("${oss.roleSessionName}")
    private String roleSessionName;

    String policy =  "{\n" +
            "  \"Statement\": [\n" +
            "    {\n" +
            "      \"Action\": [\n" +
            "        \"oss:GetObject\",\n" +
            "        \"oss:PutObject\",\n" +
            "        \"oss:DeleteObject\",\n" +
            "        \"oss:ListParts\",\n" +
            "        \"oss:AbortMultipartUpload\",\n" +
            "        \"oss:ListObjects\"\n" +
            "      ],\n" +
            "      \"Effect\": \"Allow\",\n" +
            "      \"Resource\": [\"acs:oss:*:*:liaowan/*\", \"acs:oss:*:*:liaowan\"]\n" +
            "    }\n" +
            "  ],\n" +
            "  \"Version\": \"1\"\n" +
            "}";
    public OssAK getAK() {
        OssAK ak = new OssAK();
        try {
            // Init ACS Client
            DefaultProfile.addEndpoint("", "oss-cn-shenzhen", "Sts", endpoint);
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
            DefaultAcsClient client = new DefaultAcsClient(profile);
            final AssumeRoleRequest request = new AssumeRoleRequest();
            request.setMethod(MethodType.POST);
            request.setRoleArn(roleArn);
            request.setRoleSessionName(roleSessionName);
            request.setPolicy(policy); // Optional
            final AssumeRoleResponse response = client.getAcsResponse(request);

            ak.setRequestId(response.getRequestId());
            ak.setAccessKeyId(response.getCredentials().getAccessKeyId());
            ak.setAccessKeySecret(response.getCredentials().getAccessKeySecret());
            ak.setSecurityToken(response.getCredentials().getSecurityToken());
            ak.setExpiration(response.getCredentials().getExpiration());
        } catch (ClientException e) {
            System.out.println("Failed：");
            System.out.println("Error code: " + e.getErrCode());
            System.out.println("Error message: " + e.getErrMsg());
            System.out.println("RequestId: " + e.getRequestId());
        }
        return ak;
    }
}
