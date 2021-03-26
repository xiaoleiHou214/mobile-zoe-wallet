package com.zoecasheth.app.di;

import dagger.Module;
import dagger.Provides;
import com.zoecasheth.app.interact.FindDefaultNetworkInteract;
import com.zoecasheth.app.repository.EthereumNetworkRepositoryType;
import com.zoecasheth.app.service.TokensService;
import com.zoecasheth.app.viewmodel.MyAddressViewModelFactory;

@Module
class MyAddressModule {
    @Provides
    MyAddressViewModelFactory provideMyAddressViewModelFactory(
            FindDefaultNetworkInteract findDefaultNetworkInteract,
            EthereumNetworkRepositoryType ethereumNetworkRepository,
            TokensService tokensService) {
        return new MyAddressViewModelFactory(
                findDefaultNetworkInteract,
                ethereumNetworkRepository,
                tokensService);
    }

    @Provides
    FindDefaultNetworkInteract provideFindDefaultNetworkInteract(
            EthereumNetworkRepositoryType ethereumNetworkRepositoryType) {
        return new FindDefaultNetworkInteract(ethereumNetworkRepositoryType);
    }
}
