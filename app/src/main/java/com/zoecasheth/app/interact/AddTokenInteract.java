package com.zoecasheth.app.interact;

import com.zoecasheth.app.entity.ContractType;
import com.zoecasheth.app.entity.tokens.Token;
import com.zoecasheth.app.entity.tokens.TokenInfo;
import com.zoecasheth.app.entity.Wallet;
import com.zoecasheth.app.repository.TokenRepositoryType;

import io.reactivex.Observable;

public class AddTokenInteract {
    private final TokenRepositoryType tokenRepository;

    public AddTokenInteract(
            TokenRepositoryType tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public Observable<Token> add(TokenInfo tokenInfo, ContractType type, Wallet wallet) {
        return tokenRepository
                        .addToken(wallet, tokenInfo, type).toObservable();
    }
}
