package com.qtum.walletkeystore.utils;

import org.bitcoinj.wallet.DeterministicSeed;
import org.bitcoinj.wallet.Wallet;
import org.bitcoinj.crypto.DeterministicHierarchy;
import org.bitcoinj.wallet.UnreadableWalletException;
import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.crypto.ChildNumber;
import org.bitcoinj.crypto.HDUtils;

import java.util.List;
import java.util.ArrayList;

import com.google.common.collect.ImmutableList;


public class KeyStorage {

    private final int ADDRESSES_COUNT = 10;
    private Wallet sWallet = null;
    private List<DeterministicKey> mDeterministicKeyList;
    private List<String> mAddressesList;

    public void createWallet() {
        String mnemonicCode = "";
        for (int i = 0; i < 11; i++) {
            mnemonicCode += DictionaryWords.getRandomWord() + " ";
        }
        mnemonicCode += DictionaryWords.getRandomWord();

        String passphrase = "";
        DeterministicSeed seed = null;
        try {
            seed = new DeterministicSeed(mnemonicCode, null, passphrase, DeterministicHierarchy.BIP32_STANDARDISATION_TIME_SECS);
        } catch (UnreadableWalletException e) {
            e.printStackTrace();
        }
        if (seed != null) {
            sWallet = Wallet.fromSeed(CurrentNetParams.getNetParams(), seed);
        }
        System.out.println("Word: "+mnemonicCode);
        getKeyList();
    }

    public List<DeterministicKey> getKeyList() {
        if (mDeterministicKeyList == null) {
            mDeterministicKeyList = new ArrayList<>(ADDRESSES_COUNT);
            mAddressesList = new ArrayList<>();
            List<ChildNumber> pathParent = new ArrayList<>();
            pathParent.add(new ChildNumber(88,true));
            pathParent.add(new ChildNumber(0,true));
            for (int i = 0; i < ADDRESSES_COUNT; i++) {
                ImmutableList<ChildNumber> path = HDUtils.append(pathParent, new ChildNumber(i, true));
                DeterministicKey k = sWallet.getActiveKeyChain().getKeyByPath(path,true);
                mDeterministicKeyList.add(k);
                String Address = k.toAddress(CurrentNetParams.getNetParams()).toString();
                mAddressesList.add(Address);
                System.out.println("Address: "+Address+" KEY: "+k.getPrivateKeyAsWiF(CurrentNetParams.getNetParams()).toString());

            }
        }
        return mDeterministicKeyList;
    }
    public void importWallet(final String seedString) {
            String passphrase = "";
            DeterministicSeed seed = null;
            try {
                seed = new DeterministicSeed(seedString, null, passphrase, DeterministicHierarchy.BIP32_STANDARDISATION_TIME_SECS);

            } catch (UnreadableWalletException e) {
                e.printStackTrace();
            }
            if (seed != null) {
                sWallet = Wallet.fromSeed(CurrentNetParams.getNetParams(), seed);
            }
            getKeyList();
    }
}
