package com.zoecasheth.app.di;

import dagger.Module;
import dagger.Provides;
import com.zoecasheth.app.repository.EthereumNetworkRepositoryType;
import com.zoecasheth.app.service.TokensService;
import com.zoecasheth.app.viewmodel.SelectNetworkViewModelFactory;

@Module
class SelectNetworkModule {
    @Provides
    SelectNetworkViewModelFactory provideSelectNetworkViewModelFactory(EthereumNetworkRepositoryType networkRepositoryType,
                                                                       TokensService tokensService) {
        return new SelectNetworkViewModelFactory(networkRepositoryType, tokensService);
    }
}
