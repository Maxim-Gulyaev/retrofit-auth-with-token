package android.maxim.retrofitauthtoken.ui.authscreen;

import android.maxim.retrofitauthtoken.R;
import android.maxim.retrofitauthtoken.databinding.FragmentAuthBinding;
import android.maxim.retrofitauthtoken.model.Api;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
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

        binding.btnEnter.setOnClickListener(v -> {
            String userName = binding.tvUsername.getText().toString();
            String password = binding.tvPassword.getText().toString();

            if(userName.length() != 0 && password.length() != 0) {
                authViewModel.auth(userName, password);
                showUserData();
                turnOnButtonNext();
            } else {
                Toast.makeText(getContext(), R.string.fill_credentials, Toast.LENGTH_LONG)
                        .show();
            }
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