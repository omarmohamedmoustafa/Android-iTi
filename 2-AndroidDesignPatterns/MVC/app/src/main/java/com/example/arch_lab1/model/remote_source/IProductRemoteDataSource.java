package com.example.arch_lab1.model.remote_source;

public interface IProductRemoteDataSource {
    void makeNetworkCall(NetworkCallback callback);
}
