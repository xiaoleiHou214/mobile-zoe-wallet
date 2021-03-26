package com.zoecasheth.app.di;

import android.content.Context;

import com.zoecasheth.app.repository.EthereumNetworkRepository;
import com.zoecasheth.app.repository.EthereumNetworkRepositoryType;
import com.zoecasheth.app.repository.OnRampRepository;
import com.zoecasheth.app.repository.OnRampRepositoryType;
import com.zoecasheth.app.repository.PreferenceRepositoryType;
import com.zoecasheth.app.repository.SharedPreferenceRepository;
import com.zoecasheth.app.repository.TokenLocalSource;
import com.zoecasheth.app.repository.TokenRepository;
import com.zoecasheth.app.repository.TokenRepositoryType;
import com.zoecasheth.app.repository.TokensRealmSource;
import com.zoecasheth.app.repository.TransactionLocalSource;
import com.zoecasheth.app.repository.TransactionRepository;
import com.zoecasheth.app.repository.TransactionRepositoryType;
import com.zoecasheth.app.repository.TransactionsRealmCache;
import com.zoecasheth.app.repository.WalletDataRealmSource;
import com.zoecasheth.app.repository.WalletRepository;
import com.zoecasheth.app.repository.WalletRepositoryType;
import com.zoecasheth.app.service.AccountKeystoreService;
import com.zoecasheth.app.service.AlphaWalletService;
import com.zoecasheth.app.service.AnalyticsService;
import com.zoecasheth.app.service.AnalyticsServiceType;
import com.zoecasheth.app.service.AssetDefinitionService;
import com.zoecasheth.app.service.GasService;
import com.zoecasheth.app.service.GasService2;
import com.zoecasheth.app.service.KeyService;
import com.zoecasheth.app.service.KeystoreAccountService;
import com.zoecasheth.app.service.MarketQueueService;
import com.zoecasheth.app.service.NotificationService;
import com.zoecasheth.app.service.OpenseaService;
import com.zoecasheth.app.service.RealmManager;
import com.zoecasheth.app.service.TickerService;
import com.zoecasheth.app.service.TokensService;
import com.zoecasheth.app.service.TransactionsNetworkClient;
import com.zoecasheth.app.service.TransactionsNetworkClientType;
import com.zoecasheth.app.service.TransactionsService;
import com.google.gson.Gson;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
public class RepositoriesModule {
	@Singleton
	@Provides
    PreferenceRepositoryType providePreferenceRepository(Context context) {
		return new SharedPreferenceRepository(context);
	}

	@Singleton
	@Provides
    AccountKeystoreService provideAccountKeyStoreService(Context context, KeyService keyService) {
        File file = new File(context.getFilesDir(), KeystoreAccountService.KEYSTORE_FOLDER);
		return new KeystoreAccountService(file, context.getFilesDir(), keyService);
	}

	@Singleton
    @Provides
    TickerService provideTickerService(OkHttpClient httpClient, Gson gson, Context context, TokenLocalSource localSource) {
		return new TickerService(httpClient, gson, context, localSource);
    }

	@Singleton
	@Provides
    EthereumNetworkRepositoryType provideEthereumNetworkRepository(
            PreferenceRepositoryType preferenceRepository,
			Context context) {
		return new EthereumNetworkRepository(preferenceRepository, context);
	}

	@Singleton
	@Provides
    WalletRepositoryType provideWalletRepository(
			PreferenceRepositoryType preferenceRepositoryType,
			AccountKeystoreService accountKeystoreService,
			EthereumNetworkRepositoryType networkRepository,
			WalletDataRealmSource walletDataRealmSource,
			KeyService keyService) {
		return new WalletRepository(
		        preferenceRepositoryType, accountKeystoreService, networkRepository, walletDataRealmSource, keyService);
	}

	@Singleton
	@Provides
    TransactionRepositoryType provideTransactionRepository(
			EthereumNetworkRepositoryType networkRepository,
			AccountKeystoreService accountKeystoreService,
            TransactionLocalSource inDiskCache,
			TransactionsService transactionsService) {
		return new TransactionRepository(
				networkRepository,
				accountKeystoreService,
				inDiskCache,
				transactionsService);
	}

	@Singleton
	@Provides
    OnRampRepositoryType provideOnRampRepository(Context context, AnalyticsServiceType analyticsServiceType) {
		return new OnRampRepository(context, analyticsServiceType);
	}

