package com.zoecasheth.app.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.zoecasheth.app.repository.CurrencyRepositoryType;
import com.zoecasheth.app.repository.LocaleRepositoryType;
import com.zoecasheth.app.repository.PreferenceRepositoryType;
import com.zoecasheth.app.service.AssetDefinitionService;

public class AdvancedSettingsViewModelFactory implements ViewModelProvider.Factory {
    private final LocaleRepositoryType localeRepository;
    private final CurrencyRepositoryType currencyRepository;
    private final AssetDefinitionService assetDefinitionService;
    private final PreferenceRepositoryType preferenceRepository;

    public AdvancedSettingsViewModelFactory(
            LocaleRepositoryType localeRepository,
            CurrencyRepositoryType currencyRepository,
            AssetDefinitionService assetDefinitionService,
            PreferenceRepositoryType preferenceRepository) {
        this.localeRepository = localeRepository;
        this.currencyRepository = currencyRepository;
        this.assetDefinitionService = assetDefinitionService;
        this.preferenceRepository = preferenceRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AdvancedSettingsViewModel(
                localeRepository,
                currencyRepository,
                assetDefinitionService,
                preferenceRepository
        );
    }
}