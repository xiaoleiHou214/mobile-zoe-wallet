package com.zoecasheth.app.entity;

import android.content.Context;

import com.zoecasheth.app.service.KeyService;

public interface CreateWalletCallbackInterface
{
    void HDKeyCreated(String address, Context ctx, KeyService.AuthenticationLevel level);
    void keyFailure(String message);
    void cancelAuthentication();
    void fetchMnemonic(String mnemonic);
    default void keyUpgraded(KeyService.UpgradeKeyResult result) { };
}
