package com.ltsonline.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ltsonline.GlobalElements;
import com.ltsonline.R;
import com.ltsonline.custom.ScaleImageView;
import com.ltsonline.model.ProductModel;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Suleiman on 26-07-2015.
 */
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    ArrayList<ProductModel> data = new ArrayList<>();
    Context context;
    private DisplayImageOptions options;
    protected ImageLoader imageLoader = ImageLoader.getInstance();

    String uid, cart_id;

    public CartAdapter(Context context, ArrayList<ProductModel> da, String uid) {
        this.context = context;
        this.data = da;
        this.uid = uid;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cart, parent, false);
        ViewHolder holder = new ViewHolder(layoutView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        GlobalElements.overrideFonts_P_Nova_Rg_Regular(context, holder.linearLayout);
        holder.name.setText(data.get(position).getName());
        holder.sell_price.setText("Rs." + data.get(position).getSell_price());
        holder.max_price.setText("Rs." + data.get(position).getMax_price());
        holder.max_price.setPaintFlags(holder.max_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
        options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.defult_product)
                .showImageOnFail(R.drawable.defult_product)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        imageLoader.displayImage(data.get(position).getUrl(), holder.pro_image, options);

        holder.remove_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    cart_id = data.get(position).getId();
                    //new Remove_cart().execute("remove_cart");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.list_cart_pro_image)
        ScaleImageView pro_image;
        @BindView(R.id.list_cart_name)
        TextView name;
        @BindView(R.id.list_cart_max_price)
        TextView max_price;
        @BindView(R.id.list_cart_sell_price)
        TextView sell_price;
        @BindView(R.id.list_cart_linear)
        LinearLayout linearLayout;
        @BindView(R.id.list_cart_remove)
        ImageView remove_cart;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface InterfaceCommunicator {
        void sendRemovemsg(int code);
    }


    /*class Remove_cart extends AsyncTask<String, Void, JSONObject> {

        ProgressDialog pd;
        String temp = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(context);
            pd.setTitle("Please Wait");
            pd.setMessage("Loading");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {
            temp = args[0];
            if (temp.equals("remove_cart")) {
                UserFunction uf = new UserFunction();
                JSONObject json = uf.Removecart(uid, cart_id);
                return json;
            }
            return null;
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            super.onPostExecute(json);

            if (temp.equals("remove_cart")) {
                try {
                    if (json.getInt("ack") == 1) {
                        GlobalElements.cart_item = json.getInt("cart_item");
                        Toast.makeText(context, "" + json.getString("ack_msg"), Toast.LENGTH_LONG).show();
                        CartAdapter.InterfaceCommunicator i = (CartAdapter.InterfaceCommunicator) context;
                        i.sendRemovemsg(1);
                    } else {
                        CartAdapter.InterfaceCommunicator i = (CartAdapter.InterfaceCommunicator) context;
                        i.sendRemovemsg(0);
                        Toast.makeText(context, "" + json.getString("ack_msg"), Toast.LENGTH_LONG).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            pd.dismiss();
        }
    }*/

}
