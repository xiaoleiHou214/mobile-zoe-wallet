package com.zoecasheth.app.entity.tokenscript;

import com.zoecasheth.token.entity.XMLDsigDescriptor;

public class TokenScriptFileData
{
    public String hash;
    public XMLDsigDescriptor sigDescriptor;

    public TokenScriptFileData()
    {
        hash = null;
        sigDescriptor = null;
    }
}
