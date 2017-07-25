package com.allen.learn.remote;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;

import java.rmi.Naming;
import java.util.List;

public class RemoteTest {


    static {
        BasicConfigurator.configure();
    }

    /**
     * 日志
     */
    private static final Log LOGGER = LogFactory.getLog(RemoteTest.class);

    public static void main(String[] args) throws Exception {
        // 您看，这里使用的是java名称服务技术进行的RMI接口查找。
        RemoteServiceInterface remoteServiceInterface = (RemoteServiceInterface) Naming.lookup("rmi://127.0.0.1:1099/queryAllInfo");
        List<String> users = remoteServiceInterface.queryAllInfo();

        RemoteTest.LOGGER.info("users.size() = " +users.size());
    }

}
