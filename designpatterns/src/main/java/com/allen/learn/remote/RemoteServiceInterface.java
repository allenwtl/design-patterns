package com.allen.learn.remote;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RemoteServiceInterface  extends Remote{

    public List<String> queryAllInfo() throws RemoteException ;

}
