package com.banketree.xinlanrichtextor;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mabeijianxi.jianxiexpression.ExpressionGridFragment;
import com.mabeijianxi.jianxiexpression.ExpressionManager;
import com.mabeijianxi.jianxiexpression.ExpressionShowFragment;
import com.ttm.richtextor.CharFilter;
import com.ttm.richtextor.RichClickListener;
import com.ttm.richtextor.RichEditText;
import com.ttm.richtextor.RichTextView;
import com.ttm.richtextor.model.RichModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity  {

    public final static int REQUEST_USER_CODE_CLICK = 2222;
    public final static int REQUEST_STOCK_CODE_CLICK = 3333;

    String[] str = new String[]{"dsdsd", "fdfdfd", "hghghgh", "gtrtrt", "rtrtrt", "sdsdsd"};
    private final static String MASK_STR = "@";

    RichEditText richEditor2;
    Button btnAt;
    Button btnTopic;
    Button btnGet1;
    TextView tvContent;
    Button btnEmoji;
    RichTextView richTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        richEditor2 = findViewById(R.id.richEditor2);
        btnAt = findViewById(R.id.btn_at);
        btnTopic = findViewById(R.id.btn_topic);
        btnGet1 = findViewById(R.id.btn_get1);
        tvContent = findViewById(R.id.tv_content);
        btnEmoji = findViewById(R.id.btn_emoji);
        richTextView = findViewById(R.id.richTextView);
        richEditor2.setFilters(new InputFilter[]{CharFilter.Companion.newlineCharFilter()});

        richTextView.setRichClickListener(new RichClickListener() {
            @Override
            public void onClick(@NotNull RichModel richModel) {
                Toast.makeText(MainActivity.this, richModel.getContent(), Toast.LENGTH_SHORT).show();
            }
        });
        richTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (richTextView.isSpanClicked()) {
                    Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "test", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_at:
                startActivityForResult(new Intent(MainActivity.this, UserListActivity.class), REQUEST_USER_CODE_CLICK);
                break;
            case R.id.btn_topic:
                startActivityForResult(new Intent(MainActivity.this, StockListActivity.class), REQUEST_STOCK_CODE_CLICK);
                break;
            case R.id.btn_get1:
//                addSpan();
//                addSpan();
//                addSpan();
//                addSpan();
//                addSpan();
//                addSpan();
//                tvContent.setText(richEditor.getRichContent());
//
//                List<RichModel> list = richEditor2.getRichModelList();
//                Log.i("", "" + list.size());

                String text = "@小明5@ [笑哭][亲亲][害羞]#股票代码2# ";//richEditor2.getText().toString();
                List<RichModel> richModelList = new ArrayList<>();
                richModelList.add(new HideModel("","@dflgjd@", "#f77500"));
                richModelList.add(new HideModel("","@fklgj@", "#f77500"));
                richModelList.add(new HideModel("","@lgjf@", "#f77500"));
//                richEditor2.setIRichClickListener(new RichClickListener() {
//
//                    @Override
//                    public void onClick(@NotNull RichModel richModel) {
//                        Toast.makeText(MainActivity.this, richModel.getContent(), Toast.LENGTH_SHORT).show();
//                    }
//                });
                richEditor2.setTextToSpan("@dflgjd@ [笑哭][亲亲][害羞]ss @fklgj@ kdf@lgjf@ ldkgjkflg[笑哭][亲亲][害羞]hjflgkhjlkgfhjlkfg", richModelList);
                richTextView.setTextToSpan("@dflgjd@[笑哭][亲亲][害羞]ss @fklgj@kdf@lgjf@ldkgjkflg[笑哭][亲亲][害羞]hjflgkhjlkgfhjlkfg", richModelList);
//                richEditor2.enableKeyListener(false);
                break;
            case R.id.btn_emoji:
//                emojiLayout.hideKeyboard();
//                if (emojiLayout.getVisibility() == View.VISIBLE) {
//                    emojiLayout.setVisibility(View.GONE);
//                } else {
//                    emojiLayout.setVisibility(View.VISIBLE);
//                }
//                startActivity(new Intent(this, MainActivity2.class));
                replaceEmogi();
                break;
            case R.id.activity_main:
//                emojiLayout.setVisibility(View.GONE);
                break;
        }
    }


    /**
     * 表情显示
     */
    private void replaceEmogi() {
//        fl_emogi.setVisibility(View.VISIBLE);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_emogi, ExpressionShowFragment.newInstance()).commit();

        ExpressionManager.getInstance().setExpressionDeleteClickListener(new ExpressionGridFragment.ExpressionDeleteClickListener(){

            @Override
            public void expressionDeleteClick(View v) {
                ExpressionShowFragment.delete(richEditor2);
            }
        });

        ExpressionManager.getInstance().setExpressionClickListener(new ExpressionGridFragment.ExpressionClickListener(){

            @Override
            public void expressionClick(String str) {
                ExpressionShowFragment.input(richEditor2, str);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_USER_CODE_CLICK:
                    UserModel userModel = (UserModel) data.getSerializableExtra(UserListActivity.DATA);
                    richEditor2.addSpan(new RichModel("@", userModel.getName(), "#f77500"));
                    break;
                case REQUEST_STOCK_CODE_CLICK:
                    UserModel stockModel = (UserModel) data.getSerializableExtra(StockListActivity.DATA);
                    richEditor2.addSpan(new RichModel("#", stockModel.getName(), "#f77500"));
                    break;
            }
        }
    }

    Random random = new Random();

    public void addSpan() {
        int aaa = random.nextInt(5);
//        richEditor2.addSpan(new RichModel("@", str[aaa], "#f77500"));

        List<RichModel> richModelList = new ArrayList<>();
        richModelList.add(new RichModel("@dflgjd@", "#f77500"));
        richModelList.add(new RichModel("@fklgj@", "#f77500"));
        richModelList.add(new RichModel("@lgjf@", "#f77500"));
        richEditor2.setTextToSpan("@dflgjd@ ss @fklgj@ kdf@lgjf@ ldkgjkflghjflgkhjlkgfhjlkfg", richModelList);
    }
}
