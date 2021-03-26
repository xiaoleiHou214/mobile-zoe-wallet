package com.zoecasheth.app.di;

import com.zoecasheth.app.interact.FetchTransactionsInteract;
import com.zoecasheth.app.repository.OnRampRepositoryType;
import com.zoecasheth.app.repository.TokenRepositoryType;
import com.zoecasheth.app.repository.TransactionRepositoryType;
import com.zoecasheth.app.router.MyAddressRouter;
import com.zoecasheth.app.service.AssetDefinitionService;
import com.zoecasheth.app.service.TokensService;
import com.zoecasheth.app.viewmodel.Erc20DetailViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class Erc20DetailModule {
    @Provides
    Erc20DetailViewModelFactory provideErc20DetailViewModelFactory(MyAddressRouter myAddressRouter,
                                                                   FetchTransactionsInteract fetchTransactionsInteract,
                                                                   AssetDefinitionService assetDefinitionService,
                                                                   TokensService tokensService,
                                                                   OnRampRepositoryType onRampRepository) {
        return new Erc20DetailViewModelFactory(myAddressRouter,
                fetchTransactionsInteract,
                assetDefinitionService,
                tokensService,
                onRampRepository);
    }

    @Provides
    MyAddressRouter provideMyAddressRouter() {
        return new MyAddressRouter();
    }

    @Provides
    FetchTransactionsInteract provideFetchTransactionsInteract(TransactionRepositoryType transactionRepositoryType,
                                                               TokenRepositoryType tokenRepositoryType) {
        return new FetchTransactionsInteract(transactionRepositoryType, tokenRepositoryType);
    }
}
