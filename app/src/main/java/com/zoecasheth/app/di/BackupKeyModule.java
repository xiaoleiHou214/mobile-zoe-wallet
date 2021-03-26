package com.zoecasheth.app.di;

import dagger.Module;
import dagger.Provides;

import com.zoecasheth.app.interact.ExportWalletInteract;
import com.zoecasheth.app.interact.FetchWalletsInteract;
import com.zoecasheth.app.repository.WalletRepositoryType;
import com.zoecasheth.app.service.KeyService;
import com.zoecasheth.app.viewmodel.BackupKeyViewModelFactory;

@Module
public class BackupKeyModule {
    @Provides
    BackupKeyViewModelFactory provideBackupKeyViewModelFactory(
            KeyService keyService,
            ExportWalletInteract exportWalletInteract,
            FetchWalletsInteract fetchWalletsInteract) {
        return new BackupKeyViewModelFactory(
                keyService,
                exportWalletInteract,
                fetchWalletsInteract);
    }

    @Provides
    ExportWalletInteract provideExportWalletInteract(
            WalletRepositoryType walletRepository) {
        return new ExportWalletInteract(walletRepository);
    }

    @Provides
    FetchWalletsInteract provideFetchAccountsInteract(WalletRepositoryType accountRepository) {
        return new FetchWalletsInteract(accountRepository);
    }
}