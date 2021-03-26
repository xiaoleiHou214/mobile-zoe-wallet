package com.zoecasheth.app.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.zoecasheth.app.interact.AddTokenInteract;
import com.zoecasheth.app.interact.CreateTransactionInteract;
import com.zoecasheth.app.interact.FetchTransactionsInteract;
import com.zoecasheth.app.repository.EthereumNetworkRepositoryType;
import com.zoecasheth.app.router.MyAddressRouter;
import com.zoecasheth.app.service.AnalyticsServiceType;
import com.zoecasheth.app.service.AssetDefinitionService;
import com.zoecasheth.app.service.GasService2;
import com.zoecasheth.app.service.KeyService;
import com.zoecasheth.app.service.TokensService;

import io.reactivex.annotations.NonNull;

public class SendViewModelFactory implements ViewModelProvider.Factory {

    private final MyAddressRouter myAddressRouter;
    private final EthereumNetworkRepositoryType networkRepository;
    private final TokensService tokensService;
    private final FetchTransactionsInteract fetchTransactionsInteract;
    private final AddTokenInteract addTokenInteract;
    private final CreateTransactionInteract createTransactionInteract;
    private final GasService2 gasService;
    private final AssetDefinitionService assetDefinitionService;
    private final KeyService keyService;
    private final AnalyticsServiceType analyticsService;

    public SendViewModelFactory(MyAddressRouter myAddressRouter,
                                EthereumNetworkRepositoryType networkRepository,
                                TokensService tokensService,
                                FetchTransactionsInteract fetchTransactionsInteract,
                                AddTokenInteract addTokenInteract,
                                CreateTransactionInteract createTransactionInteract,
                                GasService2 gasService,
                                AssetDefinitionService assetDefinitionService,
                                KeyService keyService,
                                AnalyticsServiceType analyticsService) {
        this.myAddressRouter = myAddressRouter;
        this.networkRepository = networkRepository;
        this.tokensService = tokensService;
        this.gasService = gasService;
        this.fetchTransactionsInteract = fetchTransactionsInteract;
        this.addTokenInteract = addTokenInteract;
        this.assetDefinitionService = assetDefinitionService;
        this.keyService = keyService;
        this.createTransactionInteract = createTransactionInteract;
        this.analyticsService = analyticsService;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SendViewModel(myAddressRouter, networkRepository, tokensService,
                fetchTransactionsInteract, addTokenInteract, createTransactionInteract, gasService, assetDefinitionService, keyService, analyticsService);
    }
}
