package com.zoecasheth.app.ui.widget;

import com.zoecasheth.app.entity.tokens.Token;

public interface OnTokenManageClickListener
{
    void onTokenClick(Token token, int position, boolean isChecked);
}
