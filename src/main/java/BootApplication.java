import cn.lfungame.zuulfilter.TokenCheckFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Auther: xuke
 * @Date: 2018/5/15 09:09
 * @Description:
 */
@SpringBootApplication
@EnableZuulProxy
@ComponentScan(basePackages={"cn.lfungame"})
@EnableEurekaClient
public class BootApplication {

    @Bean
    public TokenCheckFilter tokenCheckFilter() {
        return new TokenCheckFilter();
    }

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }
}
