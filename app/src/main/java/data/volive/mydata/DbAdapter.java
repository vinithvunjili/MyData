package data.volive.mydata;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
public class DbAdapter extends RecyclerView.Adapter<DbAdapter.ViewHolder> {
    Context activity;
    List<DataModel> arraylist;
    public DbAdapter(Context applicationContext, List<DataModel> arraylist) {
        this.activity = applicationContext;
        this.arraylist = arraylist;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myhomelist, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final DataModel model = arraylist.get(position);
        holder.text.setText(model.getName());
//        byte[] outImage=model.img;
//        ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
//        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
//        holder.image.setImageBitmap(convertToBitmap(model.getImg()));
        holder.switch_button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(activity, "Checked" + model.getName(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(activity, "Unchecked" + model.getName(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return arraylist.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView text;
        Switch switch_button;

        public ViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.image);
            text = view.findViewById(R.id.text);
            switch_button = view.findViewById(R.id.switch_button);
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Toast.makeText(activity, "selected", Toast.LENGTH_LONG).show();
                    return false;
                }
            });
        }
    }
    private Bitmap convertToBitmap(byte[] b) {
        return BitmapFactory.decodeByteArray(b, 0, b.length);
    }
}
