package android.maxim.retrofitauthtoken.ui.activity;

import android.maxim.retrofitauthtoken.databinding.ContentBaseBinding;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.maxim.retrofitauthtoken.databinding.ContentBaseBinding binding
                = ContentBaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}