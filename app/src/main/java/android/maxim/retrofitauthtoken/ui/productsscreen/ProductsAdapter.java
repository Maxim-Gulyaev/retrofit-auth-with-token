package android.maxim.retrofitauthtoken.ui.productsscreen;

import android.content.Context;
import android.maxim.retrofitauthtoken.R;
import android.maxim.retrofitauthtoken.model.Product;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>{
    public List<Product> productsList;
    Context context;

    public ProductsAdapter(List<Product> productsList, Context context) {
        this.productsList = productsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false);
        return new ProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsViewHolder holder, int position) {
        holder.title.setText(productsList.get(position).title);
        holder.description.setText(productsList.get(position).description);
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public static class ProductsViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;

        public ProductsViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            description = itemView.findViewById(R.id.tv_description);
        }
    }
}
