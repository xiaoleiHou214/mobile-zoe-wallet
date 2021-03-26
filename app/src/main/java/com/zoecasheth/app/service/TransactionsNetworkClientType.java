package com.zoecasheth.app.service;

import com.zoecasheth.app.entity.NetworkInfo;
import com.zoecasheth.app.entity.Transaction;
import com.zoecasheth.app.entity.TransactionMeta;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface TransactionsNetworkClientType {
    Single<Transaction[]> storeNewTransactions(String walletAddress, NetworkInfo networkInfo, String tokenAddress, long lastBlock);
    Single<TransactionMeta[]> fetchMoreTransactions(String walletAddress, NetworkInfo network, long lastTxTime);
    Single<Integer> readTransactions(String currentAddress, NetworkInfo networkByChain, TokensService tokensService, boolean nftCheck);
    Completable checkTransactionsForEmptyFunctions(String currentAddress);
}
