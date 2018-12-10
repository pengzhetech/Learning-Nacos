package com.javaman.nacos;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author pengzhe
 * @date 2018-12-10 21:15
 * @description 构建自定义实例
 * InstanceId是由服务端生成返回给客户端，用于唯一标识该实例。
 * IP、端口是实例的基本属性，除此之外，还有weight权重，
 * 可以设置该实例所分配流量的多少，这应该也比较好理解，权重越大
 * ，实例分配的流量就会越大。healthy字段代表该实例是否健康，
 * 这个值也是由服务端返回给客户端的。
 * cluster和service分别表示该实例对应的集群和服务的一些信息，
 * 这些信息会在下面做介绍。最后是实例的元数据，这个元数据一个String对String的Map
 */

public class InstanceDemo {

    public static void main(String[] args) throws NacosException, UnknownHostException {
        //1 配置Nacos客户端Properties
        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.SERVER_ADDR, "127.0.0.1:8848");
        //2 创建Nacos Naming客户端：
        NamingService namingService = NacosFactory.createNamingService(properties);

        Instance instance = new Instance();
        instance.setIp(InetAddress.getLocalHost().getHostAddress());
        instance.setPort(8080);
        instance.setWeight(100);

        Map<String, String> metadata = new HashMap<String, String>(16);
        metadata.put("app", "nacos");
        metadata.put("site", "beijing");
        instance.setMetadata(metadata);

        namingService.registerInstance("nacos.test.6", instance);
    }
}
