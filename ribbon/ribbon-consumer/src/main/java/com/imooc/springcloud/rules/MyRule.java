package com.imooc.springcloud.rules;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class MyRule extends AbstractLoadBalancerRule implements IRule {
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    public Server choose(Object o) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String uri = request.getServletPath() + "?" + request.getQueryString();
        return route(uri.hashCode(), getLoadBalancer().getAllServers());
    }

    public Server route(int hashId, List<Server> addressList) {
        if (CollectionUtils.isEmpty(addressList)) {
            return null;
        }
        final TreeMap<Long, Server> address = new TreeMap<Long, Server>();
        addressList.stream().forEach(e->{
            // 把每个服务节点 映射 到 环上， 每个节点 分配8个位置
            for (int i=0; i<8; i++) {
                long hash = hash(e.getId() + i);
                address.put(hash, e);
            }
        });

        long hash = hash(String.valueOf(hashId));
        SortedMap<Long, Server> last = address.tailMap(hash);
        // 当进来的hashId 比环中任何一个位置都大，那么last肯定是空的
        // 那么下一个最近的节点就是 环中的第一个节点
        if (CollectionUtils.isEmpty(last)) {
            return address.firstEntry().getValue();
        }
        return last.get(last.firstKey());
    }

    public long hash(String key) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        byte[] keyByte = key.getBytes(StandardCharsets.UTF_8);
        md5.update(keyByte);

        byte[] digest = md5.digest();

        long hashCode = ((long) (digest[2] & 0xFF << 16))
                | ((long) (digest[1] & 0xFF << 8))
                | ((long) (digest[0] & 0xFF));

        return hashCode & 0xffffffffL;
    }
}
