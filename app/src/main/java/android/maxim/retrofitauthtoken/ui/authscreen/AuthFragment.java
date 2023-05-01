package android.maxim.retrofitauthtoken.ui.authscreen;

import android.maxim.retrofitauthtoken.R;
import android.maxim.retrofitauthtoken.databinding.FragmentAuthBinding;
import android.maxim.retrofitauthtoken.model.Api;
import android.maxim.retrofitauthtoken.model.AuthData;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

@AndroidEntryPoint
public class AuthFragment extends Fragment {

    private FragmentAuthBinding binding;
    @Inject
    Api api;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentAuthBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(AuthFragment.this)
                        .navigate(R.id.action_AuthFragment_to_ProductsFragment);
            }
        });

        binding.btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth(
                        new AuthData(
                                //TODO Refactor hardcode
                                /*binding.tvUsername.getText().toString(),
                                binding.tvFirstName.getText().toString()*/
                                "rshawe2",
                                "OWsTbMUgFc"
                        )
                );
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    //TODO Move auth method in viewModel
    //TODO Handle the error in auth
    private void auth(AuthData authData) {
        api.auth(authData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        user -> {
                            binding.tvFirstName.setText(user.firstName);
                            binding.tvLastName.setText(user.lastName);
                            Picasso.get().load(user.image).into(binding.ivAvatar);
                            binding.btnNext.setVisibility(View.VISIBLE);
                        },
                        user -> {}
                );
    }

}