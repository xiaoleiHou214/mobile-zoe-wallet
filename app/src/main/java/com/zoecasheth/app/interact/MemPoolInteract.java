package com.zoecasheth.app.interact;

import com.zoecasheth.app.repository.TokenRepositoryType;

import io.reactivex.Observable;

import com.zoecasheth.app.entity.TransferFromEventResponse;

/**
 * Created by James on 1/02/2018.
 */

public class MemPoolInteract
{
    private final TokenRepositoryType tokenRepository;

    public MemPoolInteract(TokenRepositoryType tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    //create an observable
    public Observable<TransferFromEventResponse> burnListener(String contractAddress) {
        return tokenRepository.burnListenerObservable(contractAddress);
    }
}
