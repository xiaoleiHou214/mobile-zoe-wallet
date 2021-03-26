package com.zoecasheth.app.router;

import android.content.Context;
import android.content.Intent;

import com.zoecasheth.app.C;
import com.zoecasheth.app.entity.Wallet;
import com.zoecasheth.app.ui.AssetDisplayActivity;
import com.zoecasheth.app.entity.tokens.Token;

/**
 * Created by James on 22/01/2018.
 */

public class AssetDisplayRouter {

    public void open(Context context, Token ticket, Wallet wallet) {
        Intent intent = new Intent(context, AssetDisplayActivity.class);
        intent.putExtra(C.Key.TICKET, ticket);
        intent.putExtra(C.Key.WALLET, wallet);
        intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        context.startActivity(intent);
    }
}
