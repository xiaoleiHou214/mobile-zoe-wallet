package com.zoecasheth.app.viewmodel;


import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.zoecasheth.app.interact.ChangeTokenEnableInteract;
import com.zoecasheth.app.interact.FetchTokensInteract;
import com.zoecasheth.app.interact.GenericWalletInteract;
import com.zoecasheth.app.router.AssetDisplayRouter;
import com.zoecasheth.app.router.Erc20DetailRouter;
import com.zoecasheth.app.router.MyAddressRouter;
import com.zoecasheth.app.service.AssetDefinitionService;
import com.zoecasheth.app.service.TokensService;

public class WalletViewModelFactory implements ViewModelProvider.Factory {
    private final FetchTokensInteract fetchTokensInteract;
    private final Erc20DetailRouter erc20DetailRouter;
    private final AssetDisplayRouter assetDisplayRouter;
    private final GenericWalletInteract genericWalletInteract;
    private final AssetDefinitionService assetDefinitionService;
    private final TokensService tokensService;
    private final ChangeTokenEnableInteract changeTokenEnableInteract;
    private final MyAddressRouter myAddressRouter;

    public WalletViewModelFactory(FetchTokensInteract fetchTokensInteract,
                                  Erc20DetailRouter erc20DetailRouter,
                                  AssetDisplayRouter assetDisplayRouter,
                                  GenericWalletInteract genericWalletInteract,
                                  AssetDefinitionService assetDefinitionService,
                                  TokensService tokensService,
                                  ChangeTokenEnableInteract changeTokenEnableInteract,
                                  MyAddressRouter myAddressRouter) {
        this.fetchTokensInteract = fetchTokensInteract;
        this.erc20DetailRouter = erc20DetailRouter;
        this.assetDisplayRouter = assetDisplayRouter;
        this.genericWalletInteract = genericWalletInteract;
        this.assetDefinitionService = assetDefinitionService;
        this.tokensService = tokensService;
        this.changeTokenEnableInteract = changeTokenEnableInteract;
        this.myAddressRouter = myAddressRouter;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new WalletViewModel(
                fetchTokensInteract,
                erc20DetailRouter,
                assetDisplayRouter,
                genericWalletInteract,
                assetDefinitionService,
                tokensService,
                changeTokenEnableInteract,
                myAddressRouter);
    }
}