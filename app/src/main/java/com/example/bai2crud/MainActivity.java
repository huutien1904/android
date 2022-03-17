package com.example.bai2crud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bai2crud.model.Cat;
import com.example.bai2crud.model.CatAdapter;
import com.example.bai2crud.model.SpinnerAdapter;

public class MainActivity extends AppCompatActivity implements CatAdapter.CatItemListener{
//    gắn dữ liệu cho người dùng chọn ở spinner
    private Spinner sp;
    private int[] imgs = {R.drawable.anh1,R.drawable.anh2,R.drawable.anh3,R.drawable.anh4,R.drawable.anh5,R.drawable.anh6,};
    private RecyclerView recyclerView;
    private CatAdapter adapter;
//    gắn recyclerview và các dữ liệu của ô cần nhập liệu
    private EditText eName,eDesc,ePrice;
    private Button btAdd,btUpdate;
    private int pcurr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intiView();
        adapter = new CatAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
//        bắt sự kiện cho button add
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cat cat = new Cat();
                String i = sp.getSelectedItem().toString();
                String name = eName.getText().toString();
                String desc = eDesc.getText().toString();
                String p = ePrice.getText().toString();

                int img = R.drawable.anh1;
                double price =0;
                try{
                    img = imgs[Integer.parseInt(i)];
                    price = Double.parseDouble(p);
                    cat.setImg(img);
                    cat.setName(name);
                    cat.setDescribe(desc);
                    cat.setPrice(price);
                    adapter.add(cat);
                }catch (NumberFormatException e){
                    Toast.makeText(MainActivity.this, "Nhập lại đầy đủ đi ", Toast.LENGTH_SHORT).show();
                }

            }
        });
//        bắt sự kiện cho nút update
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cat cat = new Cat();
                String i = sp.getSelectedItem().toString();
                String name = eName.getText().toString();
                String desc = eDesc.getText().toString();
                String p = ePrice.getText().toString();

                int img = R.drawable.anh1;
                double price =0;
                try{
                    img = imgs[Integer.parseInt(i)];
                    price = Double.parseDouble(p);
                    cat.setImg(img);
                    cat.setName(name);
                    cat.setDescribe(desc);
                    cat.setPrice(price);
                    adapter.update(pcurr,cat);
                    btAdd.setEnabled(true);
                    btUpdate.setEnabled(false);
                }catch (NumberFormatException e){
                    Toast.makeText(MainActivity.this, "Nhập lại đầy đủ đi ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void intiView() {
        sp = findViewById(R.id.img);
        SpinnerAdapter adapter = new SpinnerAdapter(this);
        sp.setAdapter(adapter);
        recyclerView = findViewById(R.id.recycleView);
        eName = findViewById(R.id.name);
        eDesc = findViewById(R.id.describe);
        ePrice = findViewById(R.id.price);
        btAdd = findViewById(R.id.btAdd);
        btUpdate = findViewById(R.id.btUpdate);
        btUpdate.setEnabled(false);
    }


//     sau khi click vào recycleview
    @Override
    public void onItemClick(View view, int position) {
        btAdd.setEnabled(false);
        btUpdate.setEnabled(true);
        pcurr = position;
        Cat cat = adapter.getItem(position);
        int img = cat.getImg();
        int p = 0;
        for (int i = 0; i <imgs.length ; i++) {
            if (img == imgs[i]) {
                p = i;
                break;
            }
        }
            sp.setSelection(p);
            eName.setText(cat.getName());
            eDesc.setText(cat.getDescribe());
            ePrice.setText(cat.getPrice()+"");

    }
}