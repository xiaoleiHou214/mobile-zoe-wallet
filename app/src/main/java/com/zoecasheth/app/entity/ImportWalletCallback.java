package com.zoecasheth.app.entity;

import com.zoecasheth.app.service.KeyService;

public interface ImportWalletCallback
{
    void WalletValidated(String address, KeyService.AuthenticationLevel level);
    void KeystoreValidated(String newPassword, KeyService.AuthenticationLevel level);
    void KeyValidated(String newPassword, KeyService.AuthenticationLevel authLevel);
}
