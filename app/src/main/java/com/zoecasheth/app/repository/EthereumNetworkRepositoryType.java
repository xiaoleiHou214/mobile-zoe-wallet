package com.zoecasheth.app.repository;

import com.zoecasheth.app.entity.ContractLocator;
import com.zoecasheth.app.entity.KnownContract;
import com.zoecasheth.app.entity.NetworkInfo;
import com.zoecasheth.app.entity.Wallet;
import com.zoecasheth.app.entity.tokens.Token;

import org.web3j.protocol.Web3j;

import java.math.BigInteger;
import java.util.List;

import io.reactivex.Single;

public interface EthereumNetworkRepositoryType {

	NetworkInfo getDefaultNetwork();
	NetworkInfo getNetworkByChain(int chainId);

	Single<BigInteger> getLastTransactionNonce(Web3j web3j, String walletAddress);

	void setDefaultNetworkInfo(NetworkInfo networkInfo);

	NetworkInfo[] getAvailableNetworkList();

	void addOnChangeDefaultNetwork(OnNetworkChangeListener onNetworkChanged);

	String getNameById(int id);

    List<Integer> getFilterNetworkList();
    void setFilterNetworkList(int[] networkList);

	List<ContractLocator> getAllKnownContracts(List<Integer> networkFilters);
	Single<Token[]> getBlankOverrideTokens(Wallet wallet);
	Token getBlankOverrideToken();
	Token getBlankOverrideToken(NetworkInfo networkInfo);

	KnownContract readContracts();

    boolean getIsPopularToken(int chain, String address);
}
