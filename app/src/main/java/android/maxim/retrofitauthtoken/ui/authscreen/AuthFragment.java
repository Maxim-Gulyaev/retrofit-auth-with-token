package android.maxim.retrofitauthtoken.ui.authscreen;

import android.maxim.retrofitauthtoken.R;
import android.maxim.retrofitauthtoken.databinding.FragmentAuthBinding;
import android.maxim.retrofitauthtoken.model.Api;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import com.squareup.picasso.Picasso;
import javax.inject.Inject;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AuthFragment extends Fragment {

    private FragmentAuthBinding binding;
    private AuthViewModel authViewModel;
    @Inject
    Api api;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        authViewModel = new ViewModelProvider(requireActivity()).get(AuthViewModel.class);
        binding = FragmentAuthBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnNext.setOnClickListener(
                view1 -> NavHostFragment.findNavController(AuthFragment.this)
                .navigate(R.id.action_AuthFragment_to_ProductsFragment));


        //TODO Delete hardcode
        binding.btnEnter.setOnClickListener(v -> {
            authViewModel.auth("dpettegre6",
                    "YVmhktgYVS"
                    /*binding.tvUsername.getText().toString(),
                    binding.tvFirstName.getText().toString()*/
            );
            showUserData();
            turnOnButtonNext();
        });
    }

    private void showUserData() {
        authViewModel.mutableLiveDataUser.observe(getViewLifecycleOwner(), user -> {
            binding.tvFirstName.setText(user.firstName);
            binding.tvLastName.setText(user.lastName);
            Picasso.get().load(user.image).into(binding.ivAvatar);
        });
    }

    private void turnOnButtonNext() {
        binding.btnNext.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}