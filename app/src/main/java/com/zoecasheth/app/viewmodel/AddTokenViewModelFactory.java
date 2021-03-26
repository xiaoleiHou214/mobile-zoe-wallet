package com.zoecasheth.app.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.zoecasheth.app.interact.AddTokenInteract;
import com.zoecasheth.app.interact.FetchTokensInteract;
import com.zoecasheth.app.interact.FetchTransactionsInteract;
import com.zoecasheth.app.interact.GenericWalletInteract;
import com.zoecasheth.app.repository.EthereumNetworkRepositoryType;
import com.zoecasheth.app.service.AssetDefinitionService;
import com.zoecasheth.app.service.TokensService;

public class AddTokenViewModelFactory implements ViewModelProvider.Factory {

    private final AddTokenInteract addTokenInteract;
    private final GenericWalletInteract genericWalletInteract;
    private final FetchTokensInteract fetchTokensInteract;
    private final EthereumNetworkRepositoryType ethereumNetworkRepository;
    private final FetchTransactionsInteract fetchTransactionsInteract;
    private final AssetDefinitionService assetDefinitionService;
    private final TokensService tokensService;

    public AddTokenViewModelFactory(
            AddTokenInteract addTokenInteract,
            GenericWalletInteract genericWalletInteract,
            FetchTokensInteract fetchTokensInteract,
            EthereumNetworkRepositoryType ethereumNetworkRepository,
            FetchTransactionsInteract fetchTransactionsInteract,
            AssetDefinitionService assetDefinitionService,
            TokensService tokensService) {
        this.addTokenInteract = addTokenInteract;
        this.genericWalletInteract = genericWalletInteract;
        this.fetchTokensInteract = fetchTokensInteract;
        this.ethereumNetworkRepository = ethereumNetworkRepository;
        this.fetchTransactionsInteract = fetchTransactionsInteract;
        this.assetDefinitionService = assetDefinitionService;
        this.tokensService = tokensService;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AddTokenViewModel(addTokenInteract, genericWalletInteract, fetchTokensInteract, ethereumNetworkRepository, fetchTransactionsInteract, assetDefinitionService, tokensService);
    }
}
