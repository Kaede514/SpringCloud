package cn.itcast.feign.fallback;

import cn.itcast.feign.client.UserClient;
import cn.itcast.feign.pojo.User;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserClientFallbackFactory implements FallbackFactory<UserClient> {
    @Override
    public UserClient create(Throwable throwable) {
        // 创建UserClient接口实现类，实现其中方法，编写失败降级的处理逻辑
        return new UserClient() {
            @Override
            public User findById(Long id) {
                // 记录一次信息
                log.error("查询用户异常", throwable);
                // 根据业务需求返回默认的数据，这里是空用户
                return new User();
            }
        };
    }
}