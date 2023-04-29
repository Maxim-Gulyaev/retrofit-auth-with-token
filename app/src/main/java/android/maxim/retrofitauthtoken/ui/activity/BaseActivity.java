package android.maxim.retrofitauthtoken.ui.activity;

import android.maxim.retrofitauthtoken.databinding.ContentBaseBinding;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    private ContentBaseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ContentBaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}