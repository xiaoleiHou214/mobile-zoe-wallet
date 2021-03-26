package com.zoecasheth.app.interact;

import com.zoecasheth.app.entity.ActivityMeta;
import com.zoecasheth.app.entity.ContractType;
import com.zoecasheth.app.entity.tokens.TokenInfo;
import com.zoecasheth.app.entity.Transaction;
import com.zoecasheth.app.entity.Wallet;
import com.zoecasheth.app.repository.TokenRepositoryType;
import com.zoecasheth.app.repository.TransactionRepositoryType;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;

import com.zoecasheth.app.repository.entity.RealmAuxData;

import org.web3j.protocol.core.methods.response.EthTransaction;

import java.util.List;

public class FetchTransactionsInteract {

    private final TransactionRepositoryType transactionRepository;
    private final TokenRepositoryType tokenRepository;

    public FetchTransactionsInteract(TransactionRepositoryType transactionRepository,
                                     TokenRepositoryType tokenRepositoryType) {
        this.transactionRepository = transactionRepository;
        this.tokenRepository = tokenRepositoryType;
    }

    public Single<ActivityMeta[]> fetchTransactionMetas(Wallet wallet, List<Integer> networkFilters, long fetchTime, int fetchLimit) {
        return transactionRepository
                .fetchCachedTransactionMetas(wallet, networkFilters, fetchTime, fetchLimit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<ActivityMeta[]> fetchEventMetas(Wallet wallet, List<Integer> networkFilters)
    {
        return transactionRepository.fetchEventMetas(wallet, networkFilters);
    }

    public Single<ContractType> queryInterfaceSpec(TokenInfo tokenInfo)
    {
        //can resolve erc20, erc721 and erc875 from a getbalance check and look at decimals. Otherwise try more esoteric
        return tokenRepository.determineCommonType(tokenInfo);
    }

    public Transaction fetchCached(String walletAddress, String hash)
    {
        return transactionRepository.fetchCachedTransaction(walletAddress, hash);
    }

    public long fetchTxCompletionTime(String walletAddr, String hash)
    {
        return transactionRepository.fetchTxCompletionTime(walletAddr, hash);
    }

    public Realm getRealmInstance(Wallet wallet)
    {
        return transactionRepository.getRealmInstance(wallet);
    }

    public Single<ActivityMeta[]> fetchTransactionMetas(Wallet wallet, int chainId, String tokenAddress, int historyCount)
    {
        return transactionRepository
                .fetchCachedTransactionMetas(wallet, chainId, tokenAddress, historyCount)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public RealmAuxData fetchEvent(String walletAddress, String eventKey)
    {
        return transactionRepository
                .fetchCachedEvent(walletAddress, eventKey);
    }

    public Single<Transaction> storeRawTx(Wallet wallet, EthTransaction rawTx, long timeStamp)
    {
        return transactionRepository.storeRawTx(wallet, rawTx, timeStamp);
    }

    public void restartTransactionService()
    {
        transactionRepository.restartService();
    }
}
