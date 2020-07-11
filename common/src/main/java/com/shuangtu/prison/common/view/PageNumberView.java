package com.shuangtu.prison.common.view;

import android.content.Context;


import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


import com.shuangtu.prison.common.R;
import com.shuangtu.prison.common.constant.Global;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;


public class PageNumberView extends ConstraintLayout implements View.OnFocusChangeListener, View.OnClickListener {

    private static final String TAG = "PageNumberView";

    private TextView tvUp;

    private TextView tvOne;

    private TextView tvTwo;

    private TextView tvThree;

    private TextView tvFour;

    private TextView tvLot;

    private TextView tvLast;

    private TextView tvDown;

    private TextView tvSkip;

    private EditText etInputPage;

    private OnPageNumberListener onPageNumberListener;

    private int pageNumber;

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
        if (pageNumber > 4) {
            tvLast.setText(String.valueOf(pageNumber));
        }
        setShowView();
    }

    public void setOnPageNumberListener(OnPageNumberListener onPageNumberListener) {
        this.onPageNumberListener = onPageNumberListener;
    }

    public PageNumberView(Context context) {
        super(context);
    }

    public PageNumberView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public PageNumberView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView() {

        View view = inflate(getContext(), R.layout.view_page_number, this);
        tvDown = (TextView) view.findViewById(R.id.tvDown);
        tvOne = (TextView) view.findViewById(R.id.tvOne);
        tvTwo = (TextView) view.findViewById(R.id.tvTwo);
        tvThree = (TextView) view.findViewById(R.id.tvThree);
        tvFour = (TextView) view.findViewById(R.id.tvFour);
        tvLot = (TextView) view.findViewById(R.id.tvLot);
        tvLast = (TextView) view.findViewById(R.id.tvLast);
        tvUp = (TextView) view.findViewById(R.id.tvUp);
        tvSkip = (TextView) view.findViewById(R.id.tvSkip);
        etInputPage = (EditText) view.findViewById(R.id.etInputPage);

        tvDown.setOnFocusChangeListener(this);
        tvOne.setOnFocusChangeListener(this);
        tvTwo.setOnFocusChangeListener(this);
        tvThree.setOnFocusChangeListener(this);
        tvFour.setOnFocusChangeListener(this);
        tvLast.setOnFocusChangeListener(this);
        tvUp.setOnFocusChangeListener(this);
        tvSkip.setOnFocusChangeListener(this);

        tvDown.setOnClickListener(this);
        tvOne.setOnClickListener(this);
        tvTwo.setOnClickListener(this);
        tvThree.setOnClickListener(this);
        tvFour.setOnClickListener(this);
        tvLast.setOnClickListener(this);
        tvUp.setOnClickListener(this);
        tvSkip.setOnClickListener(this);

        setPageNumber(100);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (v instanceof TextView) {
            TextView tv = (TextView) v;
            if (hasFocus) {
                tv.setTextColor(Global.toColorFromString("#000343"));
            } else {
                tv.setTextColor(Global.toColorFromString("#0096ff"));
            }
        }
    }

    @Override
    public void onClick(View v) {
        TextView textView = (TextView) v;
        int id = v.getId();
        if (id == R.id.tvUp) {
            int up = Integer.valueOf(tvOne.getText().toString()) - 4;
            if (up <= 0) {
                up = 1;
            }
            tvOne.setText(String.valueOf(up));
            tvTwo.setText(String.valueOf(up + 1));
            tvThree.setText(String.valueOf(up + 2));
            tvFour.setText(String.valueOf(up + 3));
        } else if (id == R.id.tvOne || id == R.id.tvTwo || id == R.id.tvThree || id == R.id.tvFour || id == R.id.tvLast) {
            if (pageNumber > 4) {
                tvOne.setText(String.valueOf(pageNumber - 3));
                tvTwo.setText(String.valueOf(pageNumber - 2));
                tvThree.setText(String.valueOf(pageNumber - 1));
                tvFour.setText(String.valueOf(pageNumber));

            }
            onPage(Integer.valueOf(textView.getText().toString()));
        } else if (id == R.id.tvDown) {
            int down = Integer.valueOf(tvOne.getText().toString()) + 4;
            if (down >= pageNumber - 4) {
                down = pageNumber - 3;
            }
            tvOne.setText(String.valueOf(down));
            tvTwo.setText(String.valueOf(down + 1));
            tvThree.setText(String.valueOf(down + 2));
            tvFour.setText(String.valueOf(down + 3));
        } else if (id == R.id.tvSkip) {
            int page = Integer.valueOf(etInputPage.getText().toString());

            if (page > pageNumber) {
                etInputPage.setText(String.valueOf(pageNumber));
                return;
            } else if (page <= 0) {
                etInputPage.setText("1");
                return;
            }

            onPage(page);
        }
    }

    /**
     * 最大同时显示4个
     */
    private void setShowView() {
        if (pageNumber == 0) {
            tvDown.setVisibility(GONE);
            tvOne.setVisibility(GONE);
            tvTwo.setVisibility(GONE);
            tvThree.setVisibility(GONE);
            tvFour.setVisibility(GONE);
            tvLot.setVisibility(GONE);
            tvLast.setVisibility(GONE);
            tvUp.setVisibility(GONE);
            tvSkip.setVisibility(GONE);
            etInputPage.setVisibility(GONE);
        } else if (pageNumber == 1) {
            tvDown.setVisibility(GONE);
            tvOne.setVisibility(VISIBLE);
            tvTwo.setVisibility(GONE);
            tvThree.setVisibility(GONE);
            tvFour.setVisibility(GONE);
            tvLot.setVisibility(GONE);
            tvLast.setVisibility(GONE);
            tvUp.setVisibility(GONE);
            tvSkip.setVisibility(GONE);
            etInputPage.setVisibility(GONE);
        } else if (pageNumber == 2) {
            tvDown.setVisibility(GONE);
            tvOne.setVisibility(VISIBLE);
            tvTwo.setVisibility(VISIBLE);
            tvThree.setVisibility(GONE);
            tvFour.setVisibility(GONE);
            tvLot.setVisibility(GONE);
            tvLast.setVisibility(GONE);
            tvUp.setVisibility(GONE);
            tvSkip.setVisibility(GONE);
            etInputPage.setVisibility(GONE);
        } else if (pageNumber == 3) {
            tvDown.setVisibility(GONE);
            tvOne.setVisibility(VISIBLE);
            tvTwo.setVisibility(VISIBLE);
            tvThree.setVisibility(VISIBLE);
            tvFour.setVisibility(GONE);
            tvLot.setVisibility(GONE);
            tvLast.setVisibility(GONE);
            tvUp.setVisibility(GONE);
            tvSkip.setVisibility(GONE);
            etInputPage.setVisibility(GONE);
        } else if (pageNumber == 4) {
            tvDown.setVisibility(GONE);
            tvOne.setVisibility(VISIBLE);
            tvTwo.setVisibility(VISIBLE);
            tvThree.setVisibility(VISIBLE);
            tvFour.setVisibility(VISIBLE);
            tvLot.setVisibility(GONE);
            tvLast.setVisibility(GONE);
            tvUp.setVisibility(GONE);
            tvSkip.setVisibility(GONE);
            etInputPage.setVisibility(GONE);
        } else if (pageNumber > 4) {
            tvDown.setVisibility(VISIBLE);
            tvOne.setVisibility(VISIBLE);
            tvTwo.setVisibility(VISIBLE);
            tvThree.setVisibility(VISIBLE);
            tvFour.setVisibility(VISIBLE);
            tvLot.setVisibility(VISIBLE);
            tvLast.setVisibility(VISIBLE);
            tvUp.setVisibility(VISIBLE);
            tvSkip.setVisibility(VISIBLE);
            etInputPage.setVisibility(VISIBLE);
        }
    }

    private void onPage(int index) {
        if (onPageNumberListener != null) {
            onPageNumberListener.loadPage(index);
        }
    }
}
