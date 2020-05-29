package com.example.ytmd.ViewModels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.ytmd.Activities.SearchPlaylistActivity;

import javax.inject.Inject;
import javax.inject.Provider;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final Provider<SearchViewModel> searchViewModelProvider;

    private final Provider<SearchPlaylistViewModel> searchPlaylistViewModelProvider;

    @Inject
    public ViewModelFactory(Provider<SearchViewModel> myViewModelProvider,
                            Provider<SearchPlaylistViewModel> searchPlaylistViewModelProvider) {
        searchViewModelProvider = myViewModelProvider;
        this.searchPlaylistViewModelProvider = searchPlaylistViewModelProvider;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        ViewModel viewModel;
        if (modelClass == SearchViewModel.class) {
            viewModel =searchViewModelProvider.get();
        } else  if (modelClass == SearchPlaylistViewModel.class) {
            viewModel =searchPlaylistViewModelProvider.get();
        }
        else {
            throw new RuntimeException("unsupported view model class: " + modelClass);
        }

        return (T) viewModel;
    }
}