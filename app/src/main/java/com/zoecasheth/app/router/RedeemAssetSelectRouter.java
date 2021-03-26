package com.zoecasheth.app.router;

import android.content.Context;
import android.content.Intent;

import com.zoecasheth.app.C;
import com.zoecasheth.app.ui.RedeemAssetSelectActivity;
import com.zoecasheth.app.entity.tokens.Token;

/**
 * Created by James on 27/02/2018.
 */

public class RedeemAssetSelectRouter
{
    public void open(Context context, Token ticket) {
        Intent intent = new Intent(context, RedeemAssetSelectActivity.class);
        intent.putExtra(C.Key.TICKET, ticket);
        intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        context.startActivity(intent);
    }
}
