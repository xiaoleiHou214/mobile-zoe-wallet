package com.zoecasheth.app.interact;

import com.zoecasheth.app.repository.WalletRepositoryType;
import com.zoecasheth.app.util.BalanceUtils;

import io.reactivex.disposables.Disposable;
import com.zoecasheth.app.entity.Wallet;
import com.zoecasheth.app.entity.tokens.Token;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.realm.Realm;

import java.math.BigDecimal;

import static com.zoecasheth.app.C.ETHER_DECIMALS;

public class GenericWalletInteract
{
	private final WalletRepositoryType walletRepository;

	public GenericWalletInteract(WalletRepositoryType walletRepository) {
		this.walletRepository = walletRepository;
	}

	public Single<Wallet> find() {
		return walletRepository
				.getDefaultWallet()
				.onErrorResumeNext(walletRepository
						.fetchWallets()
						.to(single -> Flowable.fromArray(single.blockingGet()))
						.firstOrError()
						.flatMapCompletable(walletRepository::setDefaultWallet)
						.andThen(walletRepository.getDefaultWallet()))
				.observeOn(AndroidSchedulers.mainThread());
	}

	/**
	 * Called when wallet marked as backed up.
	 * Update the wallet date
	 *
	 * @param walletAddr
	 */
	public Disposable updateBackupTime(String walletAddr)
	{
		return walletRepository.updateBackupTime(walletAddr);
	}

	/**
	 * Called when wallet has had its backup warning dismissed
	 * Update the wallet date
	 *
	 * @param walletAddr
	 */
	public Disposable updateWarningTime(String walletAddr)
	{
		return walletRepository.updateWarningTime(walletAddr);
	}

	public Single<String> getWalletNeedsBackup(String walletAddr)
	{
		return walletRepository.getWalletRequiresBackup(walletAddr);
	}

	public Single<String> setIsDismissed(String walletAddr, boolean isDismissed)
	{
		return walletRepository.setIsDismissed(walletAddr, isDismissed);
	}

	/**
	 * Check if a wallet needs to be backed up.
	 * @param walletAddr
	 * @return
	 */
	public Single<Boolean> getBackupWarning(String walletAddr)
	{
		return walletRepository.getWalletBackupWarning(walletAddr);
	}

    public Single<Wallet> getWallet(String keyAddress)
    {
    	return walletRepository.findWallet(keyAddress);
    }

	private boolean hasBalance(Wallet wallet)
	{
		String balance = wallet.balance;
		if (balance == null || balance.length() == 0 || !BalanceUtils.isDecimalValue(balance)) return false;
		BigDecimal b = new BigDecimal(balance);
		return b.compareTo(BigDecimal.ZERO) > 0;
	}

	public Single<Wallet> updateBalanceIfRequired(Wallet wallet, BigDecimal newBalance)
	{
		String newBalanceStr = BalanceUtils.getScaledValueFixed(newBalance, ETHER_DECIMALS, Token.TOKEN_BALANCE_PRECISION);
		if (!newBalance.equals(BigDecimal.valueOf(-1)) && !wallet.balance.equals(newBalanceStr))
		{
			wallet.balance = newBalanceStr;
			return walletRepository.updateWalletData(wallet);
		}
		else
		{
			return Single.fromCallable(() -> wallet);
		}
	}

	public Realm getWalletRealm()
	{
		return walletRepository.getWalletRealm();
	}

    public enum BackupLevel
	{
		BACKUP_NOT_REQUIRED, WALLET_HAS_LOW_VALUE, WALLET_HAS_HIGH_VALUE
	}
}