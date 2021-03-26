package com.zoecasheth.app.di;

import com.zoecasheth.app.interact.FetchTransactionsInteract;
import com.zoecasheth.app.interact.GenericWalletInteract;
import com.zoecasheth.app.repository.TokenRepositoryType;
import com.zoecasheth.app.repository.TransactionRepositoryType;
import com.zoecasheth.app.repository.WalletRepositoryType;
import com.zoecasheth.app.service.AssetDefinitionService;
import com.zoecasheth.app.service.TokensService;
import com.zoecasheth.app.service.TransactionsService;
import com.zoecasheth.app.viewmodel.ActivityViewModelFactory;

import dagger.Module;
import dagger.Provides;

/**
 * Created by JB on 26/06/2020.
 */
@Module
class ActivityModule
{
    @Provides
    ActivityViewModelFactory provideActivityViewModelFactory(
            GenericWalletInteract genericWalletInteract,
            FetchTransactionsInteract fetchTransactionsInteract,
            AssetDefinitionService assetDefinitionService,
            TokensService tokensService,
            TransactionsService transactionsService) {
        return new ActivityViewModelFactory(
                genericWalletInteract,
                fetchTransactionsInteract,
                assetDefinitionService,
                tokensService,
                transactionsService);
    }

    @Provides
    GenericWalletInteract provideFindDefaultWalletInteract(WalletRepositoryType walletRepository) {
        return new GenericWalletInteract(walletRepository);
    }

    @Provides
    FetchTransactionsInteract provideFetchTransactionsInteract(TransactionRepositoryType transactionRepository,
                                                               TokenRepositoryType tokenRepositoryType) {
        return new FetchTransactionsInteract(transactionRepository, tokenRepositoryType);
    }
}
