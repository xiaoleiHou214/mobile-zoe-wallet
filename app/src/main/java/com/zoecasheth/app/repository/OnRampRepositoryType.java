package com.zoecasheth.app.repository;

import com.zoecasheth.app.entity.OnRampContract;
import com.zoecasheth.app.entity.tokens.Token;

public interface OnRampRepositoryType {
    String getUri(String address, Token token);

    OnRampContract getContract(Token token);
}
