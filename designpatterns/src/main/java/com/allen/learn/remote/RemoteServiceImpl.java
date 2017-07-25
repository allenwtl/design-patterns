package com.allen.learn.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;


public class RemoteServiceImpl extends UnicastRemoteObject implements RemoteServiceInterface {

    private static final long serialVersionUID = -5875145745472489286L;

    protected RemoteServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public List<String> queryAllInfo() throws RemoteException {
        List<String> stringList = new ArrayList<>();
        stringList.add("aaaaa");
        stringList.add("bbbbb");
        stringList.add("ccccc");
        return stringList;
    }
}
