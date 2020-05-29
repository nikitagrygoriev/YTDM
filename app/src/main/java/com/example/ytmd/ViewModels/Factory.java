package com.example.ytmd.ViewModels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import kotlin.annotation.AnnotationTarget;

/**
 * https://www.techyourchance.com/dependency-injection-viewmodel-with-dagger-2/
 * Implementations of {@code Factory} interface are responsible to instantiate ViewModels.
 */
public interface Factory {
    /**
     * Creates a new instance of the given {@code Class}.
     * <p>
     *
     * @param modelClass a {@code Class} whose instance is requested
     * @param <T>        The type parameter for the ViewModel.
     * @return a newly created ViewModel
     */
    @NonNull
    <T extends ViewModel> T create(@NonNull Class<T> modelClass);
}
