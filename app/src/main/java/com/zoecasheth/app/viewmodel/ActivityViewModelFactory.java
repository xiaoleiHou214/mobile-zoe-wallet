package com.zoecasheth.app.viewmodel;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.zoecasheth.app.interact.FetchTransactionsInteract;
import com.zoecasheth.app.interact.GenericWalletInteract;
import com.zoecasheth.app.service.AssetDefinitionService;
import com.zoecasheth.app.service.TokensService;
import com.zoecasheth.app.service.TransactionsService;

/**
 * Created by JB on 26/06/2020.
 */
public class ActivityViewModelFactory implements ViewModelProvider.Factory {

    private final GenericWalletInteract genericWalletInteract;
    private final FetchTransactionsInteract fetchTransactionsInteract;
    private final AssetDefinitionService assetDefinitionService;
    private final TokensService tokensService;
    private final TransactionsService transactionsService;

    public ActivityViewModelFactory(
            GenericWalletInteract genericWalletInteract,
            FetchTransactionsInteract fetchTransactionsInteract,
            AssetDefinitionService assetDefinitionService,
            TokensService tokensService,
            TransactionsService transactionsService) {
        this.genericWalletInteract = genericWalletInteract;
        this.fetchTransactionsInteract = fetchTransactionsInteract;
        this.assetDefinitionService = assetDefinitionService;
        this.tokensService = tokensService;
        this.transactionsService = transactionsService;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ActivityViewModel(
                genericWalletInteract,
                fetchTransactionsInteract,
                assetDefinitionService,
                tokensService,
                transactionsService);
    }
}
