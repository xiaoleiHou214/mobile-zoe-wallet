package com.zoecasheth.app.entity;

import android.content.Context;

import com.zoecasheth.app.C;
import com.zoecasheth.app.entity.tokens.Token;
import com.zoecasheth.app.entity.tokens.TokenCardMeta;
import com.zoecasheth.app.entity.tokens.TokenInfo;
import com.zoecasheth.app.repository.EthereumNetworkRepository;
import com.zoecasheth.app.service.TokensService;
import com.zoecasheth.app.ui.widget.entity.NetworkItem;

import java.util.concurrent.ConcurrentLinkedQueue;

public class CustomViewSettings
{
    private static int primaryChain = EthereumNetworkRepository.RINKEBY_ID;
    private static String primaryChainName = C.RINKEBY_NETWORK_NAME;

    public static boolean filterToken(TokenCardMeta token, boolean filterResult, Context context)
    {
        boolean badToken = (token.nameWeight == Integer.MAX_VALUE);
        //boolean badToken = token.isTerminated() || token.isBad();
        //if (!token.tokenInfo.isEnabled) filterResult = false;
        if (token.isEthereum()) filterResult = true;

        if (token.type == ContractType.LEGACY_DYNAMIC_CONTRACT
                || token.type == ContractType.ETHEREUM_INVISIBLE) { filterResult = false;}

        return !badToken && filterResult;
    }

    public static void addPriorityTokens(ConcurrentLinkedQueue<ContractLocator> unknownAddresses, TokensService tokensService)
    {

    }

    public static ContractType checkKnownTokens(TokenInfo tokenInfo)
    {
        return ContractType.OTHER;
    }

    public static boolean showContractAddress(Token token)
    {
        return true;
    }

    public static boolean isPrimaryNetwork(NetworkItem item)
    {
        return item.getChainId() == primaryChain;
    }

    public static String primaryNetworkName()
    {
        return primaryChainName;
    }

    public static long startupDelay()
    {
        return 0;
    }

    public static int getImageOverride()
    {
        return 0;
    }

    //Switch off dapp browser
    public static boolean hideDappBrowser()
    {
        return false;
    }

    //Hides the filter tab bar at the top of the wallet screen (ALL/CURRENCY/COLLECTIBLES)
    public static boolean hideTabBar()
    {
        return false;
    }

    //Use to switch off direct transfer, only use magiclink transfer
    public static boolean hasDirectTransfer()
    {
        return true;
    }

    //Allow multiple wallets (true) or single wallet mode (false)
    public static boolean canChangeWallets()
    {
        return true;
    }

    //Hide EIP681 generation (Payment request, generates a QR code another wallet user can scan to have all payment fields filled in)
    public static boolean hideEIP681() { return false; }

    //In main wallet menu, if wallet allows adding new tokens
    public static boolean canAddTokens() { return true; }

    //Implement minimal dappbrowser with no URL bar.
    public static boolean minimiseBrowserURLBar() { return false; }

    //Allow showing token management view
    public static boolean showManageTokens() { return true; }
}