	@Singleton
    @Provides
    TransactionLocalSource provideTransactionInDiskCache(RealmManager realmManager) {
        return new TransactionsRealmCache(realmManager);
    }

	@Singleton
	@Provides
    TransactionsNetworkClientType provideBlockExplorerClient(
			OkHttpClient httpClient,
			Gson gson,
			RealmManager realmManager) {
		return new TransactionsNetworkClient(httpClient, gson, realmManager);
	}

	@Singleton
    @Provides
    TokenRepositoryType provideTokenRepository(
            EthereumNetworkRepositoryType ethereumNetworkRepository,
            TokenLocalSource tokenLocalSource,
			OkHttpClient httpClient,
			Context context,
			TickerService tickerService) {
	    return new TokenRepository(
	            ethereumNetworkRepository,
				tokenLocalSource,
				httpClient,
				context,
				tickerService);
    }

    @Singleton
    @Provides
    TokenLocalSource provideRealmTokenSource(RealmManager realmManager, EthereumNetworkRepositoryType ethereumNetworkRepository) {
	    return new TokensRealmSource(realmManager, ethereumNetworkRepository);
    }

	@Singleton
	@Provides
	WalletDataRealmSource provideRealmWalletDataSource(RealmManager realmManager) {
		return new WalletDataRealmSource(realmManager);
	}

	@Singleton
	@Provides
    TokensService provideTokensService(EthereumNetworkRepositoryType ethereumNetworkRepository,
                                       TokenRepositoryType tokenRepository,
                                       PreferenceRepositoryType preferenceRepository,
                                       Context context,
                                       TickerService tickerService,
                                       OpenseaService openseaService,
                                       AnalyticsServiceType analyticsService) {
		return new TokensService(ethereumNetworkRepository, tokenRepository, preferenceRepository, context, tickerService, openseaService, analyticsService);
	}

	@Singleton
	@Provides
	TransactionsService provideTransactionsService(TokensService tokensService,
												   PreferenceRepositoryType preferenceRepository,
												   EthereumNetworkRepositoryType ethereumNetworkRepositoryType,
												   TransactionsNetworkClientType transactionsNetworkClientType,
												   TransactionLocalSource transactionLocalSource,
												   Context context) {
		return new TransactionsService(tokensService, preferenceRepository, ethereumNetworkRepositoryType, transactionsNetworkClientType, transactionLocalSource, context);
	}

	@Singleton
	@Provides
    GasService provideGasService(EthereumNetworkRepositoryType ethereumNetworkRepository) {
		return new GasService(ethereumNetworkRepository);
	}

	@Singleton
	@Provides
    GasService2 provideGasService2(EthereumNetworkRepositoryType ethereumNetworkRepository, OkHttpClient client, RealmManager realmManager) {
		return new GasService2(ethereumNetworkRepository, client, realmManager);
	}

	@Singleton
	@Provides
    MarketQueueService provideMarketQueueService(Context ctx, OkHttpClient okHttpClient,
                                                 TransactionRepositoryType transactionRepository) {
		return new MarketQueueService(ctx, okHttpClient, transactionRepository);
	}

	@Singleton
	@Provides
    OpenseaService provideOpenseaService(Context ctx) {
		return new OpenseaService(ctx);
	}

	@Singleton
	@Provides
    AlphaWalletService provideFeemasterService(OkHttpClient okHttpClient,
                                               TransactionRepositoryType transactionRepository,
                                               Gson gson) {
		return new AlphaWalletService(okHttpClient, transactionRepository, gson);
	}

	@Singleton
	@Provides
    NotificationService provideNotificationService(Context ctx) {
		return new NotificationService(ctx);
	}

	@Singleton
	@Provides
    AssetDefinitionService provideAssetDefinitionService(OkHttpClient okHttpClient, Context ctx, NotificationService notificationService, RealmManager realmManager,
                                                         EthereumNetworkRepositoryType ethereumNetworkRepository, TokensService tokensService,
                                                         TokenLocalSource tls, TransactionRepositoryType trt, AlphaWalletService alphaService) {
		return new AssetDefinitionService(okHttpClient, ctx, notificationService, realmManager, ethereumNetworkRepository, tokensService, tls, trt, alphaService);
	}

	@Singleton
	@Provides
	KeyService provideKeyService(Context ctx) {
		return new KeyService(ctx);
	}

	@Singleton
	@Provides
	AnalyticsServiceType provideAnalyticsService(Context ctx) {
		return new AnalyticsService(ctx);
	}
}
