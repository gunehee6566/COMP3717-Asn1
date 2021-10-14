package ca.bcit.assignment1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private Context _context;
    private ArrayList<Developer> _contriList;

    public RecyclerAdapter(Context context, ArrayList<Developer> toonArrayList) {
        _context = context;
        _contriList = toonArrayList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView _cardView;

        public ViewHolder(CardView v) {
            super(v);
            _cardView = v;
        }
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(_context)
                .inflate(R.layout.card_item, parent, false);

        return new ViewHolder(cv);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        final CardView cardView = holder._cardView;

        Developer currentItem = _contriList.get(position);

        TextView tvId = cardView.findViewById(R.id.id);
        ImageView ivPictureUrl = cardView.findViewById(R.id.image_view);

        tvId.setText(String.valueOf(currentItem.getUsername()));


        if (currentItem.getPictureUrl() != null) {
            Picasso.with(_context)
                    .load(currentItem.getPictureUrl())
                    .fit()
                    .centerInside()
                    .into(ivPictureUrl);

        }

    }

    @Override
    public int getItemCount() {
        return _contriList.size();
    }
}
