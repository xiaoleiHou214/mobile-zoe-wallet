package com.zoecasheth.app.di;

import com.zoecasheth.app.interact.ImportWalletInteract;
import com.zoecasheth.app.repository.WalletRepositoryType;
import com.zoecasheth.app.service.AnalyticsServiceType;
import com.zoecasheth.app.service.GasService;
import com.zoecasheth.app.service.KeyService;
import com.zoecasheth.app.viewmodel.ImportWalletViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class ImportModule {
    @Provides
    ImportWalletViewModelFactory provideImportWalletViewModelFactory(
            ImportWalletInteract importWalletInteract,
            KeyService keyService,
            GasService gasService,
            AnalyticsServiceType analyticsService) {
        return new ImportWalletViewModelFactory(importWalletInteract, keyService, gasService, analyticsService);
    }

    @Provides
    ImportWalletInteract provideImportWalletInteract(
            WalletRepositoryType walletRepository) {
        return new ImportWalletInteract(walletRepository);
    }
}
