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

    private static SearchPlaylistViewModel searchPlaylistViewModel;
    private static SearchViewModel searchViewModel;

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        ViewModel viewModel;
        if (modelClass == SearchViewModel.class) {
            if(searchPlaylistViewModel == null ){
                searchViewModel = searchViewModelProvider.get();
            }
            viewModel =searchViewModel;
        } else  if (modelClass == SearchPlaylistViewModel.class) {
            if(searchPlaylistViewModel == null ){
                searchPlaylistViewModel = searchPlaylistViewModelProvider.get();
            }
            viewModel = searchPlaylistViewModel;
        }
        else {
            throw new RuntimeException("unsupported view model class: " + modelClass);
        }

        return (T) viewModel;
    }
}