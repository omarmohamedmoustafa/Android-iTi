package com.example.arch_lab2.model.remote_source;

public interface IProductRemoteDataSource {
    void makeNetworkCall(NetworkCallback callback);
}
