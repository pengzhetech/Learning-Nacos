package com.javaman.nacos;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

/**
 * @author pengzhe
 * @date 2018-12-10 21:03
 * @description 首先在第一步中，属性PropertyKeyConst.SERVER_ADDR表示的是Nacos服务端的地址，
 * 这个地址的格式为IP:port,IP:port。对于单机版，
 * 只需要指定一个IP:port。甚至您可以将端口省略，
 * 这样将会访问Nacos的默认端口8848。
 * 在第二步中，将创建一个NamingService实例，客户端将为该实例创建单独的资源空间，包括缓存、线程池以及配置等。
 * Nacos客户端没有对该实例做单例的限制，请小心维护这个实例，以防新建了多于预期的实例。
 * 第三步注册服务中，使用的是最简单的API注册方式，只需要传入服务名、IP、端口就可以。
 * 第四步是获取服务下的所有实例列表，包括健康和不健康的。
 */

public class BasicDemo {

    public static void main(String[] args) throws NacosException, UnknownHostException {
        //1 配置Nacos客户端Properties
        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.SERVER_ADDR, "127.0.0.1:8848");
        //2 创建Nacos Naming客户端：
        NamingService namingService = NacosFactory.createNamingService(properties);
        //3 注册一个实例
        namingService.registerInstance("nacos.test.1", InetAddress.getLocalHost().getHostAddress(), 8080);

        //4 查找所有实例
        System.out.println(namingService.getAllInstances("nacos.test.1"));

    }

}


