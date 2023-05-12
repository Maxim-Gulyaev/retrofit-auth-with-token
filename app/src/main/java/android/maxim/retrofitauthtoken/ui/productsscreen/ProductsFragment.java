package android.maxim.retrofitauthtoken.ui.productsscreen;

import android.maxim.retrofitauthtoken.databinding.FragmentProductsBinding;
import android.maxim.retrofitauthtoken.model.Api;
import android.maxim.retrofitauthtoken.ui.authscreen.AuthViewModel;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import javax.inject.Inject;
import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@AndroidEntryPoint
public class ProductsFragment extends Fragment {

    @Inject
    CompositeDisposable compositeDisposable;
    @Inject
    Api api;
    private FragmentProductsBinding binding;
    private AuthViewModel authViewModel;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        authViewModel = new ViewModelProvider(requireActivity()).get(AuthViewModel.class);
        binding = FragmentProductsBinding.inflate(inflater, container, false);

        initRecycler();

        return binding.getRoot();
    }

    private void initRecycler() {
        authViewModel.token.observe(getViewLifecycleOwner(), token ->
                compositeDisposable.add(
                Single.just(token)
                        .subscribeOn(Schedulers.io())
                        .flatMap(it -> api.getProducts(token))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(it -> {
                            binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                            binding.recyclerView.setAdapter(new ProductsAdapter(it.products, getContext()));
                        }, this::showError)
        ));
    }

    private void showError(Throwable error) {
        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        compositeDisposable.dispose();
    }
